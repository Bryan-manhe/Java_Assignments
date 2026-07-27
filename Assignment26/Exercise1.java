/**
 * Assignment 26 - Exercise 1
 * Create and run two simultaneous tasks using Runnable interface.
 * Task 1: Print numbers 1-10 with a 300ms delay.
 * Task 2: Print squares of numbers 1-10 with a delay.
 */

class NumberPrinter implements Runnable {
    @Override
    public void run() {
        System.out.println("   [NumberPrinter] Task started.");
        for (int i = 1; i <= 10; i++) {
            System.out.println("   [NumberPrinter] Number: " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("   [NumberPrinter] Interrupted.");
            }
        }
        System.out.println("   [NumberPrinter] Task finished.\n");
    }
}

class SquareCalculator implements Runnable {
    @Override
    public void run() {
        System.out.println("   [SquareCalculator] Task started.");
        for (int i = 1; i <= 10; i++) {
            System.out.println("   [SquareCalculator] Square of " + i + " = " + (i * i));
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("   [SquareCalculator] Interrupted.");
            }
        }
        System.out.println("   [SquareCalculator] Task finished.\n");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  TWO SIMULTANEOUS TASKS - RUNNABLE");
        System.out.println("========================================\n");

        System.out.println("Main thread starting both tasks...\n");

        Thread thread1 = new Thread(new NumberPrinter(), "NumberPrinter");
        Thread thread2 = new Thread(new SquareCalculator(), "SquareCalculator");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Both tasks completed. Main thread ending.");
    }
}
