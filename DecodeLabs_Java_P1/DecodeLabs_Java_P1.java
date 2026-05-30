import java.util.Random;
import java.util.Scanner;

public class DecodeLabs_Java_P1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundNumber = 0;
        String playAgain = "y";

        System.out.println("===========================================");
        System.out.println("   WELCOME TO THE NUMBER GUESSING GAME!   ");
        System.out.println("          Powered by DecodeLabs            ");
        System.out.println("===========================================");

        // Multiple rounds using do-while loop
        do {
            roundNumber++;
            int maxAttempts = 7;
            int attempts = 0;
            boolean won = false;

            // Generate random number between 1 and 100
            int secretNumber = random.nextInt(100) + 1;

            System.out.println("\n--- Round " + roundNumber + " ---");
            System.out.println("I have picked a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts. Good luck!\n");

            // Game loop - continues until correct guess or attempts run out
            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + " - Enter your guess: ");

                int guess = -1;

                // Input validation using try-catch
                try {
                    guess = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next(); // clear bad input from scanner
                    continue;
                }

                attempts++;

                // Check the guess
                if (guess == secretNumber) {
                    won = true;
                    int roundScore = (maxAttempts - attempts + 1) * 10;
                    totalScore += roundScore;
                    System.out.println("\n*** Correct! You guessed it in " + attempts + " attempt(s)! ***");
                    System.out.println("Round Score: " + roundScore + " points");
                    break;

                } else if (guess > secretNumber) {
                    System.out.println("Too High! Try lower.");

                } else {
                    System.out.println("Too Low! Try higher.");
                }

                // Show remaining attempts
                int remaining = maxAttempts - attempts;
                if (remaining > 0 && !won) {
                    System.out.println("Attempts remaining: " + remaining);
                }
            }

            // If player ran out of attempts
            if (!won) {
                System.out.println("\nGame Over! You ran out of attempts.");
                System.out.println("The secret number was: " + secretNumber);
            }

            System.out.println("\nTotal Score so far: " + totalScore + " points");
            System.out.print("\nPlay another round? (y/n): ");
            sc.nextLine(); // flush buffer
            playAgain = sc.nextLine().trim().toLowerCase();

        } while (playAgain.equals("y"));

        // Final result
        System.out.println("\n===========================================");
        System.out.println("           GAME OVER - FINAL SCORE        ");
        System.out.println("===========================================");
        System.out.println("Total Rounds Played : " + roundNumber);
        System.out.println("Final Score         : " + totalScore + " points");
        System.out.println("Thank you for playing! - DecodeLabs 2026");
        System.out.println("===========================================");

        sc.close();
    }
}
