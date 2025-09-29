package com.example.javacodingjourney.chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private volatile boolean running = true;

    public ChatClient() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            // Connect to server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to chat server!");

            // Setup I/O streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start thread to receive messages
            Thread receiveThread = new Thread(this::receiveMessages);
            receiveThread.start();

            // Send messages from console input
            sendMessages();

            // Wait for receive thread to finish
            receiveThread.join();

        } catch (IOException | InterruptedException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    private void receiveMessages() {
        try {
            String message;
            while (running && (message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Error receiving message: " + e.getMessage());
            }
        }
    }

    private void sendMessages() {
        System.out.println("Type your messages (type '/quit' to exit):");

        while (running) {
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("/quit")) {
                out.println("/quit");
                running = false;
                break;
            }

            out.println(message);
        }
    }

    private void cleanup() {
        running = false;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
        System.out.println("Disconnected from server.");
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
    }
}