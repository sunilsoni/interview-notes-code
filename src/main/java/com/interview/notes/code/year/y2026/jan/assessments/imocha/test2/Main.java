package com.interview.notes.code.year.y2026.jan.assessments.imocha.test2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int removalOfNodes(int N, int[] Node) {
        int n = N, minOdd = Integer.MAX_VALUE, minOddVal = 0;
        boolean hasEven = false;

        for (int i = 0; i < n; ) {
            int v = Node[i], j = i + 1;
            while (j < n && Node[j] == v) j++;
            int len = j - i;
            if ((len & 1) == 0) hasEven = true;
            else if (!hasEven && len < minOdd) {
                minOdd = len;
                minOddVal = v;
            }
            i = j;
        }

        int[] out = new int[n];
        int p = 0;

        for (int i = 0; i < n; ) {
            int v = Node[i], j = i + 1;
            while (j < n && Node[j] == v) j++;
            int len = j - i;
            boolean keep = ((len & 1) == 0) || (!hasEven && v == minOddVal);
            if (keep) for (int k = 0; k < len; k++) out[p++] = v;
            i = j;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p; i++) {
            if (i > 0) sb.append(' ');
            sb.append(out[i]);
        }
        System.out.print(sb);
        return 0;
    }

    static int[] fastKeep(int[] a) {
        int n = a.length, minOdd = Integer.MAX_VALUE, minOddVal = 0;
        boolean hasEven = false;

        for (int i = 0; i < n; ) {
            int v = a[i], j = i + 1;
            while (j < n && a[j] == v) j++;
            int len = j - i;
            if ((len & 1) == 0) hasEven = true;
            else if (!hasEven && len < minOdd) {
                minOdd = len;
                minOddVal = v;
            }
            i = j;
        }

        int[] out = new int[n];
        int p = 0;

        for (int i = 0; i < n; ) {
            int v = a[i], j = i + 1;
            while (j < n && a[j] == v) j++;
            int len = j - i;
            boolean keep = ((len & 1) == 0) || (!hasEven && v == minOddVal);
            if (keep) for (int k = 0; k < len; k++) out[p++] = v;
            i = j;
        }
        return Arrays.copyOf(out, p);
    }

    static int[] bruteKeep(int[] a) {
        int n = a.length;
        ArrayList<int[]> seg = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int v = a[i], j = i + 1;
            while (j < n && a[j] == v) j++;
            seg.add(new int[]{v, j - i});
            i = j;
        }
        int m = seg.size();
        long best = Long.MAX_VALUE;
        int bestMask = 0;

        for (int mask = 0; mask < (1 << m); mask++) {
            long keep = 0;
            for (int i = 0; i < m; i++) {
                int len = seg.get(i)[1];
                if ((len & 1) == 0) keep += len;
                else if (((mask >> i) & 1) == 1) keep += len;
            }
            if (keep == 0) continue;
            if (keep < best) {
                best = keep;
                bestMask = mask;
            }
        }

        int[] out = new int[n];
        int p = 0;
        for (int i = 0; i < m; i++) {
            int v = seg.get(i)[0], len = seg.get(i)[1];
            boolean keep = ((len & 1) == 0) || (((bestMask >> i) & 1) == 1);
            if (keep) for (int k = 0; k < len; k++) out[p++] = v;
        }
        return Arrays.copyOf(out, p);
    }

    static void selfTest() {
        int total = 0, ok = 0;

        int[] s = {1, 1, 2, 2, 2, 3, 4, 4, 5};
        int[] exp = {1, 1, 4, 4};
        total++;
        if (Arrays.equals(fastKeep(s), exp)) ok++;
        System.out.println((Arrays.equals(fastKeep(s), exp) ? "PASS" : "FAIL") + " sample");

        Random r = new Random(1);
        for (int t = 0; t < 400; t++) {
            int n = 1 + r.nextInt(14);
            int[] a = new int[n];
            int v = 1;
            for (int i = 0; i < n; i++) {
                if (i > 0 && r.nextInt(3) == 0) v++;
                a[i] = v;
            }
            int[] got = fastKeep(a);
            int[] b = bruteKeep(a);
            total++;
            if (Arrays.equals(got, b)) ok++;
            else {
                System.out.println("FAIL rnd");
                return;
            }
        }
        System.out.println((ok == total ? "PASS" : "FAIL") + " " + ok + "/" + total);
    }

    public static void main(String[] args) throws Exception {
        FastIn fs = new FastIn(System.in);
        if (!fs.hasNext()) {
            selfTest();
            return;
        }
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();
        removalOfNodes(n, a);
    }

    static final class FastIn {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int len, ptr, pushed = -1;

        FastIn(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (pushed != -1) {
                int t = pushed;
                pushed = -1;
                return t;
            }
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len < 0) return -1;
            }
            return buf[ptr++];
        }

        boolean hasNext() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return false;
            } while (c <= 32);
            pushed = c;
            return true;
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= 32);
            int s = 1;
            if (c == '-') {
                s = -1;
                c = read();
            }
            int v = 0;
            while (c > 32 && c != -1) {
                v = v * 10 + (c - '0');
                c = read();
            }
            return v * s;
        }
    }
}
