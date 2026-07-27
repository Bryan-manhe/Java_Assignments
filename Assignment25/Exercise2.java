/**
 * Assignment 25 - Exercise 2
 * Bank transaction simulation using threads.
 * Multiple threads perform deposit and withdrawal on a shared account.
 * Demonstrates synchronization to prevent race conditions.
 */

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        System.out.println("   Depositing: $" + amount);
        balance += amount;
        System.out.println("   New Balance: $" + balance);
    }

    public synchronized void withdraw(int amount) {
        if (amount <= balance) {
            System.out.println("   Withdrawing: $" + amount);
            balance -= amount;
            System.out.println("   New Balance: $" + balance);
        } else {
            System.out.println("   Insufficient balance to withdraw $" + amount
                    + " (available: $" + balance + ")");
        }
    }

    public int getBalance() {
        return balance;
    }
}

class TransactionThread extends Thread {
    private BankAccount account;
    private boolean isDeposit;
    private int amount;

    public TransactionThread(BankAccount account, boolean isDeposit,
                             int amount, String name) {
        super(name);
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println(getName() + " starting...");
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
        System.out.println(getName() + " completed.\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  BANK TRANSACTION SIMULATION");
        System.out.println("  (Thread Synchronization)");
        System.out.println("========================================\n");

        BankAccount account = new BankAccount(5000);
        System.out.println("Initial Balance: $" + account.getBalance() + "\n");

        TransactionThread[] threads = {
            new TransactionThread(account, true,  2000, "Deposit-1"),
            new TransactionThread(account, false, 1500, "Withdraw-1"),
            new TransactionThread(account, true,  1000, "Deposit-2"),
            new TransactionThread(account, false, 3000, "Withdraw-2"),
            new TransactionThread(account, false, 5000, "Withdraw-3"),
            new TransactionThread(account, true,   500, "Deposit-3"),
        };

        for (TransactionThread t : threads) {
            t.start();
        }

        try {
            for (TransactionThread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("========================================");
        System.out.println("  FINAL BALANCE: $" + account.getBalance());
        System.out.println("  All transactions completed.");
        System.out.println("========================================");
    }
}
