package Day17;

import java.util.Arrays;
import java.util.Scanner;


public class Part2_3 {

    private static long ra;
    private static long rb;
    private static long rc;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        int b1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        int c1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        scanner.nextLine();
        String s = scanner.nextLine().split(" ")[1];
        int[] a = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
        s = s.replaceAll(",", "");
        System.out.println(s);

        long l = 0; long r = Long.MAX_VALUE;
        while(l < r) {
            long m = (l + r) / 2;
            String sequence = getSequence(a, m, 0, 0);
            System.out.println(l + " " + r + " # " + sequence);
            if (s.length() < sequence.length()) {
                r = m - 1;
            } else if (s.length() > sequence.length()) {
                l = m + 1;
            } else {
                boolean ok = false;
                for(int j = 0; j < s.length(); j ++) {
                    ok = false;
                    if (sequence.charAt(j) > s.charAt(j)) {
                        r = m - 1;
                        break;
                    } else if (sequence.charAt(j) < s.charAt(j)) {
                        l = m + 1;
                        break;
                    } else {
                        ok = true;
                    }
                }
                if (ok) {
                    r = m;
                }
            }
            System.out.println(l);
        }


    }

    private static String getSequence(int[] a, long a1, long b1, long c1) {
        ra = a1;
        rb = b1;
        rc = c1;
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while(i < a.length) {
            int code = a[i];
            if (i + 1 == a.length) break;
            int value = a[i + 1];
            if (code == 0) {
                ra = ra / (1L << combo(value));
            } else if (code == 1) {
                rb = rb ^ value;
            } else if (code == 2) {
                rb =  combo(value) % 8;
            } else if (code == 3) {
                if (ra > 0) {
                    i = value;
                    continue;
                }
            } else if (code == 4) {
                rb = rb ^ rc;
            } else if (code == 5) {
                Long newValue = combo(value) % 8;
                sb.append(newValue);
                j ++;
            } else if (code == 6) {
                rb = ra / (1L << combo(value));
            } else if (code == 7) {
                rc = ra / (1L << combo(value));
            } else {
                throw new IllegalArgumentException("Invalid combination");
            }
            i += 2;
        }
        return sb.toString();
    }

    public static long combo(int value) {
        if (value == 4) return ra;
        if (value == 5) return rb;
        if (value == 6) return rc;
        return value;
    }





}
