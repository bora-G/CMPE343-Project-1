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
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Primary School"+reset);
            System.out.println(cyan+"[B] Secondary School"+reset);
            System.out.println(cyan+"[C] High School"+reset);
            System.out.println(cyan+"[D] University"+reset);
            System.out.println(cyan+"[E] Terminate"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.println(green+"Please select an option to continue: "+reset);
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
                System.out.print(red + "Input cannot be empty. Please enter a letter between " + min + " and " + max + ": " + reset);
                continue;
            }

            if (s.length() > 1){
                System.out.print(red + "Invalid input. Please enter a single letter: " + reset);
                continue;

            }

            char choice = s.charAt (0);

            if (choice < min || choice > max) {
                System.out.print(red + "Please enter a valid option letter between [" + min + "] and [" + max + "]: " + reset);
                continue;
            }
            return choice;
        }
    }

    
    /* 
    private static int readInt(Scanner scan, int min, int max) {
        while (true) {
            String s = scan.nextLine().trim();

            if (s.isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter a number between " + min + " and " + max + ": " + reset);
                continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(red + "Please enter a number between " + min + " and " + max + ": " + reset);
                continue;
            }

            int val;
            try {
                val = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print(red + "Please enter a number between " + min + " and " + max + ": " + reset);
                continue;
            }

            if (val < min || val > max) {
                System.out.print(red + "Please enter a number between " + min + " and " + max + ": " + reset);
                continue;
            }

            return val;
        }
    }
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
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Calculate Age and Zodiac Sign"+reset);
            System.out.println(cyan+"[B] Reverse the Words"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.println(green+"Please select an option to continue: "+reset);
            int choice = readMenuOption(SC, 'A', 'C');

            switch (choice) {
                case 'A':
                    ageAndZodiacSignDetection();
                    break;
                case 'B':
                    reverseTheWords();
                    break;
                case 'C':
                    System.out.println(green + "\nReturning the main menu."+reset);
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
        if(birthYear<=1500)
        {
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
    private static int getDay(Scanner scan) {
        while (true) {
            int currentMonth = getCurrentMonth();
            int currentYear = getCurrentYear();

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
            if (currentMonth == 2) {
                int max = 0;
                if (IsLeapYear(currentYear))
                    max = 29;
                else
                    max = 28;

                if (val <= 0 || val > max) {
                    System.out.println(red + "Your day of birth must be between 1 - " + max + "." + reset);
                    System.out.print(red + "Please enter your day of birth: " + reset);
                    continue;
                }
            } else if (currentMonth == 4 || currentMonth == 6 || currentMonth == 9 || currentMonth == 11) {
                if (val <= 0 || val > 30) {
                    System.out.println(red + "Your day of birth must be between 1 - 30." + reset);
                    System.out.print(red + "Please enter your day of birth: " + reset);
                    continue;
                }
            } else {
                if (val <= 0 || val > 31) {
                    System.out.println(red + "Your day of birth must be between 1 - 31." + reset);
                    System.out.print(red + "Please enter your day of birth: " + reset);
                    continue;
                }
            }
            return val;
        }

    }
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
                max = IsLeapYear(year) ? 29 : 28;
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
                System.out.println(red + "Your year of birth cannot be in the future (<= " + currentYear + ")." + reset);
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
        //String output = createReverseOutput(input);
        
        System.out.println(yellow + "\nOriginal Text:\n" + reset + input);
        //System.out.println(cyan + "Reversed Words Text:\n" + reset + output);

        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    // Get text input from user with using java.util.Scanner and return it.
    private static String getTextInput(Scanner scan) {
        String s;
        System.out.print(yellow + "Enter your text to be reversed: " + reset);
        while (true){
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
        // TODO: Board size (5x4, 6x5, 7x6) and mode (1P vs CPU / 2P)
    }

    private static void startConnectFour() {
        // TODO: Main game loop, render board each move
    }

    private static void renderBoard() {
        // TODO: Print the board to console
    }

    private static void makeMovePlayer() {
        // TODO: Player move with column validation
    }

    private static void makeMoveCPU() {
        // TODO: Random/AI move (Minimax/Alpha-Beta optional)
    }

    private static boolean checkWin() {
        return false;
    }

    private static boolean checkDraw() {
        return false;
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