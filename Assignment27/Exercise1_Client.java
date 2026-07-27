/**
 * Assignment 27 - Exercise 1 (Client)
 * Chat application - Client side.
 * Connects to the chat server and enables bidirectional chat.
 * Type 'bye' to end the conversation.
 */

import java.net.*;
import java.io.*;

public class Exercise1_Client {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  CHAT CLIENT (Exercise 1)");
        System.out.println("========================================\n");

        try (Socket socket = new Socket("localhost", 5001)) {
            System.out.println("Connected to chat server.\n");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // Thread to read messages from server
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        if (serverMsg.equalsIgnoreCase("bye")) {
                            System.out.println("\n[Server has left the chat]");
                            break;
                        }
                        System.out.println("\n[Server]: " + serverMsg);
                        System.out.print("You: ");
                    }
                } catch (IOException e) {
                    if (!Thread.currentThread().isInterrupted())
                        System.out.println("\n[Connection closed]");
                }
            });
            readerThread.start();

            // Main thread reads client's console input and sends to server
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("Chat started! Type 'bye' to exit.\n");
            System.out.print("You: ");

            String clientMsg;
            while ((clientMsg = consoleReader.readLine()) != null) {
                out.println(clientMsg);
                if (clientMsg.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.print("You: ");
            }

            readerThread.interrupt();
            System.out.println("\nClient disconnected.");

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
