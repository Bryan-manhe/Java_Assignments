/**
 * Assignment 25 - Exercise 1
 * Create multiple threads by extending Thread class.
 * Each thread prints numbers 1 to 10 with its name.
 */

class NumberThread extends Thread {
    public NumberThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread '" + getName() + "' started.");
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " - " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
        System.out.println("Thread '" + getName() + "' finished.\n");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  MULTIPLE THREADS - EXTENDING THREAD");
        System.out.println("========================================\n");

        System.out.println("Main thread started.\n");

        NumberThread thread1 = new NumberThread("Thread-1");
        NumberThread thread2 = new NumberThread("Thread-2");
        NumberThread thread3 = new NumberThread("Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("All threads have finished execution.");
        System.out.println("Main thread ending.");
    }
}
