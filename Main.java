import java.time.LocalDate;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Locale; 
import java.util.Scanner;

public class Main {

    /*
     * =============================
     * Global Constants & Scanner
     * =============================
     */
    
    private static final java.util.Scanner SC = new java.util.Scanner(System.in).useLocale(Locale.US);
    
    // ANSI escape codes for colored console output
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m"; 
    
    
    //For Connect4 game
    private static int ROWS, COLS;
    private static char[][] board;
    private static char currentPlayer; 
    private static boolean singlePlayer; 
    private static final java.util.Random RNG = new java.util.Random();

    // Shared data structure for Option C
    private static ArrayList<Double> arrC = new ArrayList<>();


    /*
     * =============================
     * Welcome & Main Menu Logic
     * =============================
     */

    private static void welcomeMessage() {
        clearScreen();
        System.out.println(YELLOW +
                " █████  ███   █████       ████           \n" +
                "░░███  ░███  ░░███        ░░███           \n" +
                " ░███  ░███   ░███   ██████  ░███   ██████   ██████  █████████████   ██████  \n" +
                " ░███  ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███ \n" +
                " ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████  \n" +
                "  ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░   \n" +
                "    ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████  \n" +
                "     ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░   \n" +
                RESET);
        System.out.println(GREEN + "Welcome to CMPE343 Project 1" + RESET);
        System.out.println(GREEN + "-----------------------------------------------" + RESET);
        System.out.println(GREEN + "Group Members:" + RESET);
        System.out.println(RED +
" ____    ___   ____    ____        ____   ___   ____    ____  __ __  ____  \n" +
"|    \\  /   \\ |    \\  /    |      /    | /   \\ |    \\  /    ||  |  ||    \\ \n" +
"|  o  )|     ||  D  )|  o  |     |   __||     ||  D  )|   __||  |  ||  _  |\n" +
"|     ||  O  ||    / |     |     |  |  ||  O  ||    / |  |  ||  |  ||  |  |\n" +
"|  O  ||     ||    \\ |  _  |     |  |_ ||     ||    \\ |  |_ ||  :  ||  |  |\n" +
"|     ||     ||  .  \\|  |  |     |     ||     ||  .  \\|     ||     ||  |  |\n" +
"|_____| \\___/ |__|\\_||__|__|     |___,_| \\___/ |__|\\_||___,_| \\__,_||__|__|\n"
+ RESET);



        System.out.println(CYAN +
"    __   ____  ____         ___  ____    _____  ____  ____  \n" +
"   /  ] /    ||    \\       /  _]|    \\  / ___/ /    ||    \\ \n" +
"  /  / |  o  ||  _  |     /  [_ |  D  )(   \\_ |  o  ||  _  |\n" +
" /  /  |     ||  |  |    |    _]|    /  \\__  ||     ||  |  |\n" +
"/   \\_ |  _  ||  |  |    |   [_ |    \\  /  \\ ||  _  ||  |  |\n" +
"\\     ||  |  ||  |  |    |     ||  .  \\ \\    ||  |  ||  |  |\n" +
" \\____||__|__||__|__|    |_____||__|\\_|  \\___||__|__||__|__|\n"
+ RESET);

        System.out.println(CYAN +
" ___ ___    ___  _        ___  __  _       _____  ____  ___    ____  __  _  ____ \n" +
"|   |   |  /  _]| |      /  _]|  |/ ]     / ___/ /    ||   \\  |    ||  |/ ]|    |\n" +
"| _   _ | /  [_ | |     /  [_ |  ' /     (   \\_ |  o  ||    \\  |  | |  ' /  |  | \n" +
"|  \\_/  ||    _]| |___ |    _]|    \\      \\__  ||     ||  D  | |  | |    \\  |  | \n" +
"|   |   ||   [_ |     ||   [_ |     \\     /  \\ ||  _  ||     | |  | |     \\ |  | \n" +
"|   |   ||     ||     ||     ||  .  |     \\    ||  |  ||     | |  | |  .  | |  | \n" +
"|___|___||_____||_____||_____||__|\\_|      \\___||__|__||_____||____||__|\\_||____|\n"
+ RESET);

        System.out.println(CYAN +
" ___ ___  ____  __  _   ____  ____  _          __  _   ____  ____    ____    __   ____    ___  ____  \n" +
"|   |   ||    ||  |/ ] /    ||    || |        |  |/ ] /    ||    \\  /    |  /  ] /    |  /  _]|    \\ \n" +
"| _   _ | |  | |  ' / |  o  | |  | | |        |  ' / |  o  ||  D  )|  o  | /  / |  o  | /  [_ |  D  )\n" +
"|  \\_/  | |  | |    \\ |     | |  | | |___     |    \\ |     ||    / |     |/  /  |     ||    _]|    / \n" +
"|   |   | |  | |     \\|  _  | |  | |     |    |     ||  _  ||    \\ |  _  /   \\_ |  _  ||   [_ |    \\ \n" +
"|   |   | |  | |  .  ||  |  | |  | |     |    |  .  ||  |  ||  .  \\|  |  \\     ||  |  ||     ||  .  \\\n" +
"|___|___||____||__|\\_||__|__||____||_____|    |__|\\_||__|__||__|\\_||__|__|\\____||__|__||_____||__|\\_|\n"
+ RESET);

        System.out.println();
        System.out.println(YELLOW + "Press ENTER to continue..." + RESET);
        SC.nextLine(); 
    }

    private static void mainMenu() {
        while (true) {
            clearScreen(); 
            System.out.println(RED+"********************************"+RESET);
            System.out.println(CYAN+"[A] Primary School"+RESET);
            System.out.println(CYAN+"[B] Secondary School"+RESET);
            System.out.println(CYAN+"[C] High School"+RESET);
            System.out.println(CYAN+"[D] University"+RESET);
            System.out.println(CYAN+"[E] Terminate"+RESET);
            System.out.println(RED+"********************************"+RESET);
            System.out.print(GREEN+"Please select an option to continue: "+RESET);
            char choice = readMenuOption(SC, 'A', 'E'); 

            clearScreen(); 

            switch (choice) {
                case 'A' -> subMenuOption1(); 
                case 'B' -> subMenuOption2(); 
                case 'C' -> subMenuOption3(); 
                case 'D' -> subMenuOption4(); 
                case 'E' -> { 
                    System.out.println(GREEN + "\nTurning the program off..." + RESET);
                    System.out.println(RED + "Thank you for using our program!" + RESET);
                    return; 
                }
            }
        }
    }

    /**
     * Helper method to read a single menu option character (A-Z) with validation.
     */
    private static char readMenuOption(Scanner scan, char min, char max) {
        while (true) {
            String s = scan.nextLine().trim().toUpperCase(); 

            if (s.isEmpty()) {
                System.out.print(RED + "Input cannot be empty. Please enter a letter between " + min + " and " + max + ": " + RESET);
                continue; 
            }
            if (s.length() > 1){
                System.out.print(RED + "Invalid input. Please enter a single letter: " + RESET);
                continue; 
            }

            char choice = s.charAt(0); 
            if (choice < min || choice > max) {
                System.out.print(RED + "Please enter a valid option letter between [" + min + "] and [" + max + "]: " + RESET);
                continue; 
            }
            return choice; 
        }
    }

    /**
     * Helper method to read an integer within a specific range [min, max] with validation.
     */
    private static int readInt(Scanner scan, int min, int max) {
        while (true) {
            String s = scan.nextLine().trim(); 

            if (s.isEmpty()) {
                System.out.print(RED + "Input cannot be empty. Please enter a number between " + min + " and " + max + ": " + RESET);
                continue; 
            }
            if (!s.matches("\\d+")) {
                System.out.print(RED + "Invalid input. Please enter a number between " + min + " and " + max + ": " + RESET);
                continue; 
            }
            
            try {
                int val = Integer.parseInt(s); 
                if (val < min || val > max) {
                    System.out.print(RED + "Number out of range. Please enter between " + min + " and " + max + ": " + RESET);
                    continue; 
                }
                return val; 
            } catch (NumberFormatException e) {
                System.out.print(RED + "Invalid number format (too large). Please enter between " + min + " and " + max + ": " + RESET);
            }
        }
    }

    /* Helper method to clear the console screen */
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
        while (true) {
            clearScreen(); 
            System.out.println(RED+"********************************"+RESET);
            System.out.println(CYAN+"[A] Calculate Age and Zodiac Sign"+RESET);
            System.out.println(CYAN+"[B] Reverse the Words"+RESET);
            System.out.println(CYAN+"[C] Return To Main Menu"+RESET);
            System.out.println(RED+"********************************"+RESET);
            System.out.print(GREEN+"Please select an option to continue: "+RESET);
            char choice = readMenuOption(SC, 'A', 'C');

            clearScreen();

            switch (choice) {
                case 'A' -> ageAndZodiacSignDetection();
                case 'B' -> reverseTheWords();
                case 'C' -> { 
                    System.out.println(GREEN + "\nReturning the main menu."+RESET);
                    System.out.println(YELLOW + "Press ENTER..." + RESET);
                    SC.nextLine(); 
                    return; 
                }
            }
        }
    }

    /* Option A - Task 1: Age and Zodiac Calculation */
    private static void ageAndZodiacSignDetection() {
        while (true) {
            System.out.println(CYAN + "--- Age and Zodiac Sign Calculator ---" + RESET);
            System.out.print(YELLOW + "Please enter your year of birth: " + RESET);
            int birthYear = getYear(SC); 

            System.out.print(YELLOW + "Please enter your month of birth (1-12): " + RESET);
            int birthMonth = getMonth(SC); 

            System.out.print(YELLOW + "Please enter your day of birth: " + RESET);
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
                if (prevMonth == 0) { prevMonth = 12; prevYear -= 1; } 
                int daysInPrevMonth = getDaysInMonth(prevMonth, prevYear); 
                
                days += daysInPrevMonth; 
                months -= 1; 

                tempMonth = prevMonth;
                tempYear = prevYear;
            }

            if (months < 0) {
                years -= 1; 
                months += 12; 
            }

            System.out.println(CYAN + "Your age: " + years + " years, " + months + " months, " + days + " days" + RESET);
            System.out.println(RED + "Your zodiac sign is " + getZodiacString(birthDay, birthMonth) + "." + RESET);
            
            if (birthYear <= 1500) {
                System.out.println(RED + " Wow... You must be a time traveler! " + RESET);
                System.out.println(YELLOW + "Are you sure you were born in " + birthYear + "?" + RESET);
            }
            
            System.out.print(YELLOW + "\nCalculate again? (Y/N): " + RESET);
            char repeatChoice = readMenuOption(SC, 'N', 'Y'); 
            if (repeatChoice == 'N') {
                break; 
            }
            clearScreen(); 
        }
    }

    private static String getZodiacString(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Aries";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Taurus";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Gemini";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Cancer";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Leo";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Virgo";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Libra";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Scorpio";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Sagittarius";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Capricorn";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Aquarius";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "Pisces";
        return "Unknown"; 
    }

    private static int getDaysInMonth(int month, int year) {
        if (month == 2) return IsLeapYear(year) ? 29 : 28; 
        else if (month == 4 || month == 6 || month == 9 || month == 11) return 30; 
        else return 31; 
    }

    private static boolean IsLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    private static int getDay(Scanner scan, int month, int year) {
        int maxDay = getDaysInMonth(month, year); 
        while (true) {
            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.print(RED + "Leading zeros are not allowed. Enter day (1-" + maxDay + "): " + RESET); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(RED + "Digits only. Enter day (1-" + maxDay + "): " + RESET); continue;
            }
            try {
                int val = Integer.parseInt(s);
                if (val < 1 || val > maxDay) {
                    System.out.print(RED + "Day must be between 1 and " + maxDay + ": " + RESET); continue;
                }
                return val; 
            } catch (NumberFormatException e) {
                System.out.print(RED + "Invalid number format. Enter day (1-" + maxDay + "): " + RESET);
            }
        }
    }

    private static int getMonth(Scanner scan) {
        while (true) {
            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                 System.out.print(RED + "Leading zeros are not allowed. Enter month (1-12): " + RESET); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(RED + "Digits only. Enter month (1-12): " + RESET); continue;
            }
            try {
                int val = Integer.parseInt(s);
                if (val < 1 || val > 12) {
                    System.out.print(RED + "Month must be between 1 and 12: " + RESET); continue;
                }
                return val; 
            } catch (NumberFormatException e) {
                 System.out.print(RED + "Invalid number format. Enter month (1-12): " + RESET);
            }
        }
    }

    private static int getYear(Scanner scan) {
        int currentYear = getCurrentYear();
        while (true) {
            String s = scan.nextLine().trim();
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.print(RED + "Leading zeros are not allowed. Enter year (<= " + currentYear + "): " + RESET); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(RED + "Digits only. Enter year (<= " + currentYear + "): " + RESET); continue;
            }
            try {
                int val = Integer.parseInt(s);
                if (val < 0) {
                    System.out.print(RED + "Year cannot be negative. Enter year: " + RESET); continue;
                }
                if (val > currentYear) {
                    System.out.print(RED + "Year cannot be in the future (<= " + currentYear + "): " + RESET); continue;
                }
                return val; 
            } catch (NumberFormatException e) {
                 System.out.print(RED + "Invalid number format. Enter year (<= " + currentYear + "): " + RESET);
            }
        }
    }

    private static int getCurrentDay() { return LocalDate.now().getDayOfMonth(); }
    private static int getCurrentMonth() { return LocalDate.now().getMonthValue(); }
    private static int getCurrentYear() { return LocalDate.now().getYear(); }

    /* Option A — Task 2: Reverse the Words (recursively) */
    
    private static void reverseTheWords() {
        while(true) {
            System.out.println(CYAN + "--- Reverse Words in Text ---" + RESET);
            String input = getTextInput(SC); 

            if (!isReversed(input)) {
                System.out.println(RED + "Input must contain meaningful characters." + RESET);
            } else {
                String output = createReverseOutput(input);
                System.out.println(YELLOW + "\nOriginal Text:\n" + RESET + input);
                System.out.println(CYAN + "Reversed Words Text:\n" + RESET + output);
            }
            
            System.out.print(YELLOW + "\nReverse another text? (Y/N): " + RESET);
            char repeatChoice = readMenuOption(SC, 'N', 'Y'); 
            if (repeatChoice == 'N') {
                break; 
            }
            clearScreen(); 
        }
    }
    
    private static String getTextInput(Scanner scan) {
        System.out.print(GREEN + "Enter text (Turkish characters allowed): " + RESET);
        while (true) {
            String s = scan.nextLine();
            if (s.trim().isEmpty()) {
                System.out.print(RED + "Input cannot be empty. Please enter text: " + RESET);
                continue; 
            }
            return s; 
        }
    }
    
    /**
     * Core logic for reversing words >= 2 chars.
     * Iterates string, identifying 'word blocks' (letters/apostrophes) 
     * and 'non-word blocks' (digits/punctuation/space).
     */
    private static String createReverseOutput(String s) {
        String result = ""; 
        int i = 0; 
        
        while (i < s.length()) {
            char startChar = s.charAt(i);

            if (Character.isLetter(startChar) || startChar == '\'') {
                int j = i;
                while (j < s.length() && (Character.isLetter(s.charAt(j)) || s.charAt(j) == '\'')) {
                    j++;
                }
                String word = s.substring(i, j); 
                
                if (word.length() >= 2) {
                    result += reverseWordRec(word); // Append reversed word
                } else {
                    result += word; // Append short word as is
                }
                i = j; 
            } else { 
                int j = i;
                while (j < s.length() && (!Character.isLetter(s.charAt(j)) && s.charAt(j) != '\'')) {
                    j++;
                }
                String nonWord = s.substring(i, j); 
                result += nonWord; // Append non-word block as is
                i = j; 
            }
        }
        return result; 
    }

    private static boolean isReversed(String s) {
        return s != null && s.trim().length() >= 2;
    }
    
    /**
     * Recursive helper method to reverse a single string (word).
     * REQUIRED by the project description for Option A - Task 2.
     */
    private static String reverseWordRec(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return reverseWordRec(s.substring(1)) + s.charAt(0);
    }

    /*
     * =============================
     * Option B — Secondary School
     * =============================
     */
   

/**
 *author MikailKaracaer
 * Shows the Secondary School submenu and routes to the selected task:
 * A) Prime Numbers, B) Step-by-step Evaluation of Expression, or
 * C) Return to the Main Menu.
 */
    private static void subMenuOption2() {
        while (true) {
            clearScreen(); 
            System.out.println(RED+"********************************"+RESET);
            System.out.println(CYAN+"[A] Prime Numbers"+RESET);
            System.out.println(CYAN+"[B] Step-by-step Evaluation of Expression"+RESET);
            System.out.println(CYAN+"[C] Return To Main Menu"+RESET);
            System.out.println(RED+"********************************"+RESET);
            System.out.print(GREEN+"Please select an option to continue: "+RESET);
            char choice = readMenuOption(SC, 'A', 'C');

            clearScreen();

            switch (choice) {
                case 'A' -> primeNumbers(); 
                case 'B' -> evaluationOfExpression(); 
                case 'C' -> { 
                    System.out.println(GREEN + "\nReturning the main menu."+RESET);
                    System.out.println(YELLOW + "Press ENTER..." + RESET);
                    SC.nextLine(); 
                    return; 
                }
            }
        }
    }

    /* Option B — Task 1: Prime Numbers using Sieve Algorithms */
/**
 * Reads an upper limit n (>= 12) and computes all primes up to n using
 * Eratosthenes, Sundaram, and Atkin sieves. Prints a brief summary and timing
 * for each method.
 */
    private static void primeNumbers() {
        while(true) {
            System.out.println(CYAN + "--- Prime Numbers (Sieve Algorithms) ---" + RESET);
            int n = getPrimeNumberInput(); 
            
            System.out.println(YELLOW + "\nCalculating primes up to " + n + "..." + RESET);

            long startE = System.nanoTime(); 
            int[] primesE = sieveEratosthenes(n); 
            long timeE = System.nanoTime() - startE; 
            printPrimeSummary("Eratosthenes", primesE, timeE); 
            
            long startS = System.nanoTime();
            int[] primesS = sieveSundaram(n); 
            long timeS = System.nanoTime() - startS;
            printPrimeSummary("Sundaram", primesS, timeS);
            
            long startA = System.nanoTime();
            int[] primesA = sieveAtkin(n); 
            long timeA = System.nanoTime() - startA;
            printPrimeSummary("Atkin", primesA, timeA);
            
            System.out.print(YELLOW + "\nCalculate for another limit? (Y/N): " + RESET);
            char repeatChoice = readMenuOption(SC, 'N', 'Y');
            if (repeatChoice == 'N') {
                break; 
            }
             clearScreen(); 
        }
    }

/**
 * Prompts for and validates the upper limit n for prime computations.
 *
 * @return a validated integer n (n >= 12)
 */
    private static int getPrimeNumberInput() {
        System.out.print(GREEN + "Enter the upper limit n (must be >= 12): " + RESET);
        return readInt(SC, 12, Integer.MAX_VALUE); 
    }
    
    /**
 * Prints a short summary for a prime-generation method: elapsed time,
 * first three primes, and last two primes (if available).
 *
 * @param name  method name (e.g., "Eratosthenes")
 * @param p     the array of primes found
 * @param nanos elapsed time in nanoseconds
 */
    private static void printPrimeSummary(String name, int[] p, long nanos) {
        System.out.printf("Method: %s | Time: %.3f ms | ", name, nanos / 1_000_000.0); 
        if (p.length == 0) { 
            System.out.println("No primes found."); 
            return; 
        }

        String first3 = "";
        for(int i = 0; i < p.length && i < 3; i++) {
            first3 += p[i]; 
            if (i < 2 && i < p.length - 1) first3 += ","; 
        }

        String last2 = "";
        if (p.length >= 2) { 
            last2 = p[p.length-2] + "," + p[p.length-1]; 
        } else if (p.length == 1) { 
            last2 = String.valueOf(p[0]); 
        }
        
        System.out.printf("First3: %s | Last2: %s%n", first3, last2); 
    }
    
/**
 * Generates prime numbers in [2, n] using the Sieve of Eratosthenes.
 *
 * @param n upper bound (returns an empty array if n < 2)
 * @return primes in ascending order
 */
    private static int[] sieveEratosthenes(int n) {
        if (n < 2) return new int[0]; 
        boolean[] isPrime = new boolean[n + 1]; 
        Arrays.fill(isPrime, true); 
        isPrime[0] = isPrime[1] = false; 
        
        for (int p = 2; (long)p * p <= n; p++) { 
            if (isPrime[p]) { 
                for (long m = (long)p * p; m <= n; m += p) { 
                    isPrime[(int)m] = false;
                }
            }
        }
        
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) count++;
        }
        
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes[index++] = i;
        }
        return primes; 
    }
    
/**
 * Generates prime numbers in [2, n] using the Sieve of Sundaram.
 * Treats 2 separately and filters the remaining odd numbers.
 *
 * @param n upper bound (returns an empty array if n < 2)
 * @return primes in ascending order
 */
    private static int[] sieveSundaram(int n) {
        if (n < 2) return new int[0]; 
        int k = (n - 1) / 2; 
        boolean[] marked = new boolean[k + 1]; 
        
        for (int i = 1; i <= k; i++) {
            for (int j = i; ; j++) { 
                 int index = i + j + 2 * i * j;
                 if (index > k) break; 
                 marked[index] = true; 
            }
        }
        
        int count = 1; // For '2'
        for (int i = 1; i <= k; i++) {
            if (!marked[i]) count++; 
        }
        
        int[] primes = new int[count];
        primes[0] = 2; 
        int index = 1;
        for (int i = 1; i <= k; i++) {
            if (!marked[i]) primes[index++] = 2 * i + 1; 
        }
        return primes; 
    }
    
/**
 * Generates prime numbers in [2, limit] using the Sieve of Atkin.
 * Applies quadratic residue toggling and square-factor elimination.
 *
 * @param limit upper bound (returns an empty array if limit < 2)
 * @return primes in ascending order
 */
    private static int[] sieveAtkin(int limit) {
        if (limit < 2) return new int[0]; 
        boolean[] sieve = new boolean[limit + 1]; 
        int sqrt = (int)Math.sqrt(limit); 
        
        for (int x = 1; x <= sqrt; x++) {
            int x2 = x * x;
            for (int y = 1; y <= sqrt; y++) {
                int y2 = y * y;
                int n;
                
                n = 4 * x2 + y2; 
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) sieve[n] ^= true; 
                
                n = 3 * x2 + y2; 
                if (n <= limit && n % 12 == 7) sieve[n] ^= true; 
                
                n = 3 * x2 - y2; 
                if (x > y && n <= limit && n % 12 == 11) sieve[n] ^= true; 
            }
        }
        
        for (int r = 5; r <= sqrt; r++) {
            if (sieve[r]) {
                for (int m = r * r; m <= limit; m += r * r) {
                    sieve[m] = false;
                }
            }
        }
        
        int count = 0;
        if (limit >= 2) count++; // 2
        if (limit >= 3) count++; // 3
        for (int a = 5; a <= limit; a++) {
            if (sieve[a]) count++; 
        }
        
        int[] primes = new int[count];
        int index = 0;
        if (limit >= 2) primes[index++] = 2; 
        if (limit >= 3) primes[index++] = 3; 
        for (int a = 5; a <= limit; a++) {
            if (sieve[a]) primes[index++] = a; 
        }
        return primes; 
    }

    /* Option B — Task 2: Step-by-step Evaluation of Expression (recursively) */
/**
 * Reads an arithmetic expression (digits, +, -, x/×, : , parentheses),
 * validates its structure, then evaluates it step-by-step while printing
 * each intermediate reduction. Prints clear errors on invalid input or
 * division by zero.
 */
    private static void evaluationOfExpression() {
        while(true) {
            System.out.println(CYAN + "--- Step-by-step Expression Evaluator ---" + RESET);
            System.out.print(GREEN + "Enter expression using digits, +, -, x, :, (, ): " + RESET);
            
            String expr = null; 
            while (true) { 
                expr = SC.nextLine();
                if (!exprIsValid(expr)) { 
                    System.out.println(RED + "Invalid expression format. Please re-enter: " + RESET);
                    System.out.print(GREEN + "Enter expression: " + RESET);
                    continue; 
                }
                break; 
            }

            try {
                exprEvaluate(expr); 
            } catch (ArithmeticException e) {
                System.out.println(RED + "Error: Division by zero occurred during evaluation." + RESET);
            } catch (Exception e) {
                 System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
            }

            System.out.print(YELLOW + "\nEvaluate another expression? (Y/N): " + RESET);
            char repeatChoice = readMenuOption(SC, 'N', 'Y');
            if (repeatChoice == 'N') {
                break; 
            }
             clearScreen(); 
        }
    }
    
    /**
     * Validates the structure and characters of the mathematical expression.
     */
    private static boolean exprIsValid(String s) {
        if (s == null || s.trim().isEmpty()) return false; 
        
        String trimmedInput = s.trim();
        if (trimmedInput.equals("+0") || trimmedInput.equals("-0")) {
            return false;
        }

        if (!s.matches("[0-9\\s+\\-xX×:/*()]+")) return false; 

        String t = exprNormalize(s); 
        if (t.isEmpty()) return false;
        
        if (isOp(t.charAt(0)) && t.charAt(0) != '-') return false; 
        if (isOp(t.charAt(t.length() - 1))) return false;
        
        // Rejects patterns like ")(" or "5(" or ")3"
        if (t.contains(")(") || t.matches(".*\\d\\(.*") || t.matches(".*\\)\\d.*")) return false;

        int balance = 0;
        for (int i=0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false; 
        }
        if (balance != 0) return false; 

        for (int i = 1; i < t.length(); i++) {
            char current = t.charAt(i);
            char previous = t.charAt(i - 1);
            if (isOp(current) && current != '-') { 
                 if (isOp(previous) || previous == '(') return false; 
            } else if (isOp(current) && current == '-') { 
                 if (isOp(previous) && previous != '(') return false; 
            }
        }
        return true; 
    }

    private static String exprNormalize(String s) {
        return s.replace('×','*') 
                .replace('x','*') 
                .replace('X','*') 
                .replace(':','/') 
                .replaceAll("\\s+", ""); 
    }
    
/**
 * Normalizes the expression for evaluation by replacing:
 *  ×, x, X → '*',  ':' → '/', and removing all whitespace.
 *
 * @param s raw user input
 * @return a normalized expression string
 */
    private static String exprDenorm(String s) {
        return s.replace('*','x').replace('/',':'); 
    }

    private static boolean isOp(char c) {
        return c=='+' || c=='-' || c=='*' || c=='/';
    }

    /**
     * Initiates the recursive evaluation and prints the initial normalized step.
     */
    private static void exprEvaluate(String expr) {
        String normalizedExpr = exprNormalize(expr);
        System.out.println("= " + exprDenorm(normalizedExpr)); 
        exprReduce(normalizedExpr, true); 
    }

   /**
     * Recursively reduces a mathematical expression step-by-step, following operator precedence.
     */
    private static String exprReduce(String s, boolean shouldPrint) {
        s = exprStripOuter(s); 
        
        if (s.matches("^-?\\d+$")) return s; // Base Case

        // Step 1: Parentheses
        int deepestOpenParenIndex = -1;
        int matchingCloseParenIndex = -1; 
        int currentDepth = 0;
        int maxDepth = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                currentDepth++;
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                    deepestOpenParenIndex = i; 
                }
            } else if (s.charAt(i) == ')') {
                if (currentDepth == maxDepth && deepestOpenParenIndex != -1) {
                    matchingCloseParenIndex = i; 
                    
                    String insideResult = exprReduce(s.substring(deepestOpenParenIndex + 1, matchingCloseParenIndex), false);
                    
                    String prefix = s.substring(0, deepestOpenParenIndex);
                    String suffix = s.substring(matchingCloseParenIndex + 1);

                    if (insideResult.startsWith("-") && !prefix.isEmpty() && prefix.charAt(prefix.length() - 1) == '+') {
                        prefix = prefix.substring(0, prefix.length() - 1);
                    }
                    String nextExpression = prefix + insideResult + suffix;
                    
                    if (shouldPrint) System.out.println("= " + exprDenorm(nextExpression)); 
                    return exprReduce(nextExpression, shouldPrint); 
                }
                 currentDepth--;
            }
        }
        
        // Step 2: * and /
        int md = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') { md = i; break; }
        }
        if (md != -1) {
            String next = exprApplyOp(s, md); 
            if (shouldPrint) System.out.println("= " + exprDenorm(next)); 
            return exprReduce(next, shouldPrint);
        }

        // Step 3: + and -
        int as = -1;
        for (int i = 1; i < s.length(); i++) { 
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (!isOp(s.charAt(i-1)) && s.charAt(i-1) != '(') { as = i; break; }
            }
        }
        if (as != -1) {
            String next = exprApplyOp(s, as); 
            if (shouldPrint) System.out.println("= " + exprDenorm(next)); 
            return exprReduce(next, shouldPrint);
        }

        return s; 
    }

    /**
     * Applies the operator at index `opIndex` to the numbers immediately left and right.
     */
    private static String exprApplyOp(String s, int opIndex) {
        int L = opIndex - 1;
        while (L >= 0 && Character.isDigit(s.charAt(L))) L--; 
        if (L >= 0 && s.charAt(L) == '-' && (L == 0 || isOp(s.charAt(L-1)) || s.charAt(L-1) == '(')) {
        } else {
            L++; 
        }

        int R = opIndex + 1;
        if (R < s.length() && s.charAt(R) == '-') R++;
        while (R < s.length() && Character.isDigit(s.charAt(R))) R++; 
        
        String leftStr = s.substring(L, opIndex);
        String rightStr = s.substring(opIndex + 1, R);

        long leftNum = Long.parseLong(leftStr);
        long rightNum = Long.parseLong(rightStr);
        long resultNum;

        switch (s.charAt(opIndex)) {
            case '*' -> resultNum = leftNum * rightNum;
            case '/' -> {
                if (rightNum == 0) throw new ArithmeticException("Division by zero"); 
                resultNum = leftNum / rightNum; 
            }
            case '+' -> resultNum = leftNum + rightNum;
            case '-' -> resultNum = leftNum - rightNum; 
            default  -> throw new IllegalArgumentException("Invalid operator: " + s.charAt(opIndex));
        }
        
        String resultStr = String.valueOf(resultNum); 
        String prefix = s.substring(0, L);
        String suffix = s.substring(R);

        if (resultStr.startsWith("-") && !prefix.isEmpty() && prefix.charAt(prefix.length() - 1) == '+') {
            prefix = prefix.substring(0, prefix.length() - 1);
        }

        return prefix + resultStr + suffix;
    }

    // Removes matching outermost parentheses if they span the entire string
    private static String exprStripOuter(String s) {
        while (s.startsWith("(") && s.endsWith(")")) {
            int balance = 0; 
            boolean wrapsEntireString = true;
            for (int i = 0; i < s.length() - 1; i++) { 
                if (s.charAt(i) == '(') balance++;
                else if (s.charAt(i) == ')') balance--;
                if (balance == 0 && i < s.length() - 2) { 
                    wrapsEntireString = false; 
                    break; 
                }
            }
            if (wrapsEntireString && balance > 0) { 
                s = s.substring(1, s.length() - 1); 
            } else {
                break; 
            }
        }
        return s; 
    }

/*
 * =============================
 * Option C — High School  (with stricter inputs + quit)
 * =============================
 */
*author Melek Sadiki
/** Max array/vector length allowed in Option C (1–100). */
private static final int C_MAX_SIZE = 100;

/** Upper bound for stats array values (inclusive). */
private static final double C_MAX_VALUE = 10_000;

/**
 * Shows the Option C menu and routes to the selected task.
 * <p>A: stats on a single array, B: distances between two arrays, C: back to main menu.</p>
 */
private static void subMenuOption3() {
    while (true) {
        clearScreen();
        System.out.println(RED+"********************************"+RESET);
        System.out.println(CYAN+"[A] Statistical Information about an Array"+RESET);
        System.out.println(CYAN+"[B] Distance between Two Arrays"+RESET);
        System.out.println(CYAN+"[C] Return To Main Menu"+RESET);
        System.out.println(RED+"********************************"+RESET);
        System.out.print(GREEN+"Please select an option to continue: "+RESET);
        char choice = readMenuOption(SC, 'A', 'C');

        clearScreen();

        switch (choice) {
            case 'A' -> statisticalInformation();
            case 'B' -> distanceBetweenTwoArrays();
            case 'C' -> {
                System.out.println(GREEN + "\nReturning the main menu."+RESET);
                System.out.println(YELLOW + "Press ENTER..." + RESET);
                SC.nextLine();
                return;
            }
        }
    }
}

// ---- Helpers for Option C (with QUIT support) ----

/**
 * Reads a size/dimension within [1..C_MAX_SIZE].
 * @param prompt label to show
 * @return the size, or {@code null} if the user enters 'Q'
 */
private static Integer readSizeCOrQuit(String prompt) {
    System.out.print(YELLOW + prompt + " (1-" + C_MAX_SIZE + ", or Q to quit): " + RESET);
    while (true) {
        String s = SC.nextLine().trim();
        if (s.equalsIgnoreCase("Q")) return null;
        if (!s.matches("\\d+")) {
            System.out.print(RED + "Digits only (or Q). Try again: " + RESET);
            continue;
        }
        try {
            int n = Integer.parseInt(s);
            if (n < 1 || n > C_MAX_SIZE) {
                System.out.print(RED + "Range is 1-" + C_MAX_SIZE + ". Try again: " + RESET);
                continue;
            }
            return n;
        } catch (NumberFormatException e) {
            System.out.print(RED + "Invalid number. Try again: " + RESET);
        }
    }
}

/**
 * Reads {@code n} numbers for the stats array; each value must be ≤ 10000.
 * If any token in a line is invalid, the whole line is ignored.
 * @param n count of numbers to read
 * @return list of values, or {@code null} if user enters 'Q'
 */
private static ArrayList<Double> readStatsArrayOrQuit(int n) {
    ArrayList<Double> list = new ArrayList<>(n);
    System.out.println(CYAN + "Enter " + n + " numbers (<= " + (int)C_MAX_VALUE + "). Use space/newline. Type Q anytime to quit." + RESET);
    while (list.size() < n) {
        String line = SC.nextLine().trim();
        if (line.equalsIgnoreCase("Q")) return null;
        if (line.isEmpty()) continue;

        String[] tok = line.split("\\s+");
        boolean lineBad = false;
        ArrayList<Double> lineVals = new ArrayList<>();

        for (String t : tok) {
            if (t.equalsIgnoreCase("Q")) return null;
            try {
                double v = Double.parseDouble(t);
                if (v > C_MAX_VALUE) {
                    System.out.println(RED + "Value " + t + " exceeds " + (int)C_MAX_VALUE + ". Please enter <= " + (int)C_MAX_VALUE + "." + RESET);
                    lineBad = true;
                    break;
                }
                lineVals.add(v);
            } catch (NumberFormatException ex) {
                System.out.println(RED + "Invalid number: '" + t + "'. Please re-enter the line." + RESET);
                lineBad = true;
                break;
            }
        }

        if (lineBad) continue;

        int remaining = n - list.size();
        for (int i = 0; i < Math.min(remaining, lineVals.size()); i++) {
            list.add(lineVals.get(i));
        }
        if (lineVals.size() > remaining) {
            System.out.println(RED + "Warning: " + (lineVals.size() - remaining) + " extra value(s) ignored." + RESET);
        }
    }
    return list;
}

/**
 * Reads a vector of length {@code d} with single-digit entries [0–9].
 * If any token is invalid, that vector input restarts from the beginning.
 * @param d vector length
 * @param name label to display (A or B)
 * @return the vector, or {@code null} if user enters 'Q'
 */
private static int[] readVectorCOrQuit(int d, String name) {
    while (true) {
        int[] v = new int[d];
        int i = 0;
        System.out.println(CYAN + "Enter vector " + name + " (length " + d + ", digits 0-9). Type Q to quit." + RESET);

        while (i < d) {
            String line = SC.nextLine().trim();
            if (line.equalsIgnoreCase("Q")) return null;
            if (line.isEmpty()) continue;

            String[] tok = line.split("\\s+");
            boolean bad = false;
            int consumed = 0;

            for (String t : tok) {
                if (t.equalsIgnoreCase("Q")) return null;

                if (!t.matches("[0-9]")) {
                    System.out.println(RED + "Invalid: '" + t + "'. Elements must be single digits (0-9)." + RESET);
                    bad = true;
                    break;
                }

                if (i < d) {
                    v[i++] = Integer.parseInt(t);
                    consumed++;
                } else {
                    consumed++;
                }
            }

            if (bad) {
                i = 0;
                System.out.println(YELLOW + "Re-enter vector " + name + " from the beginning." + RESET);
            } else if (i >= d && consumed < tok.length) {
                int extras = tok.length - consumed;
                System.out.println(RED + "Warning: " + extras + " extra value(s) ignored for vector " + name + "." + RESET);
            }
        }
        return v;
    }
}

/**
 * Runs the stats task with Option C constraints and prints median/means.
 * Quits back to the Option C menu if the user enters 'Q' at any prompt.
 */
private static void statisticalInformation() {
    while (true) {
        arrC.clear();
        System.out.println(YELLOW + "=== Statistical Information about an Array ===" + RESET);

        Integer n = readSizeCOrQuit("Enter array size n");
        if (n == null) {
            System.out.println(GREEN + "Returning to High School menu..." + RESET);
            return;
        }

        ArrayList<Double> input = readStatsArrayOrQuit(n);
        if (input == null) {
            System.out.println(GREEN + "Returning to High School menu..." + RESET);
            return;
        }
        arrC = input;

        double median = calculateMedian();
        double arithmetic = calculateArithmeticMean();
        double geometric = calculateGeometricMean();
        double harmonic = calculateHarmonicMean();

        System.out.println(GREEN + "\nResults:" + RESET);
        System.out.printf(CYAN + "Median:           " + RESET + "%.6f%n", median);
        System.out.printf(CYAN + "Arithmetic mean:  " + RESET + "%.6f%n", arithmetic);
        if (Double.isNaN(geometric)) System.out.println(CYAN + "Geometric mean:   " + RESET + RED + "undefined (non-positive element)" + RESET);
        else System.out.printf(CYAN + "Geometric mean:   " + RESET + "%.6f%n", geometric);
        if (Double.isNaN(harmonic)) System.out.println(CYAN + "Harmonic mean:    " + RESET + RED + "undefined (element is 0)" + RESET);
        else System.out.printf(CYAN + "Harmonic mean:    " + RESET + "%.6f%n", harmonic);

        System.out.print(YELLOW + "\nAnother array? (Y/N): " + RESET);
        char repeatChoice = readMenuOption(SC, 'N', 'Y');
        if (repeatChoice == 'N') return;
        clearScreen();
    }
}

/**
 * Returns the median of {@code arrC}. Uses a sorted copy; supports even/odd lengths.
 * @return median value, or 0.0 for empty input
 */
private static double calculateMedian() {
    if (arrC.isEmpty()) return 0.0;
    ArrayList<Double> sortedCopy = new ArrayList<>(arrC);
    sortedCopy.sort(Double::compareTo);
    int n = sortedCopy.size();
    if (n % 2 == 1) {
        return sortedCopy.get(n / 2);
    } else {
        return (sortedCopy.get(n / 2 - 1) + sortedCopy.get(n / 2)) / 2.0;
    }
}

/**
 * Returns the arithmetic mean of {@code arrC}.
 * @return mean value, or 0.0 for empty input
 */
private static double calculateArithmeticMean() {
    if (arrC.isEmpty()) return 0.0;
    double sum = 0.0;
    for (double v : arrC) sum += v;
    return sum / arrC.size();
}

/**
 * Returns the geometric mean of {@code arrC} if all values are positive; otherwise {@code NaN}.
 * @return geometric mean or {@code NaN}
 */
private static double calculateGeometricMean() {
    if (arrC.isEmpty()) return 0.0;
    for (double v : arrC) if (v <= 0.0) return Double.NaN;

    double logSum = 0.0;
    for (double v : arrC) logSum += Math.log(v);
    return Math.exp(logSum / arrC.size());
}

/**
 * Returns the harmonic mean of {@code arrC}; returns {@code NaN} if any value is zero.
 * Uses a recursive helper to sum reciprocals.
 */
private static double calculateHarmonicMean() {
    if (arrC.isEmpty()) return 0.0;
    for (double v : arrC) if (v == 0.0) return Double.NaN;

    double sumOfReciprocals = harmonicRec(arrC, 0);
    return arrC.size() / sumOfReciprocals;
}

/**
 * Recursive helper for harmonic mean: sums 1/x over the list.
 * @param a list of values
 * @param index current position
 * @return partial sum of reciprocals
 */
private static double harmonicRec(ArrayList<Double> a, int index) {
    if (index == a.size()) return 0.0;
    return (1.0 / a.get(index)) + harmonicRec(a, index + 1);
}

/**
 * Runs the distance task with Option C rules (d ≤ 100; digits 0–9).
 * Reads A and B, then prints Manhattan, Euclidean, and Cosine similarity.
 */
private static void distanceBetweenTwoArrays() {
    while (true) {
        System.out.println(YELLOW + "=== Distance between Two Arrays ===" + RESET);

        Integer d = readSizeCOrQuit("Enter vector dimension d");
        if (d == null) {
            System.out.println(GREEN + "Returning to High School menu..." + RESET);
            return;
        }

        int[] vectorA = readVectorCOrQuit(d, "A");
        if (vectorA == null) {
            System.out.println(GREEN + "Returning to High School menu..." + RESET);
            return;
        }

        int[] vectorB = readVectorCOrQuit(d, "B");
        if (vectorB == null) {
            System.out.println(GREEN + "Returning to High School menu..." + RESET);
            return;
        }

        double manhattan = calculateManhattanDistance(vectorA, vectorB);
        double euclidean = calculateEuclideanDistance(vectorA, vectorB);
        Double cosine = calculateCosineSimilarity(vectorA, vectorB);

        System.out.println(GREEN + "\nResults:" + RESET);
        System.out.printf(CYAN + "Manhattan distance: " + RESET + "%.6f%n", manhattan);
        System.out.printf(CYAN + "Euclidean distance: " + RESET + "%.6f%n", euclidean);
        if (cosine == null) System.out.println(CYAN + "Cosine similarity:  " + RESET + RED + "undefined (one vector is zero)" + RESET);
        else System.out.printf(CYAN + "Cosine similarity:  " + RESET + "%.6f%n", cosine);

        System.out.print(YELLOW + "\nOther vectors? (Y/N): " + RESET);
        char repeatChoice = readMenuOption(SC, 'N', 'Y');
        if (repeatChoice == 'N') break;
        clearScreen();
    }
}

/**
 * Computes the Manhattan distance between two equal-length int vectors.
 * @return sum of absolute differences
 */
private static double calculateManhattanDistance(int[] a, int[] b) {
    long sumOfAbsDiff = 0L;
    for (int i = 0; i < a.length; i++) {
        sumOfAbsDiff += Math.abs(a[i] - b[i]);
    }
    return (double) sumOfAbsDiff;
}

/**
 * Computes the Euclidean distance between two equal-length int vectors.
 * @return sqrt of the sum of squared differences
 */
private static double calculateEuclideanDistance(int[] a, int[] b) {
    double sumOfSquares = 0.0;
    for (int i = 0; i < a.length; i++) {
        double diff = a[i] - b[i];
        sumOfSquares += diff * diff;
    }
    return Math.sqrt(sumOfSquares);
}

/**
 * Computes cosine similarity between two int vectors.
 * @return similarity in [-1,1], or {@code null} if either vector has zero magnitude
 */
private static Double calculateCosineSimilarity(int[] a, int[] b) {
    long dotProduct = 0L;
    long magnitudeASquared = 0L;
    long magnitudeBSquared = 0L;

    for (int i = 0; i < a.length; i++) {
        dotProduct += (long) a[i] * b[i];
        magnitudeASquared += (long) a[i] * a[i];
        magnitudeBSquared += (long) b[i] * b[i];
    }

    if (magnitudeASquared == 0L || magnitudeBSquared == 0L) {
        return null;
    }
    return dotProduct / (Math.sqrt(magnitudeASquared) * Math.sqrt(magnitudeBSquared));
}

    
    /*
     * =============================
     * Option D — University (Connect Four)
     * =============================
     */

/**
 * Displays the Connect Four (Option D) menu and allows the user to:
 * <ul>
 *   <li>Select a board size (5x4, 6x5, 7x6)</li>
 *   <li>Select the game mode (single player vs. CPU or two players)</li>
 * </ul>
 *
 * <p>If the user selects "Return to Main Menu", the method returns immediately.</p>
 *
 * @author bora-G
 */
    private static void subMenuOption4() {
        while (true) {
            System.out.println(CYAN+ "=== Connect4 ==="+ RESET);
            System.out.println(RED + "********************************" + RESET);
            System.out.println(CYAN + "[A] 5 x 4 Map " + RESET);
            System.out.println(CYAN + "[B] 6 x 5 Map " + RESET);
            System.out.println(CYAN + "[C] 7 x 6 Map " + RESET);
            System.out.println(CYAN + "[D] Return to the Main Menu " + RESET);
            System.out.println(RED + "********************************" + RESET);
            System.out.println(GREEN + "Please select an option to continue: " + RESET);
            int choice = readMenuOption(SC, 'A', 'D');

            switch (choice) {
                case 'A': ROWS = 5; COLS = 4; break;
                case 'B': ROWS = 6; COLS = 5; break;
                case 'C': ROWS = 7; COLS = 6; break;
                case 'D':
                    System.out.println(GREEN + "\nReturning the main menu."+RESET);
                    System.out.println(YELLOW + "Press ENTER..." + RESET);
                    SC.nextLine(); 
                    return; 
            }
            clearScreen();
            break;
        }

        while (true) {
            System.out.println(CYAN+ "=== Connect4 ==="+ RESET);
            System.out.println(RED + "********************************" + RESET);
            System.out.println(CYAN + "[A] 1 Player " + RESET);
            System.out.println(CYAN + "[B] 2 Player " + RESET);
            System.out.println(CYAN + "[C] Return to the Main Menu " + RESET);
            System.out.println(RED + "********************************" + RESET);
            System.out.println(GREEN + "Please select an option to continue: " + RESET);
            char choice = readMenuOption(SC, 'A', 'C');

            if (choice == 'C') {
                clearScreen();
                // This logic is slightly different from other submenus, 
                // but it achieves the goal of returning to the *board selection* menu.
                subMenuOption4();
                return;
            }

            singlePlayer = (choice == 'A');

            startConnectFour();
            clearScreen();
        }
    }

    /**
 * Starts the Connect Four game loop for the currently selected board size.
 *
 * <p>The game will continue until one of these conditions occurs:</p>
 * <ul>
 *   <li>A player wins ({@link #checkWin()})</li>
 *   <li>The board is completely filled (draw, {@link #checkDraw()})</li>
 *   <li>A player forfeits by entering 'Q' during input</li>
 * </ul>
 *
 * <p>If single-player mode is chosen, the CPU plays using {@link #makeMoveCPU()}.
 * Otherwise, both players use {@link #makeMovePlayer()}.</p>
 *
 * @author bora-G
 */

    private static void startConnectFour() {
        board = new char[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            Arrays.fill(board[r], '.');
        }

        currentPlayer = 'X';

        while (true) {

            clearScreen();
            renderBoard();
            boolean gameOn = true; 

            if (singlePlayer) {
                if (currentPlayer == 'X') {
                    gameOn = makeMovePlayer(); 
                } else {
                    makeMoveCPU();
                }
            } else {
                gameOn = makeMovePlayer(); 
            }

            // Check for Forfeit
            if (!gameOn) {
                clearScreen();
                renderBoard();
                char forfeitingPlayer = currentPlayer;
                switchPlayer(); 
                System.out.println(RED + "Player " + forfeitingPlayer + " forfeited." + RESET);
                System.out.println(GREEN + "Player " + currentPlayer + " wins by forfeit!" + RESET);
                System.out.println(YELLOW + "Press ENTER to continue..." + RESET);
                SC.nextLine();
                break; 
            }

            // Check for Win
            if (checkWin()) {
                clearScreen();
                renderBoard();
                System.out.println(GREEN + "Player " + currentPlayer + " wins!" + RESET);
                System.out.println(YELLOW + "Press ENTER to continue..." + RESET);
                SC.nextLine();
                break;
            }

            // Check for Draw
            if (checkDraw()) {
                clearScreen();
                renderBoard();
                System.out.println(YELLOW + "It's a draw." + RESET);
                System.out.println(YELLOW + "Press ENTER to continue..." + RESET);
                SC.nextLine();
                break;
            }

            switchPlayer();
        }
    }

/**
 * Switches the active player.
 *
 * <p>If the current player is 'X', switches to 'O', and vice-versa.</p>
 */
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

/**
 * Prints the Connect Four board to the console with row/column indicators.
 * Empty cells are rendered as '.', while tokens are colored 'X' or 'O'.
 *
 * <p>ANSI escape codes are used for visual coloring.</p>
 */
    private static void renderBoard() {
        System.out.print(CYAN);
        System.out.print("  ");
        for (int c = 1; c <= COLS; c++) {
            System.out.print(c);
            if (c < COLS) System.out.print("   ");
        }
        System.out.println(RESET);

        System.out.print(YELLOW + "+");
        for (int i = 0; i < COLS; i++) System.out.print("---+");
        System.out.println(RESET);

        for (int r = 0; r < ROWS; r++) {
            System.out.print(YELLOW + "|" + RESET);
            for (int c = 0; c < COLS; c++) {
                char ch = board[r][c];
                if (ch == 'X') System.out.print(RED + " X " + RESET);
                else if (ch == 'O') System.out.print(GREEN + " O " + RESET);
                else System.out.print("   ");
                System.out.print(YELLOW + "|" + RESET);
            }
            System.out.println();

            System.out.print(YELLOW + "+");
            for (int i = 0; i < COLS; i++) System.out.print("---+");
            System.out.println(RESET);
        }
        System.out.println(CYAN+ "=== Connect4 ==="+ RESET);
    }

   /**
 * Handles a player's move input.
 *
 * <p>Prompts the player to choose a column (1..COLS). If the input is invalid or
 * the column is full, the user is prompted again.</p>
 *
 * <p>If the user enters 'Q' or 'q', the method treats it as a forfeit and returns false.</p>
 *
 * @return true  – valid move was made <br>
 *         false – the player forfeited the game
 */
    private static boolean makeMovePlayer() {
        while (true) {
            System.out.print(YELLOW + "Player " + currentPlayer + ", choose a column (1-" + COLS + ") or enter 'Q' to forfeit: " + RESET);
            String choice = SC.nextLine().trim();

            if (choice.equalsIgnoreCase("Q")) {
                return false; // Forfeit
            }

            int col;
            try {
                col = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
                continue;
            }

            if (col < 1 || col > COLS) {
                System.out.println(RED + "Column out of range. Enter 1-" + COLS + "." + RESET);
                continue;
            }

            int c = col - 1; // Convert to 0-based index
            if (board[0][c] != '.') {
                System.out.println(RED + "That column is full. Choose another." + RESET);
                continue;
            }

            // Drop the disc
            for (int r = ROWS - 1; r >= 0; r--) {
                if (board[r][c] == '.') {
                    board[r][c] = currentPlayer;
                    return true; // Move successful
                }
            }
        }
    }
/**
 * Generates a move for the CPU player.
 *
 * This is NOT an AI or machine learning model.
 * It simply applies a fixed decision-making heuristic:
 * <ol>
 *   <li>If the CPU can win with this move, play it.</li>
 *   <li>Otherwise, if the opponent can win next, block it.</li>
 *   <li>Otherwise, play toward the center columns.</li>
 * </ol>
 *
 * @implNote
 * This is a deterministic rule-based algorithm.
 * No machine learning, prediction, or adaptive behavior is used.
 */

    private static void makeMoveCPU() {
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        final char aiPlayer = currentPlayer;
        final char opponentPlayer = (aiPlayer == 'X') ? 'O' : 'X';

        // 1. Winning move
        for (int col = 0; col < COLS; col++) {
            int row = dropRow(col);
            if (row == -1) continue;
            board[row][col] = aiPlayer;
            boolean win = winningAt(row, col, aiPlayer);
            board[row][col] = '.'; 
            if (win) { board[row][col] = aiPlayer; return; }
        }

        // 2. Blocking move
        for (int col = 0; col < COLS; col++) {
            int row = dropRow(col);
            if (row == -1) continue;
            board[row][col] = opponentPlayer;
            boolean oppWin = winningAt(row, col, opponentPlayer);
            board[row][col] = '.'; 
            if (oppWin) { board[row][col] = aiPlayer; return; }
        }

        // 3. Prefer center-ish
        int[] pref = new int[COLS];
        int center = COLS / 2;
        int pidx = 0;
        for (int d = 0; pidx < COLS; d++) {
            int col;
            if (d % 2 == 0) { col = center + d / 2; } 
            else { col = center - (d + 1) / 2; }
            if (col >= 0 && col < COLS) pref[pidx++] = col;
        }
        
        int[] candidates = new int[COLS];
        int n = 0;
        for (int c : pref) if (dropRow(c) != -1) candidates[n++] = c;
        if (n == 0) return; 
        
        int chosen = candidates[RNG.nextInt(n)];
        int r = dropRow(chosen);
        if (r != -1) board[r][chosen] = aiPlayer;
    }

    
   /**
 * Returns the first available row index (from bottom up) in the given column.
 *
 * @param column the zero-based column index to search
 * @return the row index where a token can be placed, or -1 if the column is full
 */
    private static int dropRow(int column) {
        if (column < 0 || column >= COLS) return -1;
        for (int row = ROWS - 1; row >= 0; row--) if (board[row][column] == '.') return row;
        return -1;
    }

/**
 * Checks if a coordinate (row, column) lies inside the board.
 *
 * @param row    row index
 * @param column column index
 * @return true if inside bounds, otherwise false
 */
    private static boolean inBounds(int row, int column) {
        return row >= 0 && row < ROWS && column >= 0 && column < COLS;
    }


   /**
 * Checks whether the last placed token at (row, column) creates a winning condition
 * for the given player.
 *
 * <p>Directions scanned:</p>
 * <ul>
 *   <li>Horizontal (→ ←)</li>
 *   <li>Vertical (↓ ↑)</li>
 *   <li>Diagonal (↘ ↖)</li>
 *   <li>Diagonal (↗ ↙)</li>
 * </ul>
 *
 * @param row     the last placed token's row index
 * @param column  the last placed token's column index
 * @param player  the token to check ('X' or 'O')
 * @return true if this move produces a four-in-a-row, otherwise false
 */
    private static boolean winningAt(int row, int column, char player) {
        if (row < 0 || column < 0) return false;
        int[][] directions = { {0,1},{1,0},{1,1},{1,-1} }; // H, V, Diag\, Diag/
        for (int[] direction : directions) {
            int count = 1, dr = direction[0], dc = direction[1];
            // Check one way
            for (int k = 1; k < 4; k++) {
                int rr = row + dr*k, cc = column + dc*k;
                if (inBounds(rr,cc) && board[rr][cc] == player) count++; else break;
            }
            // Check the other way
            for (int k = 1; k < 4; k++) {
                int rr = row - dr*k, cc = column - dc*k;
                if (inBounds(rr,cc) && board[rr][cc] == player) count++; else break;
            }
            if (count >= 4) return true;
        }
        return false;
    }

   /**
 * Checks whether the board is full (no empty cells remain in the top row).
 *
 * @return true if the board is full, false otherwise
 */
    private static boolean isFull() {
        for (int col = 0; col < COLS; col++) if (board[0][col] == '.') return false;
        return true;
    }

    /**
     * Checks the entire board for a win for the *current* player.
     */
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

    /**
 * Checks whether the game resulted in a draw.
 * A draw occurs when the board is completely full and no player has won.
 *
 * @return true if the game is a draw, false otherwise
 */
    private static boolean checkDraw() {
        return isFull();
    }

    /*
     * =============================
     * Program Entry Point
     * =============================
     */
    public static void main(String[] args) {
        welcomeMessage(); 
        mainMenu(); 
        SC.close(); 
    }
}


