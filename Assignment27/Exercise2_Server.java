/**
 * Assignment 27 - Exercise 2 (Server)
 * Echo server - Server side.
 * Receives a message from the client and sends it back (echo).
 */

import java.net.*;
import java.io.*;

public class Exercise2_Server {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  ECHO SERVER (Exercise 2)");
        System.out.println("========================================\n");

        try (ServerSocket serverSocket = new ServerSocket(5002)) {
            System.out.println("Echo server started on port 5002");
            System.out.println("Waiting for client...\n");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: "
                    + clientSocket.getInetAddress().getHostAddress() + "\n");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                String echoMsg = "Echo: " + message;
                out.println(echoMsg);
                System.out.println("Sent back: " + echoMsg + "\n");

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client requested exit.");
                    break;
                }
            }

            clientSocket.close();
            System.out.println("Echo server closed.");

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
