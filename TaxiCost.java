import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

public class TaxiCost {


    public static void main(String[] args) {
        FastScanner scan = new FastScanner();

        int t = scan.nextInt();

        while (t-- > 0) {
            int n = scan.nextInt();
            int x = scan.nextInt();
            int[] days = new int[n];
            int totCost = 0;
            for (int i = 0; i < n; i++) days[i] = scan.nextInt();

            if (n == 1) {
                if (days[0] == 0) {
                    System.out.println(0);
                } else if (days[0] == 1) {
                    System.out.println(x);
                }
            }
            for (int i = 0; i < n; i++) {
                if (days[i] == 1 || (i != 0 && days[i - 1] == 1)) {
                    totCost += x;
                }
            }
            System.out.println(totCost);
        }
    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

    static void ruffleSort(int[] a) {
        int n = a.length; //shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i]; a[i] = temp;
        }
        Arrays.sort(a);
    }
    static long add(long a, long b) {
        return (a + b) % mod;
    }
    static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }
    static long mul(long a, long b) {
        return (a * b) % mod;
    }
    static long exp(long base, long exp) {
        if (exp == 0) return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0) return mul(half, half);
        return mul(half, mul(half, base));
    }
    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];
    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }


    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }


}
