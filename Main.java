import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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

    // Basit sayısal okuma (B menüsü için)
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

    // Kelime sırasını koruyup her kelimeyi özyinelemeyle ters çevir
    private static String createReverseOutput(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            // whitespace'leri aynen aktar
            int j = i;
            while (j < s.length() && Character.isWhitespace(s.charAt(j))) j++;
            sb.append(s, i, j);
            if (j >= s.length()) break;

            // kelimeyi bul
            int k = j;
            while (k < s.length() && !Character.isWhitespace(s.charAt(k))) k++;
            String word = s.substring(j, k);
            sb.append(reverseWordRec(word));
            i = k;
        }
        return sb.toString();
    }

    private static boolean isReversed(String s) {
        return s != null && s.trim().length() >= 2;
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

    private static void printPrimeSummary(String name, R r) {
        System.out.printf("Method: %s | Time: %.3f ms | ", name, r.nanos / 1_000_000.0);
        List<Integer> p = r.list;
        if (p.isEmpty()) { System.out.println("No primes."); return; }
        String first3 = p.stream().limit(3).map(String::valueOf).collect(Collectors.joining(","));
        String last2 = p.size() >= 2 ? p.get(p.size()-2) + "," + p.get(p.size()-1) : String.valueOf(p.get(p.size()-1));
        System.out.printf("First3: %s | Last2: %s%n", first3, last2);
    }

    private static void printDiff(String name, List<Integer> ref, List<Integer> other) {
        Set<Integer> missing = new LinkedHashSet<>(ref);   missing.removeAll(other);
        Set<Integer> extra   = new LinkedHashSet<>(other); extra.removeAll(ref);
        if (missing.isEmpty() && extra.isEmpty())
            System.out.println("  " + name + ": (no difference)");
        else {
            System.out.println("  " + name + " missing vs ref: " + missing);
            System.out.println("  " + name + " extra   vs ref: " + extra);
        }
    }

    static class Primes {
        public static List<Integer> sieveEratosthenes(int n) {
            if (n < 2) return Collections.emptyList();
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = false; if (n >= 1) isPrime[1] = false;
            for (int p = 2; (long)p * p <= n; p++)
                if (isPrime[p])
                    for (long m = (long)p * p; m <= n; m += p) isPrime[(int)m] = false;
            List<Integer> out = new ArrayList<>();
            for (int i = 2; i <= n; i++) if (isPrime[i]) out.add(i);
            return out;
        }

        public static List<Integer> sieveSundaram(int n) {
            if (n < 2) return Collections.emptyList();
            int k = (n - 1) / 2;
            boolean[] marked = new boolean[k + 1];
            for (int i = 1; i <= k; i++)
                for (int j = i; i + j + 2 * i * j <= k; j++)
                    marked[i + j + 2 * i * j] = true;
            List<Integer> out = new ArrayList<>();
            out.add(2);
            for (int i = 1; i <= k; i++) if (!marked[i]) out.add(2 * i + 1);
            return out;
        }

        public static List<Integer> sieveAtkin(int limit) {
            if (limit < 2) return Collections.emptyList();
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
            List<Integer> primes = new ArrayList<>();
            if (limit >= 2) primes.add(2);
            if (limit >= 3) primes.add(3);
            for (int a = 5; a <= limit; a++) if (sieve[a]) primes.add(a);
            return primes;
        }
    }

    /* Option B — Task 2: Step-by-step Evaluation of Expression */
    static class Expr {
        static void run(Scanner sc) {
            while (true) {
                String expr = sc.nextLine();

                if (!isValid(expr)) {
                    System.out.println("Invalid input. Please re-enter a valid expression.");
                    System.out.print("Enter an expression with digits and + - x × : ( ): ");
                    continue;
                }

                try {
                    evaluate(expr);
                    System.out.println("Done.");
                    break;
                } catch (ArithmeticException e) {
                    System.out.println("Error: Division by zero! Please re-enter a valid expression.");
                    System.out.print("Enter an expression with digits and + - x × : ( ): ");
                }
            }
        }

        private static boolean isValid(String s) {
            if (s == null || s.trim().isEmpty()) return false;
            if (!s.matches("[0-9\\s\\+\\-x×:()]+")) return false;

            String t = normalize(s);
            if (t.isEmpty()) return false;
            if (isOp(t.charAt(0)) || isOp(t.charAt(t.length() - 1))) return false;
            if (t.contains(")(") || t.matches(".*\\d\\(.*") || t.matches(".*\\)\\d.*")) return false;

            // parantez dengesi
            int bal = 0;
            for (char c : t.toCharArray()) {
                if (c == '(') bal++;
                else if (c == ')') bal--;
                if (bal < 0) return false;
            }
            if (bal != 0) return false;

            // en az bir sayı olmalı; sayı-adedi kontrolü ve operatör kontrolü
            String[] parts = s.trim().split("[^0-9]+");
            int count = 0;
            for (String p : parts) if (!p.isEmpty()) count++;
            if (!t.matches(".*[+\\-*/].*") && count > 1) return false;

            for (int i = 1; i < t.length(); i++) {
                char c = t.charAt(i), p = t.charAt(i - 1);
                if (isOp(c)) {
                    if (p == '(' && c != '-') return false;
                    if (isOp(p) && c != '-') return false;
                }
            }
            return true;
        }

        private static String normalize(String s) {
            // × ve x -> *, : -> /, − -> -
            return s.replace('×','*')
                    .replace('x','*')
                    .replace(':','/')
                    .replace('−','-')
                    .replaceAll("\\s+", "");
        }

        private static String denorm(String s) {
            // çıktıda * yerine x, / yerine :
            return s.replace('*','x').replace('/',':');
        }

        private static boolean isOp(char c) {
            return c=='+'||c=='-'||c=='*'||c=='/';
        }

        private static void evaluate(String expr) {
            String e = normalize(expr);
            String result = reduce(e);
            System.out.println("= " + denorm(result));
        }

        private static String reduce(String s) {
            s = stripOuter(s);
            if (s.matches("[+-]?\\d+")) return s;

            // önce en iç parantez
            int open = -1, close = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') open = i;
                else if (s.charAt(i) == ')' && open != -1) { close = i; break; }
            }
            if (open != -1 && close != -1) {
                String inside = reduce(s.substring(open + 1, close));
                String next = s.substring(0, open) + inside + s.substring(close + 1);
                System.out.println("= " + denorm(next));
                return reduce(next);
            }

            // çarpma/bölme
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ((c == '*' || c == '/') && i > 0 && Character.isDigit(s.charAt(i - 1))) {
                    String next = applyOp(s, i);
                    System.out.println("= " + denorm(next));
                    return reduce(next);
                }
            }

            // toplama/çıkarma
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ((c == '+' || c == '-') && i > 0 && Character.isDigit(s.charAt(i - 1))) {
                    String next = applyOp(s, i);
                    System.out.println("= " + denorm(next));
                    return reduce(next);
                }
            }
            return s;
        }

        private static String applyOp(String s, int idx) {
            int L = idx - 1;
            while (L >= 0 && Character.isDigit(s.charAt(L))) L--;
            String left = s.substring(L + 1, idx);

            int R = idx + 1;
            while (R < s.length() && (Character.isDigit(s.charAt(R)) || s.charAt(R) == '-')) R++;
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
            return s.substring(0, L + 1) + res + s.substring(R);
        }

        private static String stripOuter(String s) {
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

    /*
     * =============================
     * Program Entry Point
     * =============================
     */
    public static void main(String[] args) {
        welcomeMessage();
        mainMenu();
    }
}