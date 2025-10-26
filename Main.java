import java.time.LocalDate;
import java.util.Arrays; // Sieve algoritmaları için gerekli (Arrays.fill)
import java.util.Scanner;
// ArrayList, Collectors, Supplier, Set vb. import'ları kaldırıldı.

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
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'E');

            switch (choice) {
                case 'A' -> subMenuOption1();
                case 'B' -> subMenuOption2();
                case 'C' -> subMenuOption3();
                case 'D' -> subMenuOption4();
                case 'E' -> {
                    System.out.println(green + "\nTurning the program off..." + reset);
                    System.out.println(red + "Thank you for using our program!" + reset);
                    return;
                }
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
            try {
                int val = Integer.parseInt(s);
                if (val < min || val > max) {
                    System.out.print(red + "Please enter a number between " + min + " and " + max + ": " + reset);
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print(red + "Please enter a number between " + min + " and " + max + ": " + reset);
            }
        }
    }

    /* Optional helpers for menu I/O */
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
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');

            switch (choice) {
                case 'A' -> ageAndZodiacSignDetection();
                case 'B' -> reverseTheWords();
                case 'C' -> {
                    System.out.println(green + "\nReturning the main menu."+reset);
                    return;
                }
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
            if (prevMonth == 0) { prevMonth = 12; prevYear -= 1; }
            int dim = getDaysInMonth(prevMonth, prevYear);

            days += dim;
            months -= 1;

            tempMonth = prevMonth;
            tempYear = prevYear;
        }

        if (months < 0) { years -= 1; months += 12; }

        System.out.println(cyan + "Your age: " + years + " years, " + months + " months, " + days + " days" + reset);
        System.out.println(red + "Your zodiac sign is " + getZodiacString(birthDay, birthMonth) + "." + reset);
        if (birthYear<=1500) {
            System.out.println(red + " Wow... You must be a time traveler! " + reset);
            System.out.println(yellow + "Are you sure you were born in " + birthYear + "?" + reset);
        }
        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
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
            if (month == 2) max = IsLeapYear(year) ? 29 : 28;
            else if (month == 4 || month == 6 || month == 9 || month == 11) max = 30;
            else max = 31;

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

    private static int getCurrentDay() { return LocalDate.now().getDayOfMonth(); }
    private static int getCurrentMonth() { return LocalDate.now().getMonthValue(); }
    private static int getCurrentYear() { return LocalDate.now().getYear(); }

    /* Option A — Task 2: Reverse the Words (recursive) */
    
    private static void reverseTheWords() {
        String input = getTextInput(SC); 
        if (!isReversed(input)) {
            System.out.println(red + "Input must contain at least 2 characters." + reset);
        } else {
            String output = createReverseOutput(input);
            System.out.println(yellow + "\nOriginal Text:\n" + reset + input);
            System.out.println(cyan + "Reversed Words Text:\n" + reset + output);
        }
        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }
    
    private static String getTextInput(Scanner scan) {
        System.out.print(green + "Enter the text to reverse words in (Türkçe karakterler dahil): " + reset);
        while (true) {
            String s = scan.nextLine();
            if (s.trim().isEmpty()) {
                System.out.print(red + "Input cannot be empty. Please enter your text: " + reset);
                continue;
            }
            return s;
        }
    }
    
    // StringBuilder KULLANILMAYAN versiyon
    private static String createReverseOutput(String s) {
        String result = ""; // StringBuilder yerine basit String birleştirme
        int i = 0;
        
        while (i < s.length()) {
            char startChar = s.charAt(i);

            if (Character.isLetter(startChar)) {
                int j = i;
                while (j < s.length() && Character.isLetter(s.charAt(j))) {
                    j++;
                }
                String word = s.substring(i, j);
                
                if (word.length() >= 2) {
                    result += reverseWordRec(word); // Basit birleştirme
                } else {
                    result += word; // Basit birleştirme
                }
                i = j; 
            } else {
                int j = i;
                while (j < s.length() && !Character.isLetter(s.charAt(j))) {
                    j++;
                }
                String nonWord = s.substring(i, j);
                result += nonWord; // Basit birleştirme
                i = j;
            }
        }
        return result;
    }

    private static boolean isReversed(String s) {
        return s != null && s.trim().length() >= 2;
    }
    
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

    private static void subMenuOption2() {
        while (true) {
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Prime Numbers"+reset);
            System.out.println(cyan+"[B] Step-by-step Evaluation of Expression"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');

            switch (choice) {
                case 'A' -> primeNumbers();
                case 'B' -> evaluationOfExpression();
                case 'C' -> {
                    System.out.println(green + "\nReturning the main menu."+reset);
                    return;
                }
            }
            clearScreen();
        }
    }

    /* Option B — Task 1: Prime Numbers */

    private static void primeNumbers() {
        clearScreen();
        System.out.println(cyan + "--- Prime Numbers (Sieve Algorithms) ---" + reset);
        int n = getPrimeNumberInput();
        
        System.out.println(yellow + "\nCalculating primes up to " + n + "..." + reset);

        long startE = System.nanoTime();
        int[] primesE = sieveEratosthenes(n); // ArrayList yerine int[]
        long timeE = System.nanoTime() - startE;
        printPrimeSummary("Eratosthenes", primesE, timeE);
        
        long startS = System.nanoTime();
        int[] primesS = sieveSundaram(n); // ArrayList yerine int[]
        long timeS = System.nanoTime() - startS;
        printPrimeSummary("Sundaram", primesS, timeS);
        
        long startA = System.nanoTime();
        int[] primesA = sieveAtkin(n); // ArrayList yerine int[]
        long timeA = System.nanoTime() - startA;
        printPrimeSummary("Atkin", primesA, timeA);
        
        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    private static int getPrimeNumberInput() {
        System.out.print(green + "Enter the upper limit (n >= 12): " + reset);
        while (true) {
            String s = SC.nextLine().trim();
            if (!s.matches("\\d+")) {
                System.out.print(red + "Invalid input. Please enter a number (n >= 12): " + reset);
                continue;
            }
            try {
                int val = Integer.parseInt(s);
                if (val < 12) {
                    System.out.print(red + "Please enter a number >= 12: " + reset);
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print(red + "Invalid number (too large). Please re-enter: " + reset);
            }
        }
    }
    
    // ArrayList yerine int[] (temel dizi) alacak şekilde güncellendi
    private static void printPrimeSummary(String name, int[] p, long nanos) {
        System.out.printf("Method: %s | Time: %.3f ms | ", name, nanos / 1_000_000.0);
        if (p.length == 0) { System.out.println("No primes."); return; }

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

    // ArrayList yerine int[] (temel dizi) döndürecek şekilde güncellendi
    private static int[] sieveEratosthenes(int n) {
        if (n < 2) return new int[0]; // Boş temel dizi
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false; if (n >= 1) isPrime[1] = false;
        
        for (int p = 2; (long)p * p <= n; p++)
            if (isPrime[p])
                for (long m = (long)p * p; m <= n; m += p) isPrime[(int)m] = false;
        
        // 1. Asalları say
        int count = 0;
        for (int i = 2; i <= n; i++) if (isPrime[i]) count++;
        
        // 2. Diziye yerleştir
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i <= n; i++) if (isPrime[i]) primes[index++] = i;
        
        return primes;
    }

    // ArrayList yerine int[] (temel dizi) döndürecek şekilde güncellendi
    private static int[] sieveSundaram(int n) {
        if (n < 2) return new int[0]; // Boş temel dizi
        int k = (n - 1) / 2;
        boolean[] marked = new boolean[k + 1];
        
        for (int i = 1; i <= k; i++)
            for (int j = i; i + j + 2 * i * j <= k; j++)
                marked[i + j + 2 * i * j] = true;
        
        // 1. Asalları say (2'yi de sayarak)
        int count = 1; // 2 için
        for (int i = 1; i <= k; i++) if (!marked[i]) count++;
        
        // 2. Diziye yerleştir
        int[] primes = new int[count];
        primes[0] = 2;
        int index = 1;
        for (int i = 1; i <= k; i++) if (!marked[i]) primes[index++] = 2 * i + 1;
        
        return primes;
    }

    // ArrayList yerine int[] (temel dizi) döndürecek şekilde güncellendi
    private static int[] sieveAtkin(int limit) {
        if (limit < 2) return new int[0]; // Boş temel dizi
        boolean[] sieve = new boolean[limit + 1];
        int sqrt = (int)Math.sqrt(limit);
        
        for (int x = 1; x <= sqrt; x++) {
            int x2 = x * x;
            for (int y = 1; y <= sqrt; y++) {
                int y2 = y * y, n;
                n = 4 * x2 + y2; if (n <= limit && (n % 12 == 1 || n % 12 == 5)) sieve[n] ^= true;
                n = 3 * x2 + y2; if (n <= limit &&  n % 12 == 7)                 sieve[n] ^= true;
                n = 3 * x2 - y2; if (x > y && n <= limit && n % 12 == 11)        sieve[n] ^= true;
            }
        }
        for (int r = 5; r <= sqrt; r++)
            if (sieve[r])
                for (int m = r * r; m <= limit; m += r * r) sieve[m] = false;
        
        // 1. Asalları say (2 ve 3'ü de sayarak)
        int count = 0;
        if (limit >= 2) count++;
        if (limit >= 3) count++;
        for (int a = 5; a <= limit; a++) if (sieve[a]) count++;
        
        // 2. Diziye yerleştir
        int[] primes = new int[count];
        int index = 0;
        if (limit >= 2) primes[index++] = 2;
        if (limit >= 3) primes[index++] = 3;
        for (int a = 5; a <= limit; a++) if (sieve[a]) primes[index++] = a;
        
        return primes;
    }

    /* Option B — Task 2: Step-by-step Evaluation of Expression */

    private static void evaluationOfExpression() {
        clearScreen();
        System.out.println(cyan + "--- Step-by-step Evaluation of Expression ---" + reset);
        System.out.print(green + "Enter an expression with digits and + - x × : ( ): " + reset);
        
        while (true) {
            String expr = SC.nextLine();

            if (!exprIsValid(expr)) {
                System.out.println(red + "Invalid input. Please re-enter a valid expression." + reset);
                System.out.print(green + "Enter an expression with digits and + - x × : ( ): " + reset);
                continue;
            }

            try {
                exprEvaluate(expr);
                break;
            } catch (ArithmeticException e) {
                System.out.println(red + "Error: Division by zero! Please re-enter a valid expression." + reset);
                System.out.print(green + "Enter an expression with digits and + - x × : ( ): " + reset);
            }
        }
        
        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }
    
    private static boolean exprIsValid(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        if (!s.matches("[0-9\\s\\+\\-x×:()]+")) return false;

        String t = exprNormalize(s);
        if (t.isEmpty()) return false;
        
        if (isOp(t.charAt(0)) && t.charAt(0) != '-') return false; 
        if (isOp(t.charAt(t.length() - 1))) return false;
        
        if (t.contains(")(") || t.matches(".*\\d\\(.*") || t.matches(".*\\)\\d.*")) return false;

        int bal = 0;
        for (int i=0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '(') bal++;
            else if (c == ')') bal--;
            if (bal < 0) return false;
        }
        if (bal != 0) return false;

        for (int i = 1; i < t.length(); i++) {
            char c = t.charAt(i), p = t.charAt(i - 1);
            if (isOp(c)) {
                if (p == '(' && c != '-') return false; 
                if (isOp(p) && c != '-') return false; 
            }
        }
        return true;
    }

    private static String exprNormalize(String s) {
        return s.replace('×','*')
                .replace('x','*')
                .replace(':','/')
                .replace('−','-')
                .replaceAll("\\s+", "");
    }

    private static String exprDenorm(String s) {
        return s.replace('*','x').replace('/',':');
    }

    private static boolean isOp(char c) {
        return c=='+'||c=='-'||c=='*'||c=='/';
    }

    private static void exprEvaluate(String expr) {
        String e = exprNormalize(expr);
        System.out.println("= " + exprDenorm(e)); 
        String result = exprReduce(e);
        if (!e.equals(result)) { 
             System.out.println("= " + exprDenorm(result));
        }
    }

    private static String exprReduce(String s) {
        s = exprStripOuter(s);
        if (s.matches("^-?\\d+$")) return s;

        int open = -1, close = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') open = i;
            else if (s.charAt(i) == ')' && open != -1) { 
                close = i; 
                String inside = exprReduce(s.substring(open + 1, close));
                String next = s.substring(0, open) + inside + s.substring(close + 1);
                System.out.println("= " + exprDenorm(next));
                return exprReduce(next);
            }
        }

        int opIdx = -1;
         for (int i = 0; i < s.length(); i++) {
             if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                 opIdx = i;
                 break;
             }
         }
         if (opIdx != -1) {
             String next = exprApplyOp(s, opIdx);
             System.out.println("= " + exprDenorm(next));
             return exprReduce(next);
         }

         opIdx = -1;
         for (int i = 1; i < s.length(); i++) { 
             if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                 if (!isOp(s.charAt(i-1)) && s.charAt(i-1) != '(') {
                     opIdx = i;
                     break;
                 }
             }
         }
         if (opIdx != -1) {
             String next = exprApplyOp(s, opIdx);
             System.out.println("= " + exprDenorm(next));
             return exprReduce(next);
         }

        return s;
    }

    private static String exprApplyOp(String s, int idx) {
        int L = idx - 1;
        while (L >= 0 && Character.isDigit(s.charAt(L))) L--;
        if (L >= 0 && s.charAt(L) == '-' && (L == 0 || isOp(s.charAt(L-1)) || s.charAt(L-1) == '(')) {
        } else {
            L++; 
        }

        int R = idx + 1;
        if (R < s.length() && s.charAt(R) == '-') { 
            R++;
        }
        while (R < s.length() && Character.isDigit(s.charAt(R))) R++;
        
        String left = s.substring(L, idx);
        String right = s.substring(idx + 1, R);

        long a = Long.parseLong(left);
        long b = Long.parseLong(right);
        long res;

        switch (s.charAt(idx)) {
            case '*' -> res = a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                res = a / b;
            }
            case '+' -> res = a + b;
            default  -> res = a - b; 
        }
        
        String resultStr = String.valueOf(res);
        if (res < 0 && L > 0 && s.charAt(L-1) == '+') {
             return s.substring(0, L-1) + resultStr + s.substring(R);
        }

        return s.substring(0, L) + resultStr + s.substring(R);
    }

    private static String exprStripOuter(String s) {
        while (s.startsWith("(") && s.endsWith(")")) {
            int bal = 0; boolean ok = true;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') bal++;
                else if (s.charAt(i) == ')') bal--;
                if (bal == 0 && i < s.length() - 1) { ok = false; break; }
            }
            if (ok) s = s.substring(1, s.length() - 1);
            else break;
        }
        return s;
    }

    /*
     * =============================
     * Option C — High School
     * =============================
     */
     
    private static void subMenuOption3() {
        while (true) {
            System.out.println(red+"********************************"+reset);
            System.out.println(cyan+"[A] Statistical Information about an Array"+reset);
            System.out.println(cyan+"[B] Distance between Two Arrays"+reset);
            System.out.println(cyan+"[C] Return To Main Menu"+reset);
            System.out.println(red+"********************************"+reset);
            System.out.print(green+"Please select an option to continue: "+reset);
            char choice = readMenuOption(SC, 'A', 'C');
            
            switch (choice) {
                case 'A' -> statisticalInformation();
                case 'B' -> distanceBetweenTwoArrays();
                case 'C' -> {
                    System.out.println(green + "\nReturning the main menu."+reset);
                    return;
                }
            }
            clearScreen();
        }
    }
    
    private static void statisticalInformation() {
         System.out.println(yellow + "This feature is not implemented yet. Press ENTER to return." + reset);
         SC.nextLine();
    }
    
    private static void distanceBetweenTwoArrays() {
         System.out.println(yellow + "This feature is not implemented yet. Press ENTER to return." + reset);
         SC.nextLine();
    }

    private static int arraySize() { return 0; }
    // ArrayList yerine int[] (temel dizi) döndürecek şekilde güncellendi
    private static int[] getElement() { return new int[0]; } 
    private static double calculateMedian() { return 0.0; }
    private static double calculateArithmeticMedian() { return 0.0; }
    private static double calculateGeometricMedian() { return 0.0; }
    private static double calculateHarmonicMedian() { return 0.0; }
    private static int getDimension() { return 0; }


    /*
     * =============================
     * Option D — University (Connect Four)
     * =============================
     */

    private static void subMenuOption4() {
         System.out.println(yellow + "This feature is not implemented yet. Press ENTER to return." + reset);
         SC.nextLine();
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
        welcomeMessage();
        mainMenu();
        SC.close(); 
    }
}