import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class CombinedApp {

    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== CombinedApp ===");
                System.out.println("1) Prime sieve comparison");
                System.out.println("2) Step-by-step expression evaluator");
                System.out.println("0) Exit");
                System.out.print("Select: ");
                String opt = sc.nextLine().trim();
                if ("0".equals(opt)) break;
                if ("1".equals(opt)) runPrimePart(sc);
                else if ("2".equals(opt)) Expr.run(sc);
                else System.out.println("Invalid choice.\n");
            }
        }
    }

    //  B(task1) Primes Numbers 
    private static void runPrimePart(Scanner sc) {
        int n = promptInt(sc, "Enter n (n >= 12): ", 12, Integer.MAX_VALUE);

        R er = measure(() -> Primes.sieveEratosthenes(n));
        R su = measure(() -> Primes.sieveSundaram(n));
        R at = measure(() -> Primes.sieveAtkin(n));

        System.out.println();
        printPrimeSummary("Eratosthenes", er);
        printPrimeSummary("Sundaram   ", su);
        printPrimeSummary("Atkin      ", at);

        // Consistency check (Eratosthenes as reference)
        if (!er.list.equals(su.list) || !er.list.equals(at.list)) {
            System.out.println("\nWARNING: Methods produced different results!");
            System.out.println("Differences relative to Eratosthenes:");
            printDiff("Sundaram", er.list, su.list);
            printDiff("Atkin   ", er.list, at.list);
        }
    }

   
    static class R { 
        List<Integer> list; 
        long nanos; 
        R(List<Integer> l,long n){list=l;nanos=n;}
    }

    private static R measure(Supplier<List<Integer>> job) {
        long t0 = System.nanoTime();
        List<Integer> out = job.get();
        return new R(out, System.nanoTime() - t0);
    }

    private static int promptInt(Scanner sc, String msg, int min, int max) {
        for (;;) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v < min || v > max) System.out.println("Invalid range.");
                else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
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
                if (isPrime[p]) for (long m = (long)p * p; m <= n; m += p) isPrime[(int)m] = false;
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
            for (int r = 5; r <= sqrt; r++) if (sieve[r]) for (int m = r * r; m <= limit; m += r * r) sieve[m] = false;
            List<Integer> primes = new ArrayList<>();
            if (limit >= 2) primes.add(2);
            if (limit >= 3) primes.add(3);
            for (int a = 5; a <= limit; a++) if (sieve[a]) primes.add(a);
            return primes;
        }
    }

    // B(task2) Step-by-step Evaluation of Expression
    static class Expr {

        static void run(Scanner sc) {
            while (true) {
                System.out.println("\nEnter an expression with digits and + - x : ( )");
                String expr = sc.nextLine();

                if (!isValid(expr)) {
                    System.out.println("Invalid input. Please re-enter a valid expression.");
                    continue;
                }

                try {
                    evaluate(expr);
                    System.out.println("Done.");
                    break;
                } catch (ArithmeticException e) {
                    System.out.println("Error: Division by zero! Please re-enter a valid expression.");
                }
            }
        }

        
        private static boolean isValid(String s) {
            if (s == null || s.trim().isEmpty()) return false;
            if (!s.matches("[0-9\\s\\+\\-x×:()]+")) return false;

            String t = normalize(s);
            if (t.isEmpty()) return false;
            if (isOp(t.charAt(0)) || isOp(t.charAt(t.length() - 1))) return false;
            if (t.contains(")(") || t.matches(".\\d\\(.") || t.matches(".\\)\\d.")) return false;

            // Parenthesis balance
            int bal = 0;
            for (char c : t.toCharArray()) {
                if (c == '(') bal++;
                else if (c == ')') bal--;
                if (bal < 0) return false;
            }
            if (bal != 0) return false;

            
            String[] parts = s.trim().split("[^0-9]+");
            int count = 0;
            for (String p : parts) if (!p.isEmpty()) count++;
            if (!t.matches(".[+\\-/].*") && count > 1) return false;

          
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
            // Map typographic/letter versions to real operators
            return s.replace('×','*')
                    .replace('x','*')
                    .replace(':','/')
                    .replace('−','-')
                    .replaceAll("\\s+", "");
        }

        private static String denorm(String s) {
            // Show * as x and / as : 
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

            
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ((c == '*' || c == '/') && i > 0 && Character.isDigit(s.charAt(i - 1))) {
                    String next = applyOp(s, i);
                    System.out.println("= " + denorm(next));
                    return reduce(next);
                }
            }

           
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
                case '*': res = a * b; break;
                case '/':
                    if (b == 0) throw new ArithmeticException("Division by zero");
                    res = a / b; break;
                case '+': res = a + b; break;
                default : res = a - b;
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
}