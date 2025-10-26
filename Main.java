import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
     * =============================
     * Welcome & Main Menu
     * =============================
     */
    private static final java.util.Scanner SC = new java.util.Scanner(System.in);
    private static String cyan = "\u001B[36m";
    private static String yellow = "\u001B[33m";
    private static String green = "\u001B[32m";
    private static String reset = "\u001B[0m";
    private static String red = "\u001B[31m";
    // For ConnectFourGame
    private static int ROWS, COLS;
    private static char[][] board;
    private static char currentPlayer; // 'X' or 'O'
    private static boolean singlePlayer; // true => 1P vs CPU
    private static final java.util.Random RNG = new java.util.Random();

    private static void welcomeMessage() {
        clearScreen();

        System.out.println(yellow +
                " █████   ███   █████          ████                                        \n" +
                "░░███   ░███  ░░███          ░░███                                        \n" +
                " ░███   ░███   ░███   ██████  ░███   ██████   ██████  █████████████    ██████  \n" +
                " ░███   ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███ \n" +
                " ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████  \n" +
                "  ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░   \n" +
                "    ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████  \n" +
                "     ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░   \n" +
                reset);

        System.out.println(green + "Welcome to CMPE343 Project 1" + reset);
        System.out.println(green + "-----------------------------------------------" + reset);
        System.out.println(green + "Group Members:" + reset);
        System.out.println(cyan + " - Bora Görgün" + reset);
        System.out.println(cyan + " - Can Ersan" + reset);
        System.out.println(cyan + " - Melek Sadiki" + reset);
        System.out.println(cyan + " - Mikail Karacaer" + reset);
        System.out.println();
        System.out.println(yellow + "Press ENTER to continue..." + reset);

        SC.nextLine();
        clearScreen();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[A] Primary School" + reset);
            System.out.println(cyan + "[B] Secondary School" + reset);
            System.out.println(cyan + "[C] High School" + reset);
            System.out.println(cyan + "[D] University" + reset);
            System.out.println(cyan + "[E] Terminate" + reset);
            System.out.println(red + "********************************" + reset);
            System.out.println(green + "Please select an option to continue: " + reset);
            int choice = readMenuOption(SC, 'A', 'E');

            switch (choice) {
                case 'A':
                    subMenuOption1();
                    break;
                case 'B':
                    subMenuOption2();
                    break;
                case 'C':
                    subMenuOption3();
                    break;
                case 'D':
                    subMenuOption4();
                    break;
                case 'E':
                    System.out.println(green + "\nTurning the program off...");
                    System.out.println(red + "Thank you for using our program!");
                    return;
            }
            clearScreen();
        }
    }

    private static char readMenuOption(Scanner scan, char min, char max) {
        while (true) {
            String s = scan.nextLine().trim().toUpperCase();

            if (s.isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter a letter between " + min + " and " + max
                        + ": " + reset);
                continue;
            }

            if (s.length() > 1) {
                System.out.print(red + "Invalid input. Please enter a single letter: " + reset);
                continue;

            }

            char choice = s.charAt(0);

            if (choice < min || choice > max) {
                System.out.print(
                        red + "Please enter a valid option letter between [" + min + "] and [" + max + "]: " + reset);
                continue;
            }
            return choice;
        }
    }

    /*
     * private static int readInt(Scanner scan, int min, int max) {
     * while (true) {
     * String s = scan.nextLine().trim();
     * 
     * if (s.isEmpty()) {
     * System.out.print(red +
     * "Input cannot be empty. Please enter a number between " + min + " and " + max
     * + ": " + reset);
     * continue;
     * }
     * if (!s.matches("\\d+")) {
     * System.out.print(red + "Please enter a number between " + min + " and " + max
     * + ": " + reset);
     * continue;
     * }
     * 
     * int val;
     * try {
     * val = Integer.parseInt(s);
     * } catch (NumberFormatException e) {
     * System.out.print(red + "Please enter a number between " + min + " and " + max
     * + ": " + reset);
     * continue;
     * }
     * 
     * if (val < min || val > max) {
     * System.out.print(red + "Please enter a number between " + min + " and " + max
     * + ": " + reset);
     * continue;
     * }
     * 
     * return val;
     * }
     * }
     */

    /* Optional helpers for menu I/O (no implementation yet) */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*
     * =============================
     * Option A — Primary School
     * =============================
     */
    private static void subMenuOption1() {
        System.out.println("");
        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[A] Calculate Age and Zodiac Sign" + reset);
            System.out.println(cyan + "[B] Reverse the Words" + reset);
            System.out.println(cyan + "[C] Return To Main Menu" + reset);
            System.out.println(red + "********************************" + reset);
            System.out.println(green + "Please select an option to continue: " + reset);
            int choice = readMenuOption(SC, 'A', 'C');

            switch (choice) {
                case 'A':
                    ageAndZodiacSignDetection();
                    break;
                case 'B':
                    reverseTheWords();
                    break;
                case 'C':
                    System.out.println(green + "\nReturning the main menu." + reset);
                    return;
            }
            clearScreen();
        }

    }

    private static void ageAndZodiacSignDetection() {
        System.out.print(yellow + "Please enter your year of birth: " + reset);
        int birthYear = getYear(SC);

        System.out.print(yellow + "Please enter your month of birth (1-12): " + reset);
        int birthMonth = getMonth(SC);

        System.out.print(yellow + "Please enter your day of birth: " + reset);
        int birthDay = getDay(SC, birthMonth, birthYear);

        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int currentDay = getCurrentDay();

        int years = currentYear - birthYear;
        int months = currentMonth - birthMonth;
        int days = currentDay - birthDay;

        int tempMonth = currentMonth;
        int tempYear = currentYear;

        while (days < 0) {

            int prevMonth = tempMonth - 1;
            int prevYear = tempYear;
            if (prevMonth == 0) {
                prevMonth = 12;
                prevYear -= 1;
            }
            int dim = getDaysInMonth(prevMonth, prevYear);

            days += dim;
            months -= 1;

            tempMonth = prevMonth;
            tempYear = prevYear;
        }

        if (months < 0) {
            years -= 1;
            months += 12;
        }

        System.out.println(cyan + "Your age: " + years + " years, " + months + " months, " + days + " days" + reset);
        System.out.println(red + "Your zodiac sign is " + getZodiacString(birthDay, birthMonth) + "." + reset);
        if (birthYear <= 1500) {
            System.out.println(red + " Wow... You must be a time traveler! " + reset);
            System.out.println(yellow + "Are you sure you were born in " + birthYear + "?" + reset);
        }
        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    private static String getZodiacString(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19))
            return "Aries";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20))
            return "Taurus";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20))
            return "Gemini";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22))
            return "Cancer";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22))
            return "Leo";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22))
            return "Virgo";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22))
            return "Libra";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21))
            return "Scorpio";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21))
            return "Sagittarius";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19))
            return "Capricorn";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18))
            return "Aquarius";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20))
            return "Pisces";
        return "Unknown";
    }

    private static int getDaysInMonth(int month, int year) {

        if (month == 2) {
            if (IsLeapYear(year))
                return 29;
            return 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    private static boolean IsLeapYear(int year) {
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0))
            return true;

        return false;
    }

    /*
     * private static int getDay(Scanner scan) {
     * while (true) {
     * int currentMonth = getCurrentMonth();
     * int currentYear = getCurrentYear();
     * 
     * String s = scan.nextLine().trim();
     * if (s.length() > 1 && s.charAt(0) == '0') {
     * System.out.println(red + "Leading zeros are not allowed for day." + reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * if (!s.matches("\\d+")) {
     * System.out.println(red + "Your day of birth must contain digits only." +
     * reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * 
     * int val;
     * try {
     * val = Integer.parseInt(s);
     * } catch (NumberFormatException e) {
     * System.out.println(red + "Invalid day value (number too large)." + reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * if (currentMonth == 2) {
     * int max = 0;
     * if (IsLeapYear(currentYear))
     * max = 29;
     * else
     * max = 28;
     * 
     * if (val <= 0 || val > max) {
     * System.out.println(red + "Your day of birth must be between 1 - " + max + "."
     * + reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * } else if (currentMonth == 4 || currentMonth == 6 || currentMonth == 9 ||
     * currentMonth == 11) {
     * if (val <= 0 || val > 30) {
     * System.out.println(red + "Your day of birth must be between 1 - 30." +
     * reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * } else {
     * if (val <= 0 || val > 31) {
     * System.out.println(red + "Your day of birth must be between 1 - 31." +
     * reset);
     * System.out.print(red + "Please enter your day of birth: " + reset);
     * continue;
     * }
     * }
     * return val;
     * }
     * 
     * }
     */

    // Day validator using provided birth month/year (prevents dates like 31/04)
    private static int getDay(Scanner scan, int month, int year) {
        while (true) {
            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.println(red + "Leading zeros are not allowed for day." + reset);
                System.out.print(red + "Please enter your day of birth: " + reset);
                continue;
            }
            if (!s.matches("\\d+")) {
                System.out.println(red + "Your day of birth must contain digits only." + reset);
                System.out.print(red + "Please enter your day of birth: " + reset);
                continue;
            }

            int val;
            try {
                val = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid day value (number too large)." + reset);
                System.out.print(red + "Please enter your day of birth: " + reset);
                continue;
            }

            int max;
            if (month == 2) {
                if (IsLeapYear(year)) {
                    max = 29;
                } else {
                    max = 28;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                max = 30;
            } else {
                max = 31;
            }

            if (val <= 0 || val > max) {
                System.out.println(red + "Your day of birth must be between 1 - " + max + "." + reset);
                System.out.print(red + "Please enter your day of birth: " + reset);
                continue;
            }

            return val;
        }
    }

    private static int getMonth(Scanner scan) {
        while (true) {

            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.println(red + "Leading zeros are not allowed for month." + reset);
                System.out.print(red + "Please enter your month of birth: " + reset);
                continue;
            }
            if (!s.matches("\\d+")) {
                System.out.println(red + "Your month of birth must contain digits only." + reset);
                System.out.print(red + "Please enter your month of birth: " + reset);
                continue;
            }

            int val;
            try {
                val = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid month value (number too large)." + reset);
                System.out.print(red + "Please enter your month of birth: " + reset);

                continue;
            }

            if (val <= 0 || val > 12) {
                System.out.println(red + "Your month of birth must be between 1 - 12." + reset);
                System.out.print(red + "Please enter your month of birth: " + reset);
                continue;
            }

            return val;
        }
    }

    private static int getYear(Scanner scan) {
        while (true) {

            int currentYear = getCurrentYear();
            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.println(red + "Leading zeros are not allowed for year." + reset);
                System.out.print(red + "Please enter your year of birth: " + reset);
                continue;
            }
            if (!s.matches("\\d+")) {
                System.out.println(red + "Your year of birth must contain digits only." + reset);
                System.out.print(red + "Please enter your year of birth: " + reset);
                continue;
            }

            int val;
            try {
                val = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid year value (number too large)." + reset);
                System.out.print(red + "Please enter your year of birth: " + reset);

                continue;
            }

            if (val < 0) {
                System.out.println(red + "Your year of birth cannot be negative." + reset);
                System.out.print(red + "Please enter your year of birth: " + reset);
                continue;
            }

            if (val > currentYear) {
                System.out
                        .println(red + "Your year of birth cannot be in the future (<= " + currentYear + ")." + reset);
                System.out.print(red + "Please enter your year of birth: " + reset);
                continue;
            }

            return val;
        }
    }

    private static int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    private static int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    private static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /* Option A — Task 2: Reverse the Words (recursive) */

    // Main function of Option A Task 2. Print reversed word.
    private static void reverseTheWords() {
        String input = getTextInput(SC);
        // String output = createReverseOutput(input);

        System.out.println(yellow + "\nOriginal Text:\n" + reset + input);
        // System.out.println(cyan + "Reversed Words Text:\n" + reset + output);

        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    // Get text input from user with using java.util.Scanner and return it.
    private static String getTextInput(Scanner scan) {
        String s;
        System.out.print(yellow + "Enter your text to be reversed: " + reset);
        while (true) {
            s = scan.nextLine();
            if (s.isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter your text: " + reset);
                continue;
            }
            return s;
        }

    }

    // After getting input from user, create reverse form and return it.
    private static String createReverseOutput() {
        return "";
    }

    // If input length >= 2, it can be reversed.
    private static boolean isReversed() {
        return true;
    }

    // Recursive helper for reversing a single word (no implementation)
    private static String reverseWordRec(String s) {
        return "";
    }

    /*
     * =============================
     * Option B — Secondary School
     * =============================
     */

    // Prime Numbers, Step by step Evaluation of Expression, and Return to Main
    // Menu.
    private static void subMenuOption2() {
        // TODO: Submenu loop for Option B
    }

    /* Option B — Task 1: Prime Numbers */

    // Main function of Option B Task 1. Print first and last 3 prime numbers and
    // execution times.
    private static void primeNumbers() {
        // TODO: Orchestrate n input, run sieves, measure times, print head/tail
    }

    // Get prime limit n from user using java.util.Scanner (n >= 12).
    private static int getPrimeNumberInput() {
        return 0;
    }

    // Create an ArrayList and push all prime numbers then return it. Print
    // execution time externally.
    private static ArrayList<Integer> sieveOfEratosthenes() {
        return new ArrayList<>();
    }

    private static ArrayList<Integer> sieveOfSundaram() {
        return new ArrayList<>();
    }

    private static ArrayList<Integer> sieveOfAtkin() {
        return new ArrayList<>();
    }

    /*
     * Option B — Task 2: Step-by-step Evaluation of Expression (recursive stages)
     */

    // Main function of Option B Task 2.
    private static void evaluationOfExpression() {
        // TODO: Read expression, validate, recursively reduce and print each stage
    }

    // Get expression input from user with using java.util.Scanner.
    private static String getExpressionInput() {
        return "";
    }

    // Check valid expression or not.
    private static boolean isValidExpression() {
        return false;
    }

    private static int calculateExpression(String expression) {
        return 0;
    }

    /*
     * =============================
     * Option C — High School
     * =============================
     */

    // Statistical Information about an Array, Distance between Two Arrays, and
    // Return to Main Menu.
    private static void subMenuOption3() {
        // TODO: Submenu loop for Option C
    }

    /* Option C — Task 1: Statistical Information about an Array */

    // Main function of Option C Task 1. Print all calculations.
    private static void statisticalInformation() {
        // TODO: Read size/elements, compute and print median/means
    }

    private static int arraySize() {
        return 0;
    }

    private static ArrayList<Integer> getElement() {
        return new ArrayList<>();
    }

    private static double calculateMedian() {
        return 0.0;
    }

    private static double calculateArithmeticMedian() {
        return 0.0;
    }

    private static double calculateGeometricMedian() {
        return 0.0;
    }

    private static double calculateHarmonicMedian() {
        return 0.0;
    } // !!! Should be computed recursively

    /* Option C — Task 2: Distance between Two Arrays */

    // Main function of Option C Task 2. Print all calculations.
    private static void distanceBetweenTwoArrays() {
        // TODO: Read dimension & arrays (0-9), compute Manhattan/Euclidean/Cosine
    }

    // Get dimension from user with using java.util.Scanner.
    private static int getDimension() {
        return 0;
    }

    // Check entered dimension is valid or not.
    private static boolean isValidDimension() {
        return false;
    }

    private static int calculateManhattanDistance() {
        return 0;
    }

    private static int calculateEuclideanDistance() {
        return 0;
    }

    private static int calculateCosineSimilarity() {
        return 0;
    }

    /*
     * =============================
     * Option D — University (Connect Four)
     * =============================
     */

    // (Stubs only; implement later)
    private static void subMenuOption4() {
        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[A] 5 x 4 Map " + reset);
            System.out.println(cyan + "[B] 6 x 5 Map " + reset);
            System.out.println(cyan + "[C] 7 x 6 Map " + reset);
            System.out.println(cyan + "[D] Return to the Main Menu " + reset);
            System.out.println(red + "********************************" + reset);
            System.out.println(green + "Please select an option to continue: " + reset);
            int choice = readMenuOption(SC, 'A', 'D');

            switch (choice) {
                case 'A':
                    ROWS = 5;
                    COLS = 4;
                    break;
                case 'B':
                    ROWS = 6;
                    COLS = 5;
                    break;
                case 'C':
                    ROWS = 7;
                    COLS = 6;
                    break;
                case 'D':
                    System.out.println(green + "\nReturning the main menu." + reset);
                    return;
            }
            clearScreen();
            break;
        }

        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[A] 1 Player " + reset);
            System.out.println(cyan + "[B] 2 Player " + reset);
            System.out.println(cyan + "[C] Return to the Main Menu " + reset);
            System.out.println(red + "********************************" + reset);
            System.out.println(green + "Please select an option to continue: " + reset);
            char choice = readMenuOption(SC, 'A', 'C');

            if (choice == 'C' || choice == 'c') {
                clearScreen();
                subMenuOption4();
                return;
            }

            if (choice == 'A' || choice == 'a') {
                singlePlayer = true;
            } else {
                singlePlayer = false;
            }

            startConnectFour();
            clearScreen();
            continue; 
        }

    }

    private static void startConnectFour() {
        board = new char[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = '.';
            }
        }

        currentPlayer = 'X';

        while (true) {
            clearScreen();
            renderBoard();

            if (singlePlayer) {
                if (currentPlayer == 'X') {
                    makeMovePlayer();
                } else {
                    makeMoveCPU();
                }
            } else {
                makeMovePlayer();
            }

            if (checkWin()) {
                clearScreen();
                renderBoard();
                System.out.println(green + "Player " + currentPlayer + " wins!" + reset);
                System.out.println(yellow + "Press ENTER to continue..." + reset);
                SC.nextLine();
                break;
            }

            if (checkDraw()) {
                clearScreen();
                renderBoard();
                System.out.println(yellow + "It's a draw." + reset);
                System.out.println(yellow + "Press ENTER to continue..." + reset);
                SC.nextLine();
                break;
            }

            switchPlayer();

        }
    }

    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    private static void renderBoard() {
        System.out.print(cyan);
        System.out.print("  ");
        for (int c = 1; c <= COLS; c++) {
            System.out.print(c);
            if (c < COLS) System.out.print("   ");
        }
        System.out.println(reset);

        System.out.print(yellow + "+");
        for (int i = 0; i < COLS; i++) System.out.print("---+");
        System.out.println(reset);

        // Draw rows
        for (int r = 0; r < ROWS; r++) {
            System.out.print(yellow + "|" + reset);
            for (int c = 0; c < COLS; c++) {
                char ch = board[r][c];
                if (ch == 'X') System.out.print(red + " X " + reset);
                else if (ch == 'O') System.out.print(green + " O " + reset);
                else System.out.print("   ");
                System.out.print(yellow + "|" + reset);
            }
            System.out.println();

            System.out.print(yellow + "+");
            for (int i = 0; i < COLS; i++) System.out.print("---+");
            System.out.println(reset);
        }
    }

    private static void makeMovePlayer() {
        while (true) {
            System.out.print(yellow + "Player " + currentPlayer + ", choose a column (1-" + COLS + "): " + reset);
            String choice = SC.nextLine().trim();

            int col;
            try {
                col = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid input. Please enter a number." + reset);
                continue;
            }

            if (col < 1 || col > COLS) {
                System.out.println(red + "Column out of range. Enter 1-" + COLS + "." + reset);
                continue;
            }

            int c = col - 1;
            if (board[0][c] != '.') {
                System.out.println(red + "That column is full. Choose another." + reset);
                continue;
            }

            for (int r = ROWS - 1; r >= 0; r--) {
                if (board[r][c] == '.') {
                    board[r][c] = currentPlayer;
                    return;
                }
            }
        }
    }

private static void makeMoveCPU() {
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        final char aiPlayer = currentPlayer;
        final char opponentPlayer;
        if (aiPlayer == 'X') {
            opponentPlayer = 'O';
        } else {
            opponentPlayer = 'X';
        }

        
        for (int col = 0; col < COLS; col++) {
            int row = dropRow(col);
            if (row == -1) continue;
            board[row][col] = aiPlayer;
            boolean win = winningAt(row, col, aiPlayer);
            board[row][col] = '.';
            if (win) {
                board[row][col] = aiPlayer;
                return;
            }
        }

        
        for (int col = 0; col < COLS; col++) {
            int row = dropRow(col);
            if (row == -1) continue;
            board[row][col] = opponentPlayer;
            boolean oppWin = winningAt(row, col, opponentPlayer);
            board[row][col] = '.';
            if (oppWin) {
                board[row][col] = aiPlayer;
                return;
            }
        }

        
        int[] pref = new int[COLS];
        int center = COLS / 2;
        int pidx = 0;
        for (int d = 0; pidx < COLS; d++) {
            int col;
            if (d % 2 == 0) {
                col = center + d / 2;
            } else {
                col = center - (d + 1) / 2;
            }
            if (col >= 0 && col < COLS) pref[pidx++] = col;
        }
        int[] candidates = new int[COLS];
        int n = 0;
        for (int c : pref) if (dropRow(c) != -1) candidates[n++] = c;
        if (n == 0) return; // no move
        int chosen = candidates[RNG.nextInt(n)];
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][chosen] == '.') { board[r][chosen] = aiPlayer; break; }
        }
    }

    
    private static int dropRow(int column) {
        if (column < 0 || column >= COLS) return -1;
        for (int row = ROWS - 1; row >= 0; row--) if (board[row][column] == '.') return row;
        return -1;
    }

    private static boolean inBounds(int row, int column) {
        return row >= 0 && row < ROWS && column >= 0 && column < COLS;
    }

    private static boolean winningAt(int row, int column, char player) {
        if (row < 0 || column < 0) return false;
        int[][] directions = { {0,1},{1,0},{1,1},{1,-1} };
        for (int[] direction : directions) {
            int count = 1, dr = direction[0], dc = direction[1];
            for (int k = 1; k < 4; k++) {
                int rr = row + dr*k, cc = column + dc*k;
                if (inBounds(rr,cc) && board[rr][cc] == player) count++; else break;
            }
            for (int k = 1; k < 4; k++) {
                int rr = row - dr*k, cc = column - dc*k;
                if (inBounds(rr,cc) && board[rr][cc] == player) count++; else break;
            }
            if (count >= 4) return true;
        }
        return false;
    }
    private static boolean isFull() {
        for (int col = 0; col < COLS; col++) if (board[0][col] == '.') return false;
        return true;
    }

    private static boolean checkWin() {
        char p = currentPlayer;
        if (p != 'X' && p != 'O') return false;

        // Horizontal
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == p && board[r][c+1] == p && board[r][c+2] == p && board[r][c+3] == p) return true;
            }
        }

        // Vertical
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r <= ROWS - 4; r++) {
                if (board[r][c] == p && board[r+1][c] == p && board[r+2][c] == p && board[r+3][c] == p) return true;
            }
        }

        // Diagonal down-right
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == p && board[r+1][c+1] == p && board[r+2][c+2] == p && board[r+3][c+3] == p) return true;
            }
        }

        // Diagonal up-right
        for (int r = 3; r < ROWS; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == p && board[r-1][c+1] == p && board[r-2][c+2] == p && board[r-3][c+3] == p) return true;
            }
        }

        return false;
    }

    private static boolean checkDraw() {
        return isFull();
    }

    /*
     * =============================
     * Program Entry Point
     * =============================
     */

    public static void main(String[] args) {
        // TODO: Wire everything together
        // Example flow (keep empty if you prefer):
        // welcomeMessage();
        // mainMenu();

        welcomeMessage();
        mainMenu();
    }
}
