/**
 * Assignment 26 - Exercise 2
 * Bank deposit and withdrawal using Runnable interface.
 * Multiple threads perform transactions on a shared account.
 * Demonstrates synchronized methods with Runnable.
 */

class SharedAccount {
    private int balance;

    public SharedAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        System.out.println("   Depositing: $" + amount);
        balance += amount;
        System.out.println("   Balance after deposit: $" + balance);
    }

    public synchronized void withdraw(int amount) {
        if (amount <= balance) {
            System.out.println("   Withdrawing: $" + amount);
            balance -= amount;
            System.out.println("   Balance after withdrawal: $" + balance);
        } else {
            System.out.println("   Cannot withdraw $" + amount
                    + " (available: $" + balance + ")");
        }
    }

    public int getBalance() {
        return balance;
    }
}

class DepositTask implements Runnable {
    private SharedAccount account;
    private int amount;

    public DepositTask(SharedAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " performing deposit of $" + amount);
        account.deposit(amount);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}

class WithdrawTask implements Runnable {
    private SharedAccount account;
    private int amount;

    public WithdrawTask(SharedAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " performing withdrawal of $" + amount);
        account.withdraw(amount);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  DEPOSIT/WITHDRAWAL - RUNNABLE");
        System.out.println("========================================\n");

        SharedAccount account = new SharedAccount(10000);
        System.out.println("Initial Balance: $" + account.getBalance() + "\n");

        Thread[] threads = {
            new Thread(new DepositTask(account, 3000),   "Deposit-1"),
            new Thread(new WithdrawTask(account, 2000),  "Withdraw-1"),
            new Thread(new DepositTask(account, 1500),   "Deposit-2"),
            new Thread(new WithdrawTask(account, 4000),  "Withdraw-2"),
            new Thread(new WithdrawTask(account, 10000), "Withdraw-3"),
            new Thread(new DepositTask(account, 2500),   "Deposit-3"),
        };

        for (Thread t : threads) {
            t.start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main interrupted.");
        }

        System.out.println("\n========================================");
        System.out.println("  FINAL BALANCE: $" + account.getBalance());
        System.out.println("========================================");
    }
}
