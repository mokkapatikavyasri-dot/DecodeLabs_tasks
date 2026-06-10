import java.util.Scanner;

public class DecodeLabs_Java_P2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("       STUDENT GRADE CALCULATOR             ");
        System.out.println("          Powered by DecodeLabs             ");
        System.out.println("============================================");

        // Step 1: Get student name
        System.out.print("Enter Student Name: ");
        String studentName = sc.nextLine();

        // Step 2: Get number of subjects (user can choose)
        int numSubjects = 0;
        while (numSubjects <= 0) {
            System.out.print("Enter number of subjects: ");
            try {
                numSubjects = Integer.parseInt(sc.nextLine());
                if (numSubjects <= 0)
                    System.out.println("Number of subjects must be at least 1!");
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a valid number.");
            }
        }

        // Step 3: Store subject names and marks
        String[] subjects = new String[numSubjects];
        int[]    marks    = new int[numSubjects];
        int      total    = 0;

        System.out.println("\nEnter subject names and marks (out of 100):");
        System.out.println("--------------------------------------------");

        for (int i = 0; i < numSubjects; i++) {

            // Get subject name
            System.out.print("Subject " + (i + 1) + " name : ");
            subjects[i] = sc.nextLine();

            // Get marks with validation (0 to 100 only)
            int mark = -1;
            while (mark < 0 || mark > 100) {
                System.out.print("Marks for " + subjects[i] + " (0-100): ");
                try {
                    mark = Integer.parseInt(sc.nextLine());
                    if (mark < 0 || mark > 100)
                        System.out.println("Invalid! Marks must be between 0 and 100.");
                } catch (Exception e) {
                    System.out.println("Invalid input! Enter a number between 0 and 100.");
                }
            }

            marks[i] = mark;
            total   += mark; // Accumulator loop - adding each mark to total
        }

        // Step 4: Calculate average percentage
        // Using (double) type casting to avoid integer truncation trap!
        // Without cast: 255/3 = 85 (wrong), With cast: 255/3 = 85.0 (correct)
        double average = (double) total / numSubjects;

        // Step 5: Assign grade using Logic Ladder (check strictest condition first!)
        String grade;
        String remarks;
        boolean passed = true;

        if (average >= 90) {
            grade   = "A+";
            remarks = "Outstanding!";
        } else if (average >= 80) {
            grade   = "A";
            remarks = "Excellent!";
        } else if (average >= 70) {
            grade   = "B";
            remarks = "Very Good!";
        } else if (average >= 60) {
            grade   = "C";
            remarks = "Good";
        } else if (average >= 50) {
            grade   = "D";
            remarks = "Satisfactory";
        } else {
            grade   = "F";
            remarks = "Needs Improvement";
            passed  = false;
        }

        // Step 6: Display results using printf for clean formatting
        System.out.println("\n============================================");
        System.out.println("           STUDENT REPORT CARD              ");
        System.out.println("============================================");
        System.out.printf("Student Name   : %s%n", studentName);
        System.out.printf("Total Subjects : %d%n", numSubjects);
        System.out.println("--------------------------------------------");
        System.out.printf("%-20s %-10s%n", "Subject", "Marks");
        System.out.println("--------------------------------------------");

        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("%-20s %-10d%n", subjects[i], marks[i]);
        }

        System.out.println("--------------------------------------------");
        System.out.printf("Total Marks    : %d / %d%n", total, numSubjects * 100);
        System.out.printf("Average        : %.2f%%%n", average);  // %.2f = 2 decimal places
        System.out.printf("Grade          : %s%n", grade);
        System.out.printf("Remarks        : %s%n", remarks);
        System.out.printf("Result         : %s%n", passed ? "PASS" : "FAIL");
        System.out.println("============================================");
        System.out.println("  Powered by DecodeLabs - Kavyasri 2026");
        System.out.println("============================================");

        sc.close();
    }
}