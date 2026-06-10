import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// ============================================================
//  BankAccount.java  —  The Data Vault (Business Logic Layer)
//  All fields private; access only through validated methods.
// ============================================================
class BankAccount {

    private String accountHolder;
    private String accountNumber;
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber,
                       double initialBalance, String pin) {
        this.accountHolder    = accountHolder;
        this.accountNumber    = accountNumber;
        this.balance          = initialBalance;
        this.pin              = pin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: ₹" + String.format("%.2f", initialBalance));
    }

    // ── Getters (read-only access) ────────────────────────────
    public String getAccountHolder() { return accountHolder; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance()        { return balance; }

    // ── PIN validation ────────────────────────────────────────
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    // ── Deposit (mutator with business rule) ──────────────────
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;   // reject non-positive amounts
        }
        balance += amount;
        transactionHistory.add(String.format("Deposited  : +₹%.2f  | Balance: ₹%.2f", amount, balance));
        return true;
    }

    // ── Withdraw (mutator with business rules) ────────────────
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;   // reject non-positive amounts
        }
        if (amount > balance) {
            return false;   // reject overdraft
        }
        balance -= amount;
        transactionHistory.add(String.format("Withdrawn  : -₹%.2f  | Balance: ₹%.2f", amount, balance));
        return true;
    }

    // ── Transaction history ───────────────────────────────────
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); // return a copy
    }
}


// ============================================================
//  ATM.java  —  The Customer Lobby (User Interface Layer)
//  Handles Scanner, menus, and delegates logic to BankAccount.
//  Knows NOTHING about balance math — only presentation.
// ============================================================
class ATM {

    private BankAccount account;
    private Scanner scanner;
    private boolean authenticated;

    // ── Constructor ───────────────────────────────────────────
    public ATM(BankAccount account) {
        this.account       = account;
        this.scanner       = new Scanner(System.in);
        this.authenticated = false;
    }

    // ── Entry point ───────────────────────────────────────────
    public void start() {
        printBanner();
        if (authenticateUser()) {
            showMainMenu();
        }
        System.out.println("\n  Thank you for using DecodeLabs ATM. Goodbye!\n");
        scanner.close();
    }

    // ── PIN authentication (up to 3 attempts) ─────────────────
    private boolean authenticateUser() {
        System.out.println("  Account: " + account.getAccountNumber()
                         + "  |  Holder: " + account.getAccountHolder());
        System.out.println("─".repeat(50));

        int attempts = 3;
        while (attempts > 0) {
            System.out.print("\n  Enter PIN: ");
            String pin = scanner.nextLine().trim();

            if (account.validatePin(pin)) {
                authenticated = true;
                System.out.println("\n  ✔  Authentication successful. Welcome, "
                                 + account.getAccountHolder() + "!\n");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("  ✘  Incorrect PIN. " + attempts + " attempt(s) remaining.");
                } else {
                    System.out.println("  ✘  Too many failed attempts. Card blocked.\n");
                }
            }
        }
        return false;
    }

    // ── Main menu loop ────────────────────────────────────────
    private void showMainMenu() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInput("  Your choice: ", 1, 5);

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> depositCash();
                case 3 -> withdrawCash();
                case 4 -> showHistory();
                case 5 -> running = false;
            }
        }
    }

    // ── Operation: Check Balance ──────────────────────────────
    private void checkBalance() {
        printSectionHeader("BALANCE ENQUIRY");
        System.out.printf("  Available Balance : ₹%.2f%n", account.getBalance());
        printDivider();
    }

    // ── Operation: Deposit ────────────────────────────────────
    private void depositCash() {
        printSectionHeader("DEPOSIT CASH");
        double amount = readDoubleInput("  Enter deposit amount (₹): ");

        if (account.deposit(amount)) {
            System.out.printf("  ✔  ₹%.2f deposited successfully.%n", amount);
            System.out.printf("  New Balance: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println("  ✘  Invalid amount. Deposit must be greater than ₹0.");
        }
        printDivider();
    }

    // ── Operation: Withdraw ───────────────────────────────────
    private void withdrawCash() {
        printSectionHeader("CASH WITHDRAWAL");
        System.out.printf("  Current Balance: ₹%.2f%n", account.getBalance());
        double amount = readDoubleInput("  Enter withdrawal amount (₹): ");

        if (amount <= 0) {
            System.out.println("  ✘  Invalid amount. Withdrawal must be greater than ₹0.");
        } else if (amount > account.getBalance()) {
            System.out.printf("  ✘  Insufficient funds. Available: ₹%.2f%n", account.getBalance());
        } else if (account.withdraw(amount)) {
            System.out.printf("  ✔  ₹%.2f dispensed. Please collect your cash.%n", amount);
            System.out.printf("  Remaining Balance: ₹%.2f%n", account.getBalance());
        }
        printDivider();
    }

    // ── Operation: Transaction History ───────────────────────
    private void showHistory() {
        printSectionHeader("TRANSACTION HISTORY");
        List<String> history = account.getTransactionHistory();
        for (int i = 0; i < history.size(); i++) {
            System.out.printf("  %2d. %s%n", i + 1, history.get(i));
        }
        printDivider();
    }

    // ────────────────────────────────────────────────────────
    //  SECURITY GATE: Robust input validation methods
    //  Uses hasNextDouble() / hasNextInt() to "peek" before
    //  consuming — never crashes on bad input.
    // ────────────────────────────────────────────────────────

    /** Read a valid integer within [min, max]. Loops until valid. */
    private int readIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                if (value >= min && value <= max) return value;
                System.out.println("  Please enter a number between " + min + " and " + max + ".");
            } else {
                System.out.println("  Invalid input. Please enter a number.");
                scanner.nextLine(); // clear bad token
            }
        }
    }

    /** Read a valid positive double. Loops until valid. */
    private double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // clear buffer
                return value;
            } else {
                System.out.println("  Invalid amount. Please enter a numeric value (e.g. 500.00).");
                scanner.nextLine(); // clear bad token
            }
        }
    }

    // ── Display helpers ───────────────────────────────────────
    private void printBanner() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("        DecodeLabs ATM — Batch 2026");
        System.out.println("═".repeat(50));
    }

    private void printMenu() {
        System.out.println("\n  ┌─────────────────────────────┐");
        System.out.println("  │        MAIN MENU            │");
        System.out.println("  ├─────────────────────────────┤");
        System.out.println("  │  1. Check Balance           │");
        System.out.println("  │  2. Deposit Cash            │");
        System.out.println("  │  3. Withdraw Cash           │");
        System.out.println("  │  4. Transaction History     │");
        System.out.println("  │  5. Exit                    │");
        System.out.println("  └─────────────────────────────┘");
    }

    private void printSectionHeader(String title) {
        System.out.println("\n  ── " + title + " " + "─".repeat(Math.max(0, 34 - title.length())));
    }

    private void printDivider() {
        System.out.println("  " + "─".repeat(46));
    }
}


// ============================================================
//  Main.java  —  Application Entry Point
//  Seeds a demo account and launches the ATM.
// ============================================================
class ATMInterface {
    public static void main(String[] args) {

        // Create a bank account  (holder, accountNo, balance, PIN)
        BankAccount account = new BankAccount(
            "Ravi Kumar",
            "DL-2026-00142",
            15000.00,
            "1234"
        );

        // Plug the account into the ATM and start the session
        ATM atm = new ATM(account);
        atm.start();
    }
}