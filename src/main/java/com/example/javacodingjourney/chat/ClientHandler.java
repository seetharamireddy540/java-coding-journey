package com.example.javacodingjourney.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Handles individual client connections
class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Setup I/O streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get username
            out.println("Enter your username:");
            username = in.readLine();

            // Announce new user
            String joinMessage = username + " has joined the chat!";
            System.out.println(joinMessage);
            ChatServer.broadcastMessage(joinMessage, this);
            out.println("Welcome to the chat, " + username + "!");

            // Handle messages
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("/quit")) {
                    break;
                }

                String formattedMessage = username + ": " + message;
                System.out.println(formattedMessage);
                ChatServer.broadcastMessage(formattedMessage, this);
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            // Clean up
            try {
                if (username != null) {
                    String leaveMessage = username + " has left the chat.";
                    System.out.println(leaveMessage);
                    ChatServer.broadcastMessage(leaveMessage, this);
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatServer.removeClient(this);
        }
    }

    // Send message to this client
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}