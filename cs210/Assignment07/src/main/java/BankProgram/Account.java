package BankProgram;
/**
 * Represents a bank account.
 * The Account class stores account information and handles deposits,
 * withdrawals, and account display.
 */
public class Account {
    private int accountNumber;
    private double balance;
    private double minimumBalance;
    private double overdraftFee;

    public Account(int accountNumber, double balance, double minimumBalance, double overdraftFee) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.overdraftFee = overdraftFee;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    public void depositFunds(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        balance += amount;
        System.out.printf("Deposit successful. New balance: $%.2f%n", balance);
    }

    public void withdrawFunds(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be greater than 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("Not enough funds.");
            return;
        }

        balance -= amount;

        if (balance < minimumBalance) {
            balance -= overdraftFee;
            System.out.printf("Balance went below minimum. Overdraft fee of $%.2f applied.%n", overdraftFee);
        }

        System.out.printf("Withdraw successful. New balance: $%.2f%n", balance);
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Minimum Balance: $%.2f%n", minimumBalance);
        System.out.printf("Overdraft Fee: $%.2f%n", overdraftFee);
    }
}
