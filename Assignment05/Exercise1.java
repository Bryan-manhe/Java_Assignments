/**
 * Assignment 05 - Exercise 1
 * To-Do List application using ArrayList to store tasks
 * and StringBuffer to display tasks.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise1 {
    private ArrayList<String> tasks;
    private int taskCounter;

    public Exercise1() {
        tasks = new ArrayList<>();
        taskCounter = 0;
    }

    public void addTask(String task) {
        taskCounter++;
        tasks.add(taskCounter + ". " + task);
        System.out.println("Task added: " + task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            String removed = tasks.remove(index);
            System.out.println("Task removed: " + removed);
            // Re-number remaining tasks using StringBuffer
            renumberTasks();
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private void renumberTasks() {
        ArrayList<String> renumbered = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskText = tasks.get(i).substring(tasks.get(i).indexOf(". ") + 2);
            renumbered.add((i + 1) + ". " + taskText);
        }
        tasks = renumbered;
    }

    public void displayTasks() {
        System.out.println("\n--- TO-DO LIST ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            // Using StringBuffer to build the display
            StringBuffer sb = new StringBuffer();
            for (String task : tasks) {
                sb.append(task).append("\n");
            }
            System.out.print(sb.toString());
        }
        System.out.println("------------------");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercise1 todoList = new Exercise1();

        System.out.println("=== TO-DO LIST APPLICATION ===\n");

        // Pre-populate with some tasks
        todoList.addTask("Complete Java assignment");
        todoList.addTask("Study for math exam");
        todoList.addTask("Buy groceries");
        todoList.addTask("Call parents");
        todoList.addTask("Read Chapter 5");

        todoList.displayTasks();

        // Interactive operations
        System.out.println("\n--- Interactive Operations ---");
        System.out.print("Enter a new task to add: ");
        String newTask = scanner.nextLine();
        todoList.addTask(newTask);
        todoList.displayTasks();

        System.out.print("\nEnter task number to remove: ");
        int removeIndex = scanner.nextInt() - 1;
        todoList.removeTask(removeIndex);
        todoList.displayTasks();

        System.out.println("\nTotal tasks remaining: " + todoList.getTaskCount());
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
        System.out.println("ArrayList: Dynamic storage of tasks");
        System.out.println("StringBuffer: Efficient display formatting");

        scanner.close();
    }
}
