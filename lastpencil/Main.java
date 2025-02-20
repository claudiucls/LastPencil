package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    enum Names {John, Jack}

    static int pencils = 0;
    static String firstName = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("How many pencils would you like to use:");
        pencils = getInitPencils(); // User input for the number of pencils

        System.out.println("Who will be the first (John, Jack):");
        firstName = getValidName(); // User input for the first player
        int num;
        while (pencils > 0) {
            showPencils(pencils); // Display remaining pencils
            System.out.printf("%s's turn!%n", firstName);

            firstName = firstName.equalsIgnoreCase(Names.John.name()) ? Names.Jack.name() : Names.John.name();
            if (firstName.equals("John")) {
                num = getPencilsFromBot();
            } else {
                num = getPencilsFromOtherPlayer();
            }
            // Player decides how many pencils to take
            pencils -= num;
        }
        System.out.printf("%s won!", firstName);
        scanner.close();
    }

    public static int getPencilsFromBot() {
        int number;
        Random random = new Random();
        while (true) {
            if (pencils % 4 == 1) {
                number = random.nextInt(3);
                if (number == 0) continue;
                System.out.println(number);
                return number;  // Indicating a losing position
            } else if (pencils % 4 == 2) {
                System.out.println(1);
                return 1;
            } else if (pencils % 4 == 3) {
                System.out.println(2);
                return 2;
            } else {  // n % 4 == 0
                System.out.println(3);
                return 3;  // Take 3 pencils
            }
        }

    }

    public static int getPencilsFromOtherPlayer() {
        int number;
        while (true) {
            number = getValidPencilCount();
            if (number <= pencils) {
                break;
            } else {
                System.out.println("Too many pencils were taken");
            }
        }
        return number;
    }

    public static int getInitPencils() {
        int number;
        while (true) {
            String s = scanner.nextLine(); // Capture user input
            if (s.matches("^[0-9]+$")) { // Check if the input matches a numeric value
                try {
                    number = Integer.parseInt(s); // Try to parse the input as an integer
                    if (number > 0) {
                        break; // If valid, break the loop
                    } else {
                        System.out.println("The number of pencils should be positive");
                    }
                } catch (NumberFormatException e) {
                    // Should never occur since regex already validated numeric input
                    System.out.println("The number of pencils should be numeric");
                }
            } else {
                System.out.println("The number of pencils should be numeric"); // Invalid input
            }
        }
        return number;
    }

    public static int getValidPencilCount() {
        int number = -1;
        while (number < 1 || number > 3) { // Ensure the user inputs a valid number of pencils (1, 2, or 3)
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number < 1 || number > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            } else {
                System.out.println("Possible values: '1', '2' or '3'");
                scanner.next(); // Consume invalid input
            }
        }
        return number;
    }

    static void showPencils(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    static String getValidName() {
        String s;
        while (true) {
            s = scanner.next();
            if (s.equalsIgnoreCase(Names.John.name()) || s.equalsIgnoreCase(Names.Jack.name())) {
                break;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }
        return s;
    }
}
