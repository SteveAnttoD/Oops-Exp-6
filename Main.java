import java.util.Scanner;

interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
    void viewBalance();
    void viewAccountDetails();
}

abstract class Account {
    protected String acc_name;
    protected long acc_no;
    protected double balance;

    public Account(String acc_name, long acc_no, double balance) {
        this.acc_name = acc_name;
        this.acc_no = acc_no;
        this.balance = balance;
    }
}

class Bank extends Account implements Transaction {

    public Bank(String acc_name, long acc_no, double balance) {
        super(acc_name, acc_no, balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Amount: " + amount);
            viewBalance();
        } else {
            System.out.println("Invalid deposit amount. Must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            System.out.println("Available balance: " + balance);
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Amount: " + amount);
            viewBalance();
        }
    }

    public void viewBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void viewAccountDetails() {
        System.out.println("--- Account Details ---");
        System.out.println("Account Holder: " + acc_name);
        System.out.println("Account Number: " + acc_no);
        System.out.println("Current Balance: " + balance);
        System.out.println("-------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank myAccount = new Bank("Jane Doe", 9876543210L, 10000.0);

        while (true) {
            System.out.println("\n--- Welcome to the Banking Application ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Please choose an option (1-5): ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    try {
                        double depositAmount = scanner.nextDouble();
                        myAccount.deposit(depositAmount);
                    } catch (Exception e) {
                        System.out.println("Invalid amount. Please enter a number.");
                        scanner.next();
                    }
                    break;
                
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        double withdrawAmount = scanner.nextDouble();
                        myAccount.withdraw(withdrawAmount);
                    } catch (Exception e) {
                        System.out.println("Invalid amount. Please enter a number.");
                        scanner.next();
                    }
                    break;
                
                case 3:
                    myAccount.viewBalance();
                    break;
                
                case 4:
                    myAccount.viewAccountDetails();
                    break;
                
                case 5:
                    System.out.println("Thank you for using the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                
                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }
    }
}