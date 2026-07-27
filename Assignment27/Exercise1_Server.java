/**
 * Assignment 27 - Exercise 1 (Server)
 * Chat application - Server side.
 * Accepts a client connection and enables bidirectional chat.
 * Type 'bye' to end the conversation.
 */

import java.net.*;
import java.io.*;

public class Exercise1_Server {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  CHAT SERVER (Exercise 1)");
        System.out.println("========================================\n");

        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("Server started on port 5001");
            System.out.println("Waiting for client to connect...\n");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: "
                    + clientSocket.getInetAddress().getHostAddress() + "\n");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // Thread to read messages from client
            Thread readerThread = new Thread(() -> {
                try {
                    String clientMsg;
                    while ((clientMsg = in.readLine()) != null) {
                        if (clientMsg.equalsIgnoreCase("bye")) {
                            System.out.println("\n[Client has left the chat]");
                            break;
                        }
                        System.out.println("\n[Client]: " + clientMsg);
                        System.out.print("You: ");
                    }
                } catch (IOException e) {
                    if (!Thread.currentThread().isInterrupted())
                        System.out.println("\n[Connection closed]");
                }
            });
            readerThread.start();

            // Main thread reads server's console input and sends to client
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("Chat started! Type 'bye' to exit.\n");
            System.out.print("You: ");

            String serverMsg;
            while ((serverMsg = consoleReader.readLine()) != null) {
                out.println(serverMsg);
                if (serverMsg.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.print("You: ");
            }

            readerThread.interrupt();
            clientSocket.close();
            System.out.println("\nServer disconnected.");

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
