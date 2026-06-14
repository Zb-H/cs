package BankProgram;

/**
 * Represents a bank customer.
 * Each customer has a first name, last name, and an Account object.
 */
public class Customer {
    private String firstName;
    private String lastName;
    private Account account;

    public Customer(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + firstName + " " + lastName);
        account.displayAccountInfo();
    }
}
