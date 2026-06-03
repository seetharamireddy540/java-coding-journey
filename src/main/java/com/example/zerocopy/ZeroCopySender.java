package com.example.zerocopy;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ZeroCopySender {
    public static void main(String[] args) throws Exception {
        // 1. Setup: Create a 100MB dummy file if one doesn't exist
        File f = new File("large_payload.text");
        if (!f.exists()) {
            System.out.println("Creating 100MB dummy file...");
            RandomAccessFile dummy = new RandomAccessFile(f, "rw");
            dummy.setLength(100 * 1024 * 1024); // 100 MB
            dummy.close();
        }

        // 2. Open the file to send
        RandomAccessFile file = new RandomAccessFile("large_payload.text", "r");
        FileChannel fileChannel = file.getChannel();

        // 3. Connect to the Server
        System.out.println("Connecting to server...");
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        // 4. THE ZERO COPY SEND COMMAND
        long position = 0;
        long count = fileChannel.size();

        System.out.println("Sending " + count + " bytes via Zero Copy...");
        long startTime = System.currentTimeMillis();

        // Send the file
        long transferred = fileChannel.transferTo(position, count, socketChannel);

        long endTime = System.currentTimeMillis();
        System.out.println("Sent " + transferred + " bytes in " + (endTime - startTime) + " ms.");

        // 5. Clean up
        fileChannel.close();
        file.close();
        socketChannel.close();
    }
}