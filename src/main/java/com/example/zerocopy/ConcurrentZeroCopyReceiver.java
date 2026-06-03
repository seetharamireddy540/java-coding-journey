package com.example.zerocopy;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentZeroCopyReceiver {
    public static void main(String[] args) throws Exception {
        // 1. Open the server socket
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8080));

        // 2. Create a thread pool strictly limited to 20 concurrent threads
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AtomicInteger clientCounter = new AtomicInteger(1);

        System.out.println("Concurrent Server is listening on port 8080...");
        System.out.println("Ready to accept up to 20 connections at once.");

        // 3. The Infinite Accept Loop
        while (true) {
            // This blocks until a client connects
            SocketChannel clientChannel = serverSocket.accept();
            int clientId = clientCounter.getAndIncrement();
            System.out.println("Client " + clientId + " connected!");

            // Hand the channel off to a background thread and immediately loop back to accept()
            threadPool.submit(() -> handleClient(clientChannel, clientId));
        }
    }

    // 4. The background task that actually does the Zero Copy work
    private static void handleClient(SocketChannel socketChannel, int clientId) {
        try {
            // Give each connection its own unique file name so they don't overwrite each other
            String fileName = "received_payload_" + clientId + ".dat";
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = file.getChannel();

            long position = 0;
            long bytesRead;
            long totalReceived = 0;
            long maxCount = 1024 * 1024 * 1024; // Stream up to 1GB at a time

            System.out.println("[Client " + clientId + "] Receiving data via Zero Copy...");

            // THE ZERO COPY RECEIVE COMMAND
            while ((bytesRead = fileChannel.transferFrom(socketChannel, position, maxCount)) > 0) {
                position += bytesRead;
                totalReceived += bytesRead;
            }

            System.out.println("[Client " + clientId + "] Success! Saved " + totalReceived + " bytes to " + fileName);

            // Clean up resources for this specific client
            fileChannel.close();
            file.close();
            socketChannel.close();

        } catch (Exception e) {
            System.err.println("[Client " + clientId + "] Connection error: " + e.getMessage());
        }
    }
}