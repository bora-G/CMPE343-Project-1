import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    // ====== Console UI (colors) & Scanner ======
    private static final Scanner SC = new Scanner(System.in).useLocale(Locale.US);
    private static final String cyan = "\u001B[36m";
    private static final String yellow = "\u001B[33m";
    private static final String green = "\u001B[32m";
    private static final String reset = "\u001B[0m";
    private static final String red = "\u001B[31m";

    private static final String BUILD_TAG = "BUILD-2025-10-21-TR"; // görsel kanıt

    // ====== Shared data for Option C - Task 1 ======
    private static ArrayList<Double> arrC = new ArrayList<>();

    // =============================
    // Program Entry Point
    // =============================
    public static void main(String[] args) {
        welcomeMessage();
        mainMenu();
    }

    // ====== Welcome & Main Menu ======
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
        System.out.println(cyan + " - Melek Sadiki" + reset);
        System.out.println(cyan + " - [Member 3]" + reset);
        System.out.println(cyan + " - [Member 4]" + reset);
        System.out.println();
        System.out.println(yellow + "Press ENTER to continue..." + reset);
        SC.nextLine();
        clearScreen();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[1] Primary School" + reset);
            System.out.println(cyan + "[2] Secondary School" + reset);
            System.out.println(cyan + "[3] High School" + reset);
            System.out.println(cyan + "[4] University" + reset);
            System.out.println(cyan + "[5] Terminate" + reset);
            System.out.println(red + "********************************" + reset);
            System.out.print(green + "Please select an option to continue: " + reset);

            int choice = readInt(1, 5);
            clearScreen();

            switch (choice) {
                case 1:
                    subMenuOption1();
                    break;
                case 2:
                    subMenuOption2();
                    break;
                case 3:
                    subMenuOption3(); // FULL
                    break;
                case 4:
                    subMenuOption4();
                    break;
                case 5:
                    System.out.println(green + "\nTurning the program off...");
                    System.out.println(red + "Thank you for using our program!" + reset);
                    return;
            }
            clearScreen();
        }
    }

    private static int readInt(int min, int max) {
        while (true) {
            String s = SC.nextLine().trim();
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

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // =============================
    // Option A — Primary School (stubs)
    // =============================
    private static void subMenuOption1() {
        System.out.println(yellow + "Option A is not implemented in this build." + reset);
        System.out.println(yellow + "Press ENTER to return to main menu..." + reset);
        SC.nextLine();
    }

    // =============================
    // Option B — Secondary School (stubs)
    // =============================
    private static void subMenuOption2() {
        System.out.println(yellow + "Option B is not implemented in this build." + reset);
        System.out.println(yellow + "Press ENTER to return to main menu..." + reset);
        SC.nextLine();
    }

    // =============================
    // Option C — High School (FULL)
    // =============================
    private static void subMenuOption3() {
        while (true) {
            System.out.println(red + "********************************" + reset);
            System.out.println(cyan + "[1] Statistical Information about an Array" + reset);
            System.out.println(cyan + "[2] Distance between Two Arrays" + reset);
            System.out.println(cyan + "[3] Terminate" + reset);
            System.out.println(red + "********************************" + reset);
            System.out.print(green + "Please select an option to continue: " + reset);

            int choice = readInt(1, 3);
            clearScreen();

            switch (choice) {
                case 1:
                    statisticalInformation();
                    break;
                case 2:
                    distanceBetweenTwoArrays();
                    break;
                case 3:
                    System.out.println(green + "\nReturning the main menu." + reset);
                    System.out.println(yellow + "Press ENTER..." + reset);
                    SC.nextLine();
                    return;
            }
            clearScreen();
        }
    }

    // ---------- Option C — Task 1: Statistical Information ----------
    private static void statisticalInformation() {
        arrC.clear();
        System.out.println(yellow + "=== Statistical Information about an Array ===" + reset);
        int n = arraySize();

        System.out.println(cyan + "Enter " + n + " real numbers (space/newline separated). For decimals you can use ',' or '.':" + reset);

        // Fazla girilen token'ları uyarı olarak gösteren okuma bloğu
        while (arrC.size() < n) {
            String line = SC.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] toks = line.split("\\s+");
            int j = 0;

            for (; j < toks.length; j++) {
                if (arrC.size() >= n) break;
                String t = toks[j];
                String norm = t.replace(',', '.');
                try {
                    double v = Double.parseDouble(norm);
                    arrC.add(v);
                } catch (NumberFormatException ex) {
                    System.out.println(red + "Invalid number: " + t + ". Please continue entering the remaining values." + reset);
                }
            }

            // Eğer bu satırda hedefe ulaşıldı ve hâlâ fazla token varsa uyar
            if (arrC.size() >= n && j < toks.length) {
                String[] extras = Arrays.copyOfRange(toks, j, toks.length);
                System.out.println(
                    red + "Warning: You entered " + extras.length +
                    " extra value(s) beyond the expected " + n + ". They were ignored: " +
                    String.join(" ", extras) + reset
                );
            }
        }

        double median = calculateMedian();
        double arithmetic = calculateArithmeticMean();
        double geometric = calculateGeometricMean();
        double harmonic = calculateHarmonicMean();

        System.out.println(green + "\nResults:" + reset);
        System.out.printf(cyan + "Median:             " + reset + "%.6f%n", median);
        System.out.printf(cyan + "Arithmetic mean:    " + reset + "%.6f%n", arithmetic);
        if (Double.isNaN(geometric)) {
            System.out.println(cyan + "Geometric mean:     " + reset + red + "undefined for non-positive elements" + reset);
        } else {
            System.out.printf(cyan + "Geometric mean:     " + reset + "%.6f%n", geometric);
        }
        if (Double.isNaN(harmonic)) {
            System.out.println(cyan + "Harmonic mean:      " + reset + red + "undefined when element is 0" + reset);
        } else {
            System.out.printf(cyan + "Harmonic mean:      " + reset + "%.6f%n", harmonic);
        }

        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    private static int arraySize() {
        System.out.print(yellow + "Enter array size (n >= 1): " + reset);
        while (true) {
            String s = SC.nextLine().trim();
            if (!s.matches("\\d+")) {
                System.out.print(red + "Please enter a positive integer (>=1): " + reset);
                continue;
            }
            try {
                int n = Integer.parseInt(s);
                if (n < 1) {
                    System.out.print(red + "n must be >= 1: " + reset);
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.print(red + "Please enter a positive integer (>=1): " + reset);
            }
        }
    }

    private static double calculateMedian() {
        if (arrC.isEmpty()) return 0.0;
        ArrayList<Double> copy = new ArrayList<>(arrC);
        copy.sort(Double::compareTo);
        int n = copy.size();
        if (n % 2 == 1) {
            return copy.get(n / 2);
        } else {
            return (copy.get(n / 2 - 1) + copy.get(n / 2)) / 2.0;
        }
    }

    private static double calculateArithmeticMean() {
        if (arrC.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double v : arrC) sum += v;
        return sum / arrC.size();
    }

    private static double calculateGeometricMean() {
        if (arrC.isEmpty()) return 0.0;
        for (double v : arrC) {
            if (v <= 0.0) return Double.NaN;
        }
        double logSum = 0.0;
        for (double v : arrC) logSum += Math.log(v);
        return Math.exp(logSum / arrC.size());
    }

    private static double calculateHarmonicMean() {
        if (arrC.isEmpty()) return 0.0;
        for (double v : arrC) {
            if (v == 0.0) return Double.NaN; // division by zero
        }
        double sumRecip = harmonicRec(arrC, 0);
        return arrC.size() / sumRecip;
    }

    private static double harmonicRec(ArrayList<Double> a, int idx) {
        if (idx == a.size()) return 0.0;
        return (1.0 / a.get(idx)) + harmonicRec(a, idx + 1);
    }

    // ---------- Option C — Task 2: Distance between Two Arrays ----------
    private static void distanceBetweenTwoArrays() {
        System.out.println(yellow + "=== Distance between Two Arrays ===" + reset);
        int d = getDimension();

        System.out.println(cyan + "Enter the first vector of length " + d + " (integers 0..9, space/newline separated):" + reset);
        int[] a = readVector(d, "A");

        System.out.println(cyan + "Enter the second vector of length " + d + " (integers 0..9, space/newline separated):" + reset);
        int[] b = readVector(d, "B");

        double manhattan = calculateManhattanDistance(a, b);
        double euclidean = calculateEuclideanDistance(a, b);
        Double cosine = calculateCosineSimilarity(a, b);

        System.out.println(green + "\nResults:" + reset);
        System.out.printf(cyan + "Manhattan distance: " + reset + "%.6f%n", manhattan);
        System.out.printf(cyan + "Euclidean distance: " + reset + "%.6f%n", euclidean);
        if (cosine == null || cosine.isNaN()) {
            System.out.println(cyan + "Cosine similarity:  " + reset + red + "undefined (one of the vectors is zero)" + reset);
        } else {
            System.out.printf(cyan + "Cosine similarity:  " + reset + "%.6f%n", cosine);
        }

        System.out.println(yellow + "\nPress ENTER to return to menu..." + reset);
        SC.nextLine();
    }

    private static int getDimension() {
        System.out.print(yellow + "Enter dimension (d >= 1): " + reset);
        while (true) {
            String s = SC.nextLine().trim();
            if (!s.matches("\\d+")) {
                System.out.print(red + "Please enter a positive integer (>=1): " + reset);
                continue;
            }
            try {
                int d = Integer.parseInt(s);
                if (d < 1) {
                    System.out.print(red + "d must be >= 1: " + reset);
                    continue;
                }
                return d;
            } catch (NumberFormatException e) {
                System.out.print(red + "Please enter a positive integer (>=1): " + reset);
            }
        }
    }

    private static int[] readVector(int d, String name) {
        int[] v = new int[d];
        int filled = 0;

        while (filled < d) {
            String line = SC.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] toks = line.split("\\s+");
            int j = 0;

            for (; j < toks.length; j++) {
                if (filled >= d) break;
                String t = toks[j];
                if (!t.matches("\\d+")) {
                    System.out.println(red + "Invalid integer: " + t + ". Please continue entering the remaining values." + reset);
                    continue;
                }
                try {
                    int val = Integer.parseInt(t);
                    if (val < 0 || val > 9) {
                        System.out.println(red + "Elements must be in [0..9]. Got: " + val + reset);
                        continue;
                    }
                    v[filled++] = val;
                } catch (NumberFormatException e) {
                    System.out.println(red + "Invalid integer: " + t + reset);
                }
            }

            // Fazla token uyarısı
            if (filled >= d && j < toks.length) {
                String[] extras = Arrays.copyOfRange(toks, j, toks.length);
                System.out.println(
                    red + "Warning: You entered " + extras.length +
                    " extra value(s) beyond the expected " + d + " for vector " + name +
                    ". They were ignored: " + String.join(" ", extras) + reset
                );
            }
        }
        return v;
    }

    private static double calculateManhattanDistance(int[] a, int[] b) {
        long sum = 0L;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return (double) sum;
    }

    private static double calculateEuclideanDistance(int[] a, int[] b) {
        double sumsq = 0.0;
        for (int i = 0; i < a.length; i++) {
            double diff = a[i] - b[i];
            sumsq += diff * diff;
        }
        return Math.sqrt(sumsq);
    }

    private static Double calculateCosineSimilarity(int[] a, int[] b) {
        long dot = 0L;
        long normA2 = 0L;
        long normB2 = 0L;
        for (int i = 0; i < a.length; i++) {
            dot += (long) a[i] * b[i];
            normA2 += (long) a[i] * a[i];
            normB2 += (long) b[i] * b[i];
        }
        if (normA2 == 0L || normB2 == 0L) {
            return null; // undefined
        }
        return dot / (Math.sqrt(normA2) * Math.sqrt(normB2));
    }

    // =============================
    // Option D — University (stubs)
    // =============================
    private static void subMenuOption4() {
        System.out.println(yellow + "Option D is not implemented in this build." + reset);
        System.out.println(yellow + "Press ENTER to return to main menu..." + reset);
        SC.nextLine();
    }
}
