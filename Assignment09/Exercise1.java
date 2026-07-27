/**
 * Assignment 09 - Exercise 1
 * Bank Account program where account number is final and cannot be changed.
 */

class BankAccount {
    // Final variable - cannot be changed once assigned
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;

    private static int accountCounter = 1000;

    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountNumber = "ACC" + (++accountCounter); // Assigned once in constructor
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + String.format("%.2f", amount));
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        // accountNumber = "CHANGED"; // COMPILATION ERROR! Cannot reassign final variable
        System.out.println("(Note: accountNumber is final - cannot be modified after creation)");
    }
}

// Demonstrating final method
class SavingsAccount extends BankAccount {
    private final double interestRate; // Final variable

    public SavingsAccount(String accountHolderName, double initialDeposit, double interestRate) {
        super(accountHolderName, initialDeposit);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        // interestRate is final, cannot be changed
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

// final class cannot be extended
final class FixedDepositAccount extends BankAccount {
    private final int tenureMonths;

    public FixedDepositAccount(String accountHolderName, double initialDeposit, int tenureMonths) {
        super(accountHolderName, initialDeposit);
        this.tenureMonths = tenureMonths;
    }

    public void displayFDInfo() {
        System.out.println("Tenure: " + tenureMonths + " months");
        System.out.println("Maturity Amount: $" + String.format("%.2f", getBalance() * 1.08));
    }

    private double getBalance() {
        // Accessing balance through a method since it's private in parent
        return 0; // Simplified
    }
}

// Uncommenting below would cause compilation error:
// class PremiumFD extends FixedDepositAccount { } // Cannot inherit from final class

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== BANK ACCOUNT - FINAL KEYWORD DEMO ===\n");

        System.out.println("--- Final Variable Demo ---");
        BankAccount acc1 = new BankAccount("Alice Johnson", 5000);
        acc1.displayAccountInfo();

        System.out.println();
        BankAccount acc2 = new BankAccount("Bob Smith", 10000);
        acc2.displayAccountInfo();

        System.out.println("\n--- Transactions ---");
        acc1.deposit(2500);
        acc1.withdraw(1000);
        acc1.displayAccountInfo();

        System.out.println("\n--- Final Variable in Subclass ---");
        SavingsAccount savings = new SavingsAccount("Carol Davis", 20000, 4.5);
        savings.displayAccountInfo();
        savings.calculateInterest();

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. final variable: accountNumber cannot be reassigned");
        System.out.println("2. final variable: interestRate cannot be changed");
        System.out.println("3. final class: FixedDepositAccount cannot be extended");
        System.out.println("(Uncommenting class PremiumFD would show compilation error)");
    }
}
