package BankProgram;

/**
 * Represents a bank account with a routing number.
 * The Bank class can create new Customer objects with Account objects.
 *
 * @author Zhaobi Huang
 * @version v1.0
 * @git https://github.com/Zb-H/cs/tree/main/cs210/Assignment07
 */
public class Bank {
    private int routingNumber;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public Customer createNewCustomer(
            String firstName,
            String lastName,
            int accountNumber,
            double balance,
            double minimumBalance,
            double overdraftFee
    ) {
        Account account = new Account(accountNumber, balance, minimumBalance, overdraftFee);
        Customer customer = new Customer(firstName, lastName, account);
        return customer;
    }

    public static void main(String[] args) {
        Bank bank = new Bank(123456789);

        Customer customer1 = bank.createNewCustomer(
                "Abc",
                "Def",
                1001,
                500.00,
                100.00,
                50.00
        );

        Customer customer2 = bank.createNewCustomer(
                "Xyz",
                "Uvw",
                100000000,
                1000.00,
                111111.00,
                200.00
        );

        System.out.println("Bank Routing Number: " + bank.getRoutingNumber());
        System.out.println();

        System.out.println("=== Customer 1 Info ===");
        customer1.displayCustomerInfo();

        System.out.println();

        System.out.println("=== Customer 2 Info ===");
        customer2.displayCustomerInfo();

        System.out.println();

        System.out.println("=== Deposit to Customer 1 ===");
        customer1.getAccount().depositFunds(200);

        System.out.println();

        System.out.println("=== Withdraw from Customer 2 ===");
        customer2.getAccount().withdrawFunds(850);

        System.out.println();

        System.out.println("=== Updated Customer 1 Info ===");
        customer1.displayCustomerInfo();

        System.out.println();

        System.out.println("=== Updated Customer 2 Info ===");
        customer2.displayCustomerInfo();
    }
}
