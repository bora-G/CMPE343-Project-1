import java.time.LocalDate;
import java.util.Arrays; // Needed for sieve algorithms (Arrays.fill) and Option C (Arrays.copyOfRange)
// ArrayList is no longer needed for Option B primes, but kept for Option C statistics
import java.util.ArrayList;
import java.util.Locale; // Added for Option C decimal input handling
import java.util.Scanner;

public class Main {

    /*
     * =============================
     * Global Constants & Scanner
     * =============================
     */
     
    // Use US locale to ensure decimal inputs (like 3.14) are parsed correctly using '.'
    private static final java.util.Scanner SC = new java.util.Scanner(System.in).useLocale(Locale.US);
    
    // ANSI escape codes for colored console output
    private static String cyan = "\u001B[36m";
    private static String yellow = "\u001B[33m";
    private static String green = "\u001B[32m";
    private static String reset = "\u001B[0m"; // Resets text color to default
    private static String red = "\u001B[31m";
    
    // Shared data structure for Option C - Task 1 (Statistical Information)
    private static ArrayList<Double> arrC = new ArrayList<>();


    /*
     * =============================
     * Welcome & Main Menu Logic
     * =============================
     */

    private static void welcomeMessage() {
        clearScreen();
        // Display ASCII art and group member names
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
        SC.nextLine(); // Wait for user confirmation
    }

    private static void mainMenu() {
        while (true) {
            clearScreen(); // Clear screen before showing the menu
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Primary School"+reset);
            System.out.println(cyan+"[B] Secondary School"+reset);
            System.out.println(cyan+"[C] High School"+reset);
            System.out.println(cyan+"[D] University"+reset);
            System.out.println(cyan+"[E] Terminate"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'E'); // Read a single uppercase char A-E

            // Clear screen AFTER selection, BEFORE executing the chosen option (as per PDF)
            clearScreen(); 

            switch (choice) {
                case 'A' -> subMenuOption1(); // Navigate to Primary School submenu
                case 'B' -> subMenuOption2(); // Navigate to Secondary School submenu
                case 'C' -> subMenuOption3(); // Navigate to High School submenu
                case 'D' -> subMenuOption4(); // Navigate to University submenu (stub)
                case 'E' -> { // Terminate the program
                    System.out.println(green + "\nTurning the program off..." + reset);
                    System.out.println(red + "Thank you for using our program!" + reset);
                    return; // Exit mainMenu loop and thus main method
                }
            }
        }
    }

    // Helper method to read a single menu option character (A-Z) with validation
    private static char readMenuOption(Scanner scan, char min, char max) {
        while (true) {
            String s = scan.nextLine().trim().toUpperCase(); // Read line, trim whitespace, convert to uppercase

            // Check for empty input
            if (s.isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter a letter between " + min + " and " + max + ": " + reset);
                continue; // Ask again
            }
            // Check if more than one character was entered
            if (s.length() > 1){
                System.out.print(red + "Invalid input. Please enter a single letter: " + reset);
                continue; // Ask again
            }

            char choice = s.charAt(0); // Get the single character
            // Check if the character is within the valid range (e.g., 'A' to 'E')
            if (choice < min || choice > max) {
                System.out.print(red + "Please enter a valid option letter between [" + min + "] and [" + max + "]: " + reset);
                continue; // Ask again
            }
            return choice; // Return the valid character
        }
    }

    // Helper method to read an integer within a specific range [min, max] with validation
    private static int readInt(Scanner scan, int min, int max) {
        while (true) {
            String s = scan.nextLine().trim(); // Read line, trim whitespace

            // Check for empty input
            if (s.isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter a number between " + min + " and " + max + ": " + reset);
                continue; // Ask again
            }
            // Check if input contains only digits
            if (!s.matches("\\d+")) {
                System.out.print(red + "Invalid input. Please enter a number between " + min + " and " + max + ": " + reset);
                continue; // Ask again
            }
            
            try {
                int val = Integer.parseInt(s); // Convert string to integer
                // Check if the integer is within the specified range
                if (val < min || val > max) {
                    System.out.print(red + "Number out of range. Please enter between " + min + " and " + max + ": " + reset);
                    continue; // Ask again
                }
                return val; // Return the valid integer
            } catch (NumberFormatException e) {
                // Handle cases where the number is too large to fit in an int
                System.out.print(red + "Invalid number format (too large). Please enter between " + min + " and " + max + ": " + reset);
                // Continue loop asks again
            }
        }
    }

    /* Helper method to clear the console screen */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J"); // Standard ANSI escape sequence for clearing screen
        System.out.flush(); // Ensure the command is sent to the console immediately
    }

    /*
     * =============================
     * Option A — Primary School
     * =============================
     */
    private static void subMenuOption1() {
        while (true) {
            clearScreen(); // Clear screen before showing the submenu
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Calculate Age and Zodiac Sign"+reset);
            System.out.println(cyan+"[B] Reverse the Words"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');

            // Clear screen AFTER selection, BEFORE executing the chosen option
            clearScreen();

            switch (choice) {
                case 'A' -> ageAndZodiacSignDetection();
                case 'B' -> reverseTheWords();
                case 'C' -> { // Return to main menu
                    System.out.println(green + "\nReturning the main menu."+reset);
                    System.out.println(yellow + "Press ENTER..." + reset);
                    SC.nextLine(); // Wait for user confirmation before returning
                    return; // Exit submenu loop
                }
            }
        }
    }

    /* Option A - Task 1: Age and Zodiac Calculation */
    private static void ageAndZodiacSignDetection() {
        // Loop allows user to repeat the calculation or return to the submenu
        while (true) {
            System.out.println(cyan + "--- Age and Zodiac Sign Calculator ---" + reset);
            System.out.print(yellow + "Please enter your year of birth: " + reset);
            int birthYear = getYear(SC); // Reads and validates year

            System.out.print(yellow + "Please enter your month of birth (1-12): " + reset);
            int birthMonth = getMonth(SC); // Reads and validates month

            System.out.print(yellow + "Please enter your day of birth: " + reset);
            // Reads and validates day based on month and year (handles leap years)
            int birthDay = getDay(SC, birthMonth, birthYear);

            // Get current date components using LocalDate (as allowed by PDF for current date)
            int currentYear = getCurrentYear();
            int currentMonth = getCurrentMonth();
            int currentDay = getCurrentDay();

            // Initial difference calculation
            int years = currentYear - birthYear;
            int months = currentMonth - birthMonth;
            int days = currentDay - birthDay;

            // Manual adjustment for negative days (borrowing from months)
            int tempMonth = currentMonth;
            int tempYear = currentYear;
            while (days < 0) {
                int prevMonth = tempMonth - 1;
                int prevYear = tempYear;
                if (prevMonth == 0) { prevMonth = 12; prevYear -= 1; } // Handle year change
                int daysInPrevMonth = getDaysInMonth(prevMonth, prevYear); // Get days in the month we borrow from
                
                days += daysInPrevMonth; // Add days from previous month
                months -= 1; // Decrement month count

                // Update temp date for next potential borrow iteration
                tempMonth = prevMonth;
                tempYear = prevYear;
            }

            // Manual adjustment for negative months (borrowing from years)
            if (months < 0) {
                years -= 1; // Decrement year count
                months += 12; // Add 12 to month count
            }

            // Display results
            System.out.println(cyan + "Your age: " + years + " years, " + months + " months, " + days + " days" + reset);
            System.out.println(red + "Your zodiac sign is " + getZodiacString(birthDay, birthMonth) + "." + reset);
            
            // Fun message for very old birth years
            if (birthYear <= 1500) {
                System.out.println(red + " Wow... You must be a time traveler! " + reset);
                System.out.println(yellow + "Are you sure you were born in " + birthYear + "?" + reset);
            }
            
            // Ask user if they want to repeat or return (as per PDF requirement)
            System.out.print(yellow + "\nCalculate again? (Y/N): " + reset);
            char repeatChoice = readMenuOption(SC, 'N', 'Y'); // Read Y or N
            if (repeatChoice == 'N') {
                break; // Exit the loop to return to the submenu
            }
            clearScreen(); // Clear screen before repeating
        }
    }

    // Helper to get Zodiac sign based on day and month
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
        return "Unknown"; // Should not happen with validation
    }

    // Helper to get number of days in a specific month and year (handles leap year)
    private static int getDaysInMonth(int month, int year) {
        if (month == 2) return IsLeapYear(year) ? 29 : 28; // February check
        else if (month == 4 || month == 6 || month == 9 || month == 11) return 30; // Months with 30 days
        else return 31; // Months with 31 days
    }

    // Helper to check if a year is a leap year
    private static boolean IsLeapYear(int year) {
        // Leap year rules: divisible by 400 OR divisible by 4 BUT NOT by 100
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // Helper to read and validate the day of birth
    private static int getDay(Scanner scan, int month, int year) {
        int maxDay = getDaysInMonth(month, year); // Determine max valid day for the given month/year
        while (true) {
            String s = scan.nextLine().trim();
            // Basic validation (no leading zeros, digits only)
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.print(red + "Leading zeros are not allowed. Enter day (1-" + maxDay + "): " + reset); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(red + "Digits only. Enter day (1-" + maxDay + "): " + reset); continue;
            }
            try {
                int val = Integer.parseInt(s);
                // Range validation (1 to maxDay)
                if (val < 1 || val > maxDay) {
                    System.out.print(red + "Day must be between 1 and " + maxDay + ": " + reset); continue;
                }
                return val; // Return valid day
            } catch (NumberFormatException e) {
                System.out.print(red + "Invalid number format. Enter day (1-" + maxDay + "): " + reset);
            }
        }
    }

    // Helper to read and validate the month of birth (1-12)
    private static int getMonth(Scanner scan) {
        while (true) {
            String s = scan.nextLine().trim();
             // Basic validation (no leading zeros, digits only)
            if (s.length() > 1 && s.charAt(0) == '0') {
                 System.out.print(red + "Leading zeros are not allowed. Enter month (1-12): " + reset); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(red + "Digits only. Enter month (1-12): " + reset); continue;
            }
            try {
                int val = Integer.parseInt(s);
                 // Range validation (1 to 12)
                if (val < 1 || val > 12) {
                    System.out.print(red + "Month must be between 1 and 12: " + reset); continue;
                }
                return val; // Return valid month
            } catch (NumberFormatException e) {
                 System.out.print(red + "Invalid number format. Enter month (1-12): " + reset);
            }
        }
    }

    // Helper to read and validate the year of birth (non-negative, not in future)
    private static int getYear(Scanner scan) {
        int currentYear = getCurrentYear();
        while (true) {
            String s = scan.nextLine().trim();
             // Basic validation (no leading zeros, digits only)
            if (s.length() > 1 && s.charAt(0) == '0') {
                System.out.print(red + "Leading zeros are not allowed. Enter year (<= " + currentYear + "): " + reset); continue;
            }
            if (!s.matches("\\d+")) {
                System.out.print(red + "Digits only. Enter year (<= " + currentYear + "): " + reset); continue;
            }
            try {
                int val = Integer.parseInt(s);
                // Range validation (>= 0 and <= current year)
                if (val < 0) {
                    System.out.print(red + "Year cannot be negative. Enter year: " + reset); continue;
                }
                if (val > currentYear) {
                    System.out.print(red + "Year cannot be in the future (<= " + currentYear + "): " + reset); continue;
                }
                return val; // Return valid year
            } catch (NumberFormatException e) {
                 System.out.print(red + "Invalid number format. Enter year (<= " + currentYear + "): " + reset);
            }
        }
    }

    // Helpers to get current date components using LocalDate
    private static int getCurrentDay() { return LocalDate.now().getDayOfMonth(); }
    private static int getCurrentMonth() { return LocalDate.now().getMonthValue(); }
    private static int getCurrentYear() { return LocalDate.now().getYear(); }

    /* Option A — Task 2: Reverse the Words (recursively) */
    
    private static void reverseTheWords() {
        // Loop allows user to repeat the action or return to the submenu
        while(true) {
            System.out.println(cyan + "--- Reverse Words in Text ---" + reset);
            String input = getTextInput(SC); // Reads non-empty text input

            // Check if input meets the minimum length requirement (implicitly handled by isReversed)
            if (!isReversed(input)) {
                // This condition might be less likely if getTextInput ensures non-empty,
                // but kept for robustness as per original structure.
                System.out.println(red + "Input must contain meaningful characters." + reset);
            } else {
                // Perform the word reversal using the helper method
                String output = createReverseOutput(input);
                System.out.println(yellow + "\nOriginal Text:\n" + reset + input);
                System.out.println(cyan + "Reversed Words Text:\n" + reset + output);
            }
            
            // Ask user if they want to repeat or return
            System.out.print(yellow + "\nReverse another text? (Y/N): " + reset);
            char repeatChoice = readMenuOption(SC, 'N', 'Y'); // Read Y or N
            if (repeatChoice == 'N') {
                break; // Exit loop to return to submenu
            }
            clearScreen(); // Clear screen before repeating
        }
    }
    
    // Helper to read a non-empty line of text
    private static String getTextInput(Scanner scan) {
        System.out.print(green + "Enter text (Turkish characters allowed): " + reset);
        while (true) {
            String s = scan.nextLine();
            // Ensure the input is not just whitespace
            if (s.trim().isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter text: " + reset);
                continue; // Ask again
            }
            return s; // Return the valid, non-empty string
        }
    }
    
    // Core logic for reversing words >= 2 chars, preserving non-letters/apostrophes
    // Uses simple String concatenation instead of StringBuilder
    private static String createReverseOutput(String s) {
        String result = ""; // Initialize empty result string
        int i = 0; // Index for traversing the input string
        
        while (i < s.length()) {
            char startChar = s.charAt(i);

            // Check if the current character is a letter or an apostrophe
            if (Character.isLetter(startChar) || startChar == '\'') {
                // Start of a potential word block
                int j = i;
                // Find the end of the word block (consecutive letters/apostrophes)
                while (j < s.length() && (Character.isLetter(s.charAt(j)) || s.charAt(j) == '\'')) {
                    j++;
                }
                String word = s.substring(i, j); // Extract the word
                
                // Reverse the word only if it has 2 or more characters (project requirement)
                if (word.length() >= 2) {
                    result += reverseWordRec(word); // Append reversed word using recursion
                } else {
                    result += word; // Append short word as is
                }
                i = j; // Move index past the processed word block
            } else { 
                // Start of a non-word block (digits, punctuation, spaces)
                int j = i;
                 // Find the end of the non-word block
                while (j < s.length() && (!Character.isLetter(s.charAt(j)) && s.charAt(j) != '\'')) {
                    j++;
                }
                String nonWord = s.substring(i, j); // Extract the non-word block
                result += nonWord; // Append non-word block as is (preserving punctuation/digits)
                i = j; // Move index past the processed non-word block
            }
        }
        return result; // Return the final string with words reversed
    }

    // Helper (from user's code) - checks if string is not null and has meaningful length
    private static boolean isReversed(String s) {
        // Checks if the string is usable for reversal (not null, length >= 2 after trimming)
        // Note: The logic might need refinement based on exact definition of "reversible"
        return s != null && s.trim().length() >= 2;
    }
    
    // Recursive helper method to reverse a single string (word)
    // REQUIRED by the project description for Option A - Task 2
    private static String reverseWordRec(String s) {
        // Base Case: If the string is null, empty, or has 1 character, return it as is.
        if (s == null || s.length() <= 1) {
            return s;
        }
        // Recursive Step: Take the last character + recursively reverse the rest of the string.
        // Example: reverse("abc") -> reverse("bc") + 'a' -> (reverse("c") + 'b') + 'a' -> ('c' + 'b') + 'a' -> "cba"
        return reverseWordRec(s.substring(1)) + s.charAt(0);
    }

    /*
     * =============================
     * Option B — Secondary School
     * =============================
     */

    private static void subMenuOption2() {
        while (true) {
            clearScreen(); // Clear screen before showing the submenu
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Prime Numbers"+reset);
            System.out.println(cyan+"[B] Step-by-step Evaluation of Expression"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');

            // Clear screen AFTER selection, BEFORE executing the chosen option
            clearScreen();

            switch (choice) {
                case 'A' -> primeNumbers(); // Calculate primes using 3 sieve methods
                case 'B' -> evaluationOfExpression(); // Evaluate mathematical expression step-by-step
                case 'C' -> { // Return to main menu
                    System.out.println(green + "\nReturning the main menu."+reset);
                    System.out.println(yellow + "Press ENTER..." + reset);
                    SC.nextLine(); // Wait for confirmation
                    return; // Exit submenu loop
                }
            }
        }
    }

    /* Option B — Task 1: Prime Numbers using Sieve Algorithms */

    private static void primeNumbers() {
        // Loop allows user to repeat or return
        while(true) {
            System.out.println(cyan + "--- Prime Numbers (Sieve Algorithms) ---" + reset);
            int n = getPrimeNumberInput(); // Read upper limit n (>= 12)
            
            System.out.println(yellow + "\nCalculating primes up to " + n + "..." + reset);

            // --- Sieve of Eratosthenes ---
            long startE = System.nanoTime(); // Start timer
            int[] primesE = sieveEratosthenes(n); // Calculate primes using Eratosthenes
            long timeE = System.nanoTime() - startE; // End timer
            printPrimeSummary("Eratosthenes", primesE, timeE); // Print summary (first 3, last 2, time)
            
             // --- Sieve of Sundaram ---
            long startS = System.nanoTime();
            int[] primesS = sieveSundaram(n); // Calculate primes using Sundaram
            long timeS = System.nanoTime() - startS;
            printPrimeSummary("Sundaram", primesS, timeS);
            
             // --- Sieve of Atkin ---
            long startA = System.nanoTime();
            int[] primesA = sieveAtkin(n); // Calculate primes using Atkin
            long timeA = System.nanoTime() - startA;
            printPrimeSummary("Atkin", primesA, timeA);
            
            // Ask user to repeat or return
            System.out.print(yellow + "\nCalculate for another limit? (Y/N): " + reset);
            char repeatChoice = readMenuOption(SC, 'N', 'Y');
            if (repeatChoice == 'N') {
                break; // Exit loop
            }
             clearScreen(); // Clear screen before repeating
        }
    }

    // Helper to read the upper limit for prime calculation (n >= 12)
    private static int getPrimeNumberInput() {
        System.out.print(green + "Enter the upper limit n (must be >= 12): " + reset);
        // Use readInt helper for range validation
        return readInt(SC, 12, Integer.MAX_VALUE); // Ensures input is >= 12
    }
    
    // Helper to print the prime results summary (first 3, last 2, time)
    // Now accepts int[] instead of ArrayList
    private static void printPrimeSummary(String name, int[] p, long nanos) {
        System.out.printf("Method: %s | Time: %.3f ms | ", name, nanos / 1_000_000.0); // Display name and time
        // Check if the array is empty
        if (p.length == 0) { 
            System.out.println("No primes found."); 
            return; 
        }

        // Build string for the first 3 primes (or fewer if less than 3 found)
        String first3 = "";
        for(int i = 0; i < p.length && i < 3; i++) {
            first3 += p[i]; // Append prime
            if (i < 2 && i < p.length - 1) first3 += ","; // Add comma if not the last one
        }

        // Build string for the last 2 primes (or fewer if less than 2 found)
        String last2 = "";
        if (p.length >= 2) { // If at least 2 primes exist
            last2 = p[p.length-2] + "," + p[p.length-1]; // Get second-to-last and last
        } else if (p.length == 1) { // If only 1 prime exists
            last2 = String.valueOf(p[0]); // Get the only one
        }
        
        System.out.printf("First3: %s | Last2: %s%n", first3, last2); // Print the summary
    }

    // Sieve of Eratosthenes implementation returning int[]
    private static int[] sieveEratosthenes(int n) {
        if (n < 2) return new int[0]; // Return empty array if n < 2
        boolean[] isPrime = new boolean[n + 1]; // Boolean array to mark primes
        Arrays.fill(isPrime, true); // Assume all are prime initially
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime
        
        // Mark multiples of primes as not prime
        for (int p = 2; (long)p * p <= n; p++) { // Iterate up to sqrt(n)
            if (isPrime[p]) { // If p is prime
                for (long m = (long)p * p; m <= n; m += p) { // Mark its multiples
                    isPrime[(int)m] = false;
                }
            }
        }
        
        // Count the number of primes found
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) count++;
        }
        
        // Create the result array and populate it
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes[index++] = i;
        }
        return primes; // Return the array of primes
    }

    // Sieve of Sundaram implementation returning int[]
    private static int[] sieveSundaram(int n) {
        if (n < 2) return new int[0]; // Return empty array if n < 2
        int k = (n - 1) / 2; // Calculate limit based on n
        boolean[] marked = new boolean[k + 1]; // Array to mark numbers to exclude
        
        // Sundaram's marking logic
        for (int i = 1; i <= k; i++) {
            for (int j = i; ; j++) { // Loop indefinitely until break condition
                 int index = i + j + 2 * i * j;
                 if (index > k) break; // If index exceeds limit, break inner loop
                 marked[index] = true; // Mark the number
            }
        }
        
        // Count primes (including 2)
        int count = 1; // Start count at 1 for the prime number 2
        for (int i = 1; i <= k; i++) {
            if (!marked[i]) count++; // Count unmarked numbers
        }
        
        // Create result array and populate it
        int[] primes = new int[count];
        primes[0] = 2; // Add 2 as the first prime
        int index = 1;
        for (int i = 1; i <= k; i++) {
            if (!marked[i]) primes[index++] = 2 * i + 1; // Add 2*i+1 for unmarked i
        }
        return primes; // Return the array of primes
    }

    // Sieve of Atkin implementation returning int[]
    private static int[] sieveAtkin(int limit) {
        if (limit < 2) return new int[0]; // Return empty array if limit < 2
        boolean[] sieve = new boolean[limit + 1]; // Boolean array for potential primes
        int sqrt = (int)Math.sqrt(limit); // Calculate sqrt(limit) for optimization
        
        // Atkin's main logic using quadratic forms
        for (int x = 1; x <= sqrt; x++) {
            int x2 = x * x;
            for (int y = 1; y <= sqrt; y++) {
                int y2 = y * y;
                int n;
                // n = 4x^2 + y^2
                n = 4 * x2 + y2; 
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) sieve[n] ^= true; // Flip bit
                // n = 3x^2 + y^2
                n = 3 * x2 + y2; 
                if (n <= limit && n % 12 == 7) sieve[n] ^= true; // Flip bit
                // n = 3x^2 - y^2 (where x > y)
                n = 3 * x2 - y2; 
                if (x > y && n <= limit && n % 12 == 11) sieve[n] ^= true; // Flip bit
            }
        }
        
        // Eliminate multiples of squares of primes found so far
        for (int r = 5; r <= sqrt; r++) {
            if (sieve[r]) {
                for (int m = r * r; m <= limit; m += r * r) {
                    sieve[m] = false;
                }
            }
        }
        
        // Count primes (including 2 and 3)
        int count = 0;
        if (limit >= 2) count++; // Count 2
        if (limit >= 3) count++; // Count 3
        for (int a = 5; a <= limit; a++) {
            if (sieve[a]) count++; // Count primes marked true
        }
        
        // Create result array and populate it
        int[] primes = new int[count];
        int index = 0;
        if (limit >= 2) primes[index++] = 2; // Add 2
        if (limit >= 3) primes[index++] = 3; // Add 3
        for (int a = 5; a <= limit; a++) {
            if (sieve[a]) primes[index++] = a; // Add other primes
        }
        return primes; // Return the array of primes
    }

    /* Option B — Task 2: Step-by-step Evaluation of Expression (recursively) */

    private static void evaluationOfExpression() {
         // Loop allows user to repeat or return
        while(true) {
            System.out.println(cyan + "--- Step-by-step Expression Evaluator ---" + reset);
            System.out.print(green + "Enter expression using digits, +, -, x, :, (, ): " + reset);
            
            String expr = null; 
            // Inner loop for getting and validating the expression
            while (true) { 
                 expr = SC.nextLine();
                if (!exprIsValid(expr)) { // Validate expression format and structure
                    System.out.println(red + "Invalid expression format. Please re-enter: " + reset);
                    System.out.print(green + "Enter expression: " + reset);
                    continue; // Ask for input again
                }
                break; // Exit validation loop if expression is valid
            }

            try {
                // Evaluate the expression step-by-step and print each step
                exprEvaluate(expr); 
            } catch (ArithmeticException e) {
                // Handle division by zero error during evaluation
                System.out.println(red + "Error: Division by zero occurred during evaluation." + reset);
            } catch (Exception e) {
                 // Handle other potential errors during parsing/calculation
                 System.out.println(red + "An unexpected error occurred: " + e.getMessage() + reset);
            }

            // Ask user to repeat or return
            System.out.print(yellow + "\nEvaluate another expression? (Y/N): " + reset);
            char repeatChoice = readMenuOption(SC, 'N', 'Y');
            if (repeatChoice == 'N') {
                break; // Exit the main loop for this task
            }
             clearScreen(); // Clear screen before repeating
        }
    }
    
    // Validates the structure and characters of the mathematical expression
    private static boolean exprIsValid(String s) {
        if (s == null || s.trim().isEmpty()) return false; // Check for null or empty string
        // Check allowed characters (digits, operators, parentheses, whitespace)
        if (!s.matches("[0-9\\s+\\-xX×:/*()]+")) return false; // Added X and / * for flexibility

        String t = exprNormalize(s); // Normalize the expression (remove spaces, standardize operators)
        if (t.isEmpty()) return false;
        
        // Check for operators at the very start (except unary minus) or end
        if (isOp(t.charAt(0)) && t.charAt(0) != '-') return false; 
        if (isOp(t.charAt(t.length() - 1))) return false;
        
        // Check for invalid patterns like ")(" or "5(" or ")3"
        if (t.contains(")(") || t.matches(".*\\d\\(.*") || t.matches(".*\\)\\d.*")) return false;

        // Check for balanced parentheses
        int balance = 0;
        for (int i=0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false; // More closing than opening parens found
        }
        if (balance != 0) return false; // Unbalanced parens at the end

        // Check for consecutive operators (allow unary minus after an operator or opening paren)
        for (int i = 1; i < t.length(); i++) {
            char current = t.charAt(i);
            char previous = t.charAt(i - 1);
            if (isOp(current) && current != '-') { // If current is an operator (not minus)
                 if (isOp(previous) || previous == '(') return false; // Previous cannot be operator or '('
            } else if (isOp(current) && current == '-') { // If current is minus
                 if (isOp(previous) && previous != '(') return false; // Previous cannot be operator unless '('
            }
        }
        return true; // Expression is structurally valid
    }

    // Normalizes expression string: removes spaces, replaces x,× with *, : with /
    private static String exprNormalize(String s) {
        return s.replace('×','*') // Replace Unicode multiplication
                .replace('x','*') // Replace 'x' multiplication
                .replace('X','*') // Replace 'X' multiplication
                .replace(':','/') // Replace ':' division
                .replaceAll("\\s+", ""); // Remove all whitespace
    }

    // Denormalizes expression string for display: replaces * with x, / with :
    private static String exprDenorm(String s) {
        return s.replace('*','x').replace('/',':'); // Use 'x' and ':' for output
    }

    // Helper to check if a character is one of the four basic operators
    private static boolean isOp(char c) {
        return c=='+' || c=='-' || c=='*' || c=='/';
    }

    // Initiates the recursive evaluation and prints the initial normalized step
    private static void exprEvaluate(String expr) {
        String normalizedExpr = exprNormalize(expr);
        System.out.println("= " + exprDenorm(normalizedExpr)); // Print the starting expression (denormalized)
        String finalResult = exprReduce(normalizedExpr); // Start recursive reduction
        // Print the final result only if it's different from the initial step (avoid redundant print)
        if (!normalizedExpr.equals(finalResult)) { 
             System.out.println("= " + exprDenorm(finalResult));
        }
    }

    // Recursive function to reduce the expression step-by-step following precedence rules
    private static String exprReduce(String s) {
        s = exprStripOuter(s); // Remove outermost unnecessary parentheses e.g., ((5+3)) -> 5+3
        
        // Base Case: If the string is just a number (possibly negative), return it.
        if (s.matches("^-?\\d+$")) return s; 

        // Step 1: Find and evaluate the innermost (or rightmost innermost) parentheses
        int deepestOpenParenIndex = -1;
        int matchingCloseParenIndex = -1;
        int currentDepth = 0;
        int maxDepth = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                currentDepth++;
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                    deepestOpenParenIndex = i; // Store index of the deepest '(' found so far
                }
            } else if (s.charAt(i) == ')') {
                if (currentDepth == maxDepth && deepestOpenParenIndex != -1) {
                    // Found the matching ')' for the deepest '('
                    matchingCloseParenIndex = i;
                    // Evaluate the expression inside these parentheses recursively
                    String insideResult = exprReduce(s.substring(deepestOpenParenIndex + 1, matchingCloseParenIndex));
                    // Construct the next state of the expression string
                    String nextExpression = s.substring(0, deepestOpenParenIndex) + insideResult + s.substring(matchingCloseParenIndex + 1);
                    System.out.println("= " + exprDenorm(nextExpression)); // Print the step
                    return exprReduce(nextExpression); // Recursively reduce the new expression
                }
                 currentDepth--;
            }
        }
        
        // Step 2: Find and evaluate the first multiplication or division (left-to-right)
        int multiplyDivideIndex = -1;
         for (int i = 0; i < s.length(); i++) {
             if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                 multiplyDivideIndex = i;
                 break; // Found the first one
             }
         }
         if (multiplyDivideIndex != -1) {
             String nextExpression = exprApplyOp(s, multiplyDivideIndex); // Apply the operator
             System.out.println("= " + exprDenorm(nextExpression)); // Print the step
             return exprReduce(nextExpression); // Recursively reduce the new expression
         }

        // Step 3: Find and evaluate the first addition or subtraction (left-to-right)
         // Start from index 1 to ignore potential leading unary minus
         int addSubtractIndex = -1;
         for (int i = 1; i < s.length(); i++) { 
             if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                 // Make sure it's a binary operator, not part of a negative number following another operator or '('
                 if (!isOp(s.charAt(i-1)) && s.charAt(i-1) != '(') {
                     addSubtractIndex = i;
                     break; // Found the first one
                 }
             }
         }
         if (addSubtractIndex != -1) {
             String nextExpression = exprApplyOp(s, addSubtractIndex); // Apply the operator
             System.out.println("= " + exprDenorm(nextExpression)); // Print the step
             return exprReduce(nextExpression); // Recursively reduce the new expression
         }

        // If no more operations can be performed, return the current string (should be a number)
        return s; 
    }

    // Applies the operator at index `opIndex` to the numbers immediately left and right
    private static String exprApplyOp(String s, int opIndex) {
        // Find the start index (L) of the left number
        int L = opIndex - 1;
        while (L >= 0 && Character.isDigit(s.charAt(L))) L--; // Move left past digits
        // Include the negative sign if it's the start or follows an operator/'('
        if (L >= 0 && s.charAt(L) == '-' && (L == 0 || isOp(s.charAt(L-1)) || s.charAt(L-1) == '(')) {
            // It's part of the number, keep index L as is
        } else {
            L++; // It's not part of the number, move index one position right
        }

        // Find the end index (R) of the right number
        int R = opIndex + 1;
        // Include the negative sign if it immediately follows the operator
        if (R < s.length() && s.charAt(R) == '-') { 
            R++;
        }
        while (R < s.length() && Character.isDigit(s.charAt(R))) R++; // Move right past digits
        
        // Extract left and right number strings
        String leftStr = s.substring(L, opIndex);
        String rightStr = s.substring(opIndex + 1, R);

        // Parse numbers to long to handle potentially large intermediate values
        long leftNum = Long.parseLong(leftStr);
        long rightNum = Long.parseLong(rightStr);
        long resultNum;

        // Perform the calculation based on the operator
        switch (s.charAt(opIndex)) {
            case '*' -> resultNum = leftNum * rightNum;
            case '/' -> {
                if (rightNum == 0) throw new ArithmeticException("Division by zero"); // Handle division by zero
                resultNum = leftNum / rightNum; // Integer division
            }
            case '+' -> resultNum = leftNum + rightNum;
            case '-' -> resultNum = leftNum - rightNum; 
            default  -> throw new IllegalArgumentException("Invalid operator: " + s.charAt(opIndex)); // Should not happen
        }
        
        String resultStr = String.valueOf(resultNum); // Convert result back to string

        // Construct the new expression string by replacing the operation with the result
        return s.substring(0, L) + resultStr + s.substring(R);
    }

    // Removes matching outermost parentheses if they span the entire string
    // e.g., "(5+3)" -> "5+3", "((5+3))" -> "(5+3)", "(5+3)*(2)" -> "(5+3)*(2)"
    private static String exprStripOuter(String s) {
        while (s.startsWith("(") && s.endsWith(")")) {
            int balance = 0; 
            boolean wrapsEntireString = true;
            // Check if the parentheses actually wrap the whole expression
            for (int i = 0; i < s.length() - 1; i++) { // Loop until the second to last character
                if (s.charAt(i) == '(') balance++;
                else if (s.charAt(i) == ')') balance--;
                if (balance == 0 && i < s.length() - 2) { // Found a closing paren before the end
                    wrapsEntireString = false; 
                    break; 
                }
            }
            // If they wrap the entire string and balance is correct (implicitly checked by loop finish)
            if (wrapsEntireString && balance > 0) { // Balance should be > 0 before the last ')'
                s = s.substring(1, s.length() - 1); // Remove the outer parentheses
            } else {
                break; // Parentheses do not wrap the whole string, stop stripping
            }
        }
        return s; // Return stripped or original string
    }

    /*
     * =============================
     * Option C — High School
     * =============================
     */
     
    private static void subMenuOption3() {
        while (true) {
             clearScreen(); // Clear screen before showing the submenu
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Statistical Information about an Array"+reset);
            System.out.println(cyan+"[B] Distance between Two Arrays"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');
            
            // Clear screen AFTER selection, BEFORE executing the chosen option
            clearScreen();
            
            switch (choice) {
                case 'A' -> statisticalInformation(); // Calculate array statistics
                case 'B' -> distanceBetweenTwoArrays(); // Calculate distances between vectors
                case 'C' -> { // Return to main menu
                    System.out.println(green + "\nReturning the main menu."+reset);
                    System.out.println(yellow + "Press ENTER..." + reset); 
                    SC.nextLine(); // Wait for confirmation
                    return; // Exit submenu loop
                }
            }
        }
    }
    
/*
 * =============================
 * Option C — High School
 * =============================
 */

/** Displays the High School menu and runs the chosen task. */
private static void subMenuOption3() {
    while (true) {
        clearScreen();
        System.out.println(red+"********************************"+reset);
        System.out.println(cyan+"[A] Statistical Information about an Array"+reset);
        System.out.println(cyan+"[B] Distance between Two Arrays"+reset);
        System.out.println(cyan+"[C] Return To Main Menu"+reset);
        System.out.println(red+"********************************"+reset);
        System.out.print(green+"Please select an option to continue: "+reset);
        char choice = readMenuOption(SC, 'A', 'C');

        clearScreen();

        switch (choice) {
            case 'A' -> statisticalInformation();
            case 'B' -> distanceBetweenTwoArrays();
            case 'C' -> {
                System.out.println(green + "\nReturning the main menu."+reset);
                System.out.println(yellow + "Press ENTER..." + reset);
                SC.nextLine();
                return;
            }
        }
    }
}

/** Reads an array and shows its median, arithmetic, geometric and harmonic means. */
private static void statisticalInformation() {
    while(true) {
        arrC.clear();
        System.out.println(yellow + "=== Statistical Information about an Array ===" + reset);
        int n = arraySize();

        System.out.println(cyan + "Enter " + n + " real numbers (use '.' for decimals):" + reset);

        while (arrC.size() < n) {
            String line = SC.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            int tokensProcessedThisLine = 0;

            for (String token : tokens) {
                if (arrC.size() >= n) break;
                try {
                    double value = Double.parseDouble(token);
                    arrC.add(value);
                    tokensProcessedThisLine++;
                } catch (NumberFormatException ex) {
                    System.out.println(red + "Invalid number: '" + token + "'." + reset);
                }
            }

            if (arrC.size() >= n && tokensProcessedThisLine < tokens.length) {
                String[] extras = Arrays.copyOfRange(tokens, tokensProcessedThisLine, tokens.length);
                System.out.println(red + "Warning: extra values ignored: " + String.join(" ", extras) + reset);
            }
        }

        double median = calculateMedian();
        double arithmetic = calculateArithmeticMean();
        double geometric = calculateGeometricMean();
        double harmonic = calculateHarmonicMean();

        System.out.println(green + "\nResults:" + reset);
        System.out.printf(cyan + "Median:             " + reset + "%.6f%n", median);
        System.out.printf(cyan + "Arithmetic mean:    " + reset + "%.6f%n", arithmetic);
        if (Double.isNaN(geometric)) System.out.println(cyan + "Geometric mean:     " + reset + red + "undefined" + reset);
        else System.out.printf(cyan + "Geometric mean:     " + reset + "%.6f%n", geometric);
        if (Double.isNaN(harmonic)) System.out.println(cyan + "Harmonic mean:      " + reset + red + "undefined" + reset);
        else System.out.printf(cyan + "Harmonic mean:      " + reset + "%.6f%n", harmonic);

        System.out.print(yellow + "\nCalculate for another array? (Y/N): " + reset);
        char repeatChoice = readMenuOption(SC, 'N', 'Y');
        if (repeatChoice == 'N') break;
        clearScreen();
    }
}

/** Gets the array size from the user. */
private static int arraySize() {
    System.out.print(yellow + "Enter array size n (must be >= 1): " + reset);
    return readInt(SC, 1, Integer.MAX_VALUE);
}

/** Finds the median value of the array. */
private static double calculateMedian() {
    if (arrC.isEmpty()) return 0.0;
    ArrayList<Double> sortedCopy = new ArrayList<>(arrC);
    sortedCopy.sort(Double::compareTo);
    int n = sortedCopy.size();
    if (n % 2 == 1) return sortedCopy.get(n / 2);
    return (sortedCopy.get(n / 2 - 1) + sortedCopy.get(n / 2)) / 2.0;
}

/** Calculates arithmetic mean (average). */
private static double calculateArithmeticMean() {
    if (arrC.isEmpty()) return 0.0;
    double sum = 0.0;
    for (double v : arrC) sum += v;
    return sum / arrC.size();
}

/** Calculates geometric mean. Returns NaN if any value ≤ 0. */
private static double calculateGeometricMean() {
    if (arrC.isEmpty()) return 0.0;
    for (double v : arrC) if (v <= 0.0) return Double.NaN;
    double logSum = 0.0;
    for (double v : arrC) logSum += Math.log(v);
    return Math.exp(logSum / arrC.size());
}

/** Calculates harmonic mean using recursion. Returns NaN if any value is 0. */
private static double calculateHarmonicMean() {
    if (arrC.isEmpty()) return 0.0;
    for (double v : arrC) if (v == 0.0) return Double.NaN;
    double sumOfReciprocals = harmonicRec(arrC, 0);
    return arrC.size() / sumOfReciprocals;
}

/** Recursive helper that sums 1/x for harmonic mean. */
private static double harmonicRec(ArrayList<Double> a, int index) {
    if (index == a.size()) return 0.0;
    return (1.0 / a.get(index)) + harmonicRec(a, index + 1);
}

/** Reads two arrays and shows Manhattan, Euclidean and Cosine results. */
private static void distanceBetweenTwoArrays() {
    while(true) {
        System.out.println(yellow + "=== Distance between Two Arrays ===" + reset);
        int d = getDimension();

        System.out.println(cyan + "Enter first vector A (0-9):" + reset);
        int[] vectorA = readVector(d, "A");

        System.out.println(cyan + "Enter second vector B (0-9):" + reset);
        int[] vectorB = readVector(d, "B");

        double manhattan = calculateManhattanDistance(vectorA, vectorB);
        double euclidean = calculateEuclideanDistance(vectorA, vectorB);
        Double cosine = calculateCosineSimilarity(vectorA, vectorB);

        System.out.println(green + "\nResults:" + reset);
        System.out.printf(cyan + "Manhattan distance: " + reset + "%.6f%n", manhattan);
        System.out.printf(cyan + "Euclidean distance: " + reset + "%.6f%n", euclidean);
        if (cosine == null) System.out.println(cyan + "Cosine similarity:  " + reset + red + "undefined" + reset);
        else System.out.printf(cyan + "Cosine similarity:  " + reset + "%.6f%n", cosine);

        System.out.print(yellow + "\nCalculate for other vectors? (Y/N): " + reset);
        char repeatChoice = readMenuOption(SC, 'N', 'Y');
        if (repeatChoice == 'N') break;
        clearScreen();
    }
}

/** Reads the vector dimension. */
private static int getDimension() {
    System.out.print(yellow + "Enter vector dimension d (must be >= 1): " + reset);
    return readInt(SC, 1, Integer.MAX_VALUE);
}

/** Reads a vector of given length with digits 0–9. */
private static int[] readVector(int d, String vectorName) {
    int[] vector = new int[d];
    int elementsRead = 0;

    while (elementsRead < d) {
        String line = SC.nextLine().trim();
        if (line.isEmpty()) continue;

        String[] tokens = line.split("\\s+");
        int tokensProcessedThisLine = 0;

        for (String token : tokens) {
            if (elementsRead >= d) break;
            if (!token.matches("[0-9]")) {
                System.out.println(red + "Invalid: '" + token + "'. Must be 0–9." + reset);
                continue;
            }
            try {
                int value = Integer.parseInt(token);
                vector[elementsRead++] = value;
                tokensProcessedThisLine++;
            } catch (NumberFormatException e) {
                System.out.println(red + "Error parsing '" + token + "'." + reset);
            }
        }

        if (elementsRead >= d && tokensProcessedThisLine < tokens.length) {
            String[] extras = Arrays.copyOfRange(tokens, tokensProcessedThisLine, tokens.length);
            System.out.println(red + "Warning: extra values ignored for " + vectorName + reset);
        }
    }
    return vector;
}

/** Calculates Manhattan distance (sum of absolute differences). */
private static double calculateManhattanDistance(int[] a, int[] b) {
    long sumOfAbsDiff = 0L;
    for (int i = 0; i < a.length; i++) sumOfAbsDiff += Math.abs(a[i] - b[i]);
    return (double) sumOfAbsDiff;
}

/** Calculates Euclidean distance (square root of squared differences). */
private static double calculateEuclideanDistance(int[] a, int[] b) {
    double sumOfSquares = 0.0;
    for (int i = 0; i < a.length; i++) {
        double diff = a[i] - b[i];
        sumOfSquares += diff * diff;
    }
    return Math.sqrt(sumOfSquares);
}

/** Calculates cosine similarity between two vectors. */
private static Double calculateCosineSimilarity(int[] a, int[] b) {
    long dotProduct = 0L;
    long magnitudeASquared = 0L;
    long magnitudeBSquared = 0L;

    for (int i = 0; i < a.length; i++) {
        dotProduct += (long) a[i] * b[i];
        magnitudeASquared += (long) a[i] * a[i];
        magnitudeBSquared += (long) b[i] * b[i];
    }

    if (magnitudeASquared == 0L || magnitudeBSquared == 0L) return null;
    return dotProduct / (Math.sqrt(magnitudeASquared) * Math.sqrt(magnitudeBSquared));
}

    
    /*
     * =============================
     * Option D — University (Connect Four) - STUB
     * =============================
     */
     
    // Placeholder for Option D functionality
    private static void subMenuOption4() {
         System.out.println(yellow + "--- Connect Four ---" + reset);
         System.out.println(red + "This feature (Option D) is not implemented yet." + reset);
         System.out.println(yellow + "Press ENTER to return to main menu..." + reset);
         SC.nextLine(); // Wait for user
    }

    // Stubs related to Connect Four (as per original code structure)
    private static boolean checkWin() { return false; } 
    private static boolean checkDraw() { return false; } 

    /*
     * =============================
     * Program Entry Point
     * =============================
     */
    public static void main(String[] args) {
        welcomeMessage(); // Show welcome screen
        mainMenu(); // Start the main menu loop
        SC.close(); // Close the scanner when the program exits
    }

}
