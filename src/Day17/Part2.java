package Day17;

import java.util.Arrays;
import java.util.Scanner;


public class Part2 {

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

        int l = s.length();
        for(int i = 1; i < l; i ++) {

        }


//        Long maxValue = Long.MAX_VALUE / 10;
//        Long maxValue = 92233720368547758L;
//        Long maxValue = 9223372036854775L;
        Long minValue = 0L;
        Long maxValue = 300L;
        for(long i = minValue; i < maxValue; i ++) {
            //if (i % 100000000L == 0) System.out.println("iteration " + i + " / " + Long.MAX_VALUE);;
            if (isValid(a, i, b1, c1)) {
                System.out.println("RESULT " + i);
                return;
            }
        }
    }

    private static boolean isValid(int[] a, long a1, long b1, long c1) {
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
                if (!sb.isEmpty()) sb.append(",");
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
        System.out.println(a1 + " " + b1 + " " + c1 + " # " + ra + " " + rb + " " + rc + " # " + sb);
        return false;
    }

    public static long combo(int value) {
        if (value == 4) return ra;
        if (value == 5) return rb;
        if (value == 6) return rc;
        return value;
    }





}
