import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    /* =============================
     *  Welcome & Main Menu TestBORAG
     * ============================= */
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
    System.out.println(cyan + " - Bora Görgün"+ reset);
    System.out.println(cyan + " - [Member 2]"+ reset);
    System.out.println(cyan + " - [Member 3]"+ reset);
    System.out.println(cyan + " - [Member 4]"+ reset);
    System.out.println();
    System.out.println(yellow + "Press ENTER to continue..." + reset);

    SC.nextLine();
    clearScreen();
    }

    private static void mainMenu() {
       while(true)
       {
        System.out.println("********************************");
        System.out.println("[1] Primary School");
        System.out.println("[2] Secondary School");
        System.out.println("[3] High Schooll");
        System.out.println("[4] University");
        System.out.println("[5] Terminate");
        System.out.println("********************************");
        System.out.println("Please select an option to continue: ");
        int choice = readInt(SC,1,5);
        
        switch (choice) {
            case 1:
                subMenuOption1();
                break;
            case 2:
                subMenuOption2();
                break;
            case 3:
                subMenuOption3();
                break;
            case 4:
                subMenuOption4();
                break;
            case 5:
                System.out.println(green+"\nTurning the program off...");
                System.out.println(red+"Thank you for using our program!");
                return; 
        }
        clearScreen();
      }
    }
private static int readInt(Scanner scan, int min, int max) {
    while (true) {
        String s = scan.nextLine().trim();  
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


    /* Optional helpers for menu I/O (no implementation yet) */
    private static void clearScreen() {
           System.out.print("\033[H\033[2J");
    System.out.flush();
    }

    /* =============================
     *  Option A — Primary School
     * ============================= */

    // Age and Zodiac Sign Detection, Reverse the Words, and Return to Main Menu.
    private static void subMenuOption1() {
        // TODO: Submenu loop for Option A
    }

    /* Option A — Task 1: Age and Zodiac Sign Detection */

    // Main function of Option A Task 1. Print age and zodiac.
    private static void ageAndZodiacSignDetection() {
        // TODO: Orchestrate input, calculations, and formatted output
    }

    // Get date of birth from user using java.util.Scanner.
    private static int getDay() { return 0; }
    private static int getMonth() { return 0; }
    private static int getYear() { return 0; }

    // Get current date using java.util.Calendar package.
    private static int getCurrentDay() { return 0; }
    private static int getCurrentMonth() { return 0; }
    private static int getCurrentYear() { return 0; }

    // Calculate age with parameters that are day, month and year.
    private static int calculateAge(String day, String month, String year) { return 0; }

    // Calculate zodiac with parameters that are day, month and year.
    private static String calculateZodiac(String day, String month, String year) { return ""; }

    /* Option A — Task 2: Reverse the Words (recursive) */

    // Main function of Option A Task 2. Print reversed word.
    private static void reverseTheWords() {
        // TODO: Orchestrate input, recursion, and output
    }

    // Get text input from user with using java.util.Scanner and return it.
    private static String getTextInput() { return ""; }

    // After getting input from user, create reverse form and return it.
    private static String createReverseOutput() { return ""; }

    // If input length >= 2, it can be reversed.
    private static boolean isReversed() { return true; }

    // Recursive helper for reversing a single word (no implementation)
    private static String reverseWordRec(String s) { return ""; }

    /* =============================
     *  Option B — Secondary School
     * ============================= */

    // Prime Numbers, Step by step Evaluation of Expression, and Return to Main Menu.
    private static void subMenuOption2() {
        // TODO: Submenu loop for Option B
    }

    /* Option B — Task 1: Prime Numbers */

    // Main function of Option B Task 1. Print first and last 3 prime numbers and execution times.
    private static void primeNumbers() {
        // TODO: Orchestrate n input, run sieves, measure times, print head/tail
    }

    // Get prime limit n from user using java.util.Scanner (n >= 12).
    private static int getPrimeNumberInput() { return 0; }

    // Create an ArrayList and push all prime numbers then return it. Print execution time externally.
    private static ArrayList<Integer> sieveOfEratosthenes() { return new ArrayList<>(); }
    private static ArrayList<Integer> sieveOfSundaram() { return new ArrayList<>(); }
    private static ArrayList<Integer> sieveOfAtkin() { return new ArrayList<>(); }

    /* Option B — Task 2: Step-by-step Evaluation of Expression (recursive stages) */

    // Main function of Option B Task 2.
    private static void evaluationOfExpression() {
        // TODO: Read expression, validate, recursively reduce and print each stage
    }

    // Get expression input from user with using java.util.Scanner.
    private static String getExpressionInput() { return ""; }

    // Check valid expression or not.
    private static boolean isValidExpression() { return false; }

    private static int calculateExpression(String expression) { return 0; }

    /* =============================
     *  Option C — High School
     * ============================= */

    // Statistical Information about an Array, Distance between Two Arrays, and Return to Main Menu.
    private static void subMenuOption3() {
        // TODO: Submenu loop for Option C
    }

    /* Option C — Task 1: Statistical Information about an Array */

    // Main function of Option C Task 1. Print all calculations.
    private static void statisticalInformation() {
        // TODO: Read size/elements, compute and print median/means
    }

    private static int arraySize() { return 0; }
    private static ArrayList<Integer> getElement() { return new ArrayList<>(); }

    private static double calculateMedian() { return 0.0; }
    private static double calculateArithmeticMedian() { return 0.0; }
    private static double calculateGeometricMedian() { return 0.0; }
    private static double calculateHarmonicMedian() { return 0.0; } // !!! Should be computed recursively

    /* Option C — Task 2: Distance between Two Arrays */

    // Main function of Option C Task 2. Print all calculations.
    private static void distanceBetweenTwoArrays() {
        // TODO: Read dimension & arrays (0-9), compute Manhattan/Euclidean/Cosine
    }

    // Get dimension from user with using java.util.Scanner.
    private static int getDimension() { return 0; }

    // Check entered dimension is valid or not.
    private static boolean isValidDimension() { return false; }

    private static int calculateManhattanDistance() { return 0; }
    private static int calculateEuclideanDistance() { return 0; }
    private static int calculateCosineSimilarity() { return 0; }

    /* =============================
     *  Option D — University (Connect Four)
     * ============================= */

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

    private static boolean checkWin() { return false; }
    private static boolean checkDraw() { return false; }

    /* =============================
     *  Program Entry Point
     * ============================= */

    public static void main(String[] args) {
        // TODO: Wire everything together
        // Example flow (keep empty if you prefer):
        // welcomeMessage();
        // mainMenu();

         welcomeMessage();
         mainMenu();
    }
}
