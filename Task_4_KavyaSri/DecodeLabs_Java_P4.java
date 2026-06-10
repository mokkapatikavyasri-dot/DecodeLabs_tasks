import java.util.Scanner;

public class DecodeLabs_Java_P4 {

    // Step 1: Currency data — symbol, name, exchange rate to USD
    static String[] codes   = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD", "SGD", "AED", "CNY"};
    static String[] names   = {
        "US Dollar", "Indian Rupee", "Euro",
        "British Pound", "Japanese Yen", "Australian Dollar",
        "Canadian Dollar", "Singapore Dollar", "UAE Dirham", "Chinese Yuan"
    };
    static String[] symbols = {"$", "₹", "€", "£", "¥", "A$", "C$", "S$", "د.إ", "¥"};

    // Exchange rates relative to 1 USD (as of 2026)
    static double[] ratesFromUSD = {
        1.0,      // USD
        83.50,    // INR
        0.92,     // EUR
        0.79,     // GBP
        149.50,   // JPY
        1.53,     // AUD
        1.36,     // CAD
        1.34,     // SGD
        3.67,     // AED
        7.24      // CNY
    };

    // Step 2: Convert amount from one currency to another
    // Logic: First convert to USD, then convert to target
    static double convert(double amount, int fromIndex, int toIndex) {
        double inUSD = amount / ratesFromUSD[fromIndex]; // Step A: to USD
        return inUSD * ratesFromUSD[toIndex];            // Step B: to target
    }

    // Step 3: Display currency menu
    static void showCurrencies() {
        System.out.println("\n--------------------------------------------");
        System.out.printf("%-4s %-6s %-22s %-12s%n", "No.", "Code", "Currency", "Rate(per USD)");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < codes.length; i++) {
            System.out.printf("%-4d %-6s %-22s %s%.2f%n",
                    (i + 1), codes[i], names[i], symbols[i], ratesFromUSD[i]);
        }
        System.out.println("--------------------------------------------");
    }

    // Step 4: Get valid currency choice from user
    static int getCurrencyChoice(Scanner sc, String prompt) {
        int choice = -1;
        while (choice < 1 || choice > codes.length) {
            System.out.print(prompt);
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < 1 || choice > codes.length)
                    System.out.println("Please enter a number between 1 and " + codes.length);
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
        return choice - 1; // return 0-based index
    }

    // Step 5: Get valid amount from user
    static double getAmount(Scanner sc) {
        double amount = -1;
        while (amount <= 0) {
            System.out.print("Enter amount to convert: ");
            try {
                amount = Double.parseDouble(sc.nextLine().trim());
                if (amount <= 0)
                    System.out.println("Amount must be greater than 0!");
            } catch (Exception e) {
                System.out.println("Invalid amount! Enter a valid number.");
            }
        }
        return amount;
    }

    // Step 6: Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("    CURRENCY CONVERTER - DECODELABS P4      ");
        System.out.println("    The Financial Translation Engine         ");
        System.out.println("============================================");

        String again = "y";

        do {
            // Show currency list
            showCurrencies();

            // Get base currency
            int fromIndex = getCurrencyChoice(sc, "\nSelect BASE currency (number): ");

            // Get target currency
            int toIndex = getCurrencyChoice(sc, "Select TARGET currency (number): ");

            // Get amount
            double amount = getAmount(sc);

            // Perform conversion
            double result = convert(amount, fromIndex, toIndex);

            // Display result clearly
            System.out.println("\n============================================");
            System.out.println("           CONVERSION RESULT                ");
            System.out.println("============================================");
            System.out.printf("  From   : %s%.2f %s (%s)%n",
                    symbols[fromIndex], amount, codes[fromIndex], names[fromIndex]);
            System.out.printf("  To     : %s%.2f %s (%s)%n",
                    symbols[toIndex], result, codes[toIndex], names[toIndex]);
            System.out.println("--------------------------------------------");
            System.out.printf("  Rate   : 1 %s = %.4f %s%n",
                    codes[fromIndex],
                    ratesFromUSD[toIndex] / ratesFromUSD[fromIndex],
                    codes[toIndex]);
            System.out.println("============================================");

            // Reverse conversion info
            System.out.printf("  (Reverse: 1 %s = %.4f %s)%n",
                    codes[toIndex],
                    ratesFromUSD[fromIndex] / ratesFromUSD[toIndex],
                    codes[fromIndex]);

            // Convert again?
            System.out.print("\nConvert again? (y/n): ");
            again = sc.nextLine().trim().toLowerCase();

        } while (again.equals("y"));

        System.out.println("\n============================================");
        System.out.println("  Thank you for using Currency Converter!");
        System.out.println("  Powered by DecodeLabs - Kavyasri 2026");
        System.out.println("============================================");

        sc.close();
    }
}