/**
 * Assignment 27 - Exercise 2 (Client)
 * Echo server - Client side.
 * Sends messages to the echo server and displays the echoed response.
 * Type 'exit' to quit.
 */

import java.net.*;
import java.io.*;

public class Exercise2_Client {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  ECHO CLIENT (Exercise 2)");
        System.out.println("========================================\n");

        try (Socket socket = new Socket("localhost", 5002)) {
            System.out.println("Connected to echo server.\n");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));

            System.out.println("Type messages to echo. Type 'exit' to quit.\n");

            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                System.out.println("Server response: " + response + "\n");
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            System.out.println("Client disconnected.");

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
