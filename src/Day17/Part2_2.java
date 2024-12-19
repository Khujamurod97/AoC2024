package Day17;

import java.util.Arrays;
import java.util.Scanner;


public class Part2_2 {

    private static long ra;
    private static long rb;
    private static long rc;

    public static void main(String[] args) {
        System.out.println(7 ^ 1);
        Scanner scanner = new Scanner(System.in);
        int a1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        int b1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        int c1 = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        scanner.nextLine();
        int[] a = Arrays.stream(scanner.nextLine().split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        for(long i = 1; i < Long.MAX_VALUE; i ++) {
            if (i % 100000000L == 0) System.out.println("iteration " + i + " / " + Long.MAX_VALUE);;
            if (isValid(a, 0, 0, i)) {
                System.out.println("RESULT " + i);
                return;
            }
        }
    }

    private static boolean isValid(int[] a, long a1, long b1, long c1) {
        ra = a1;
        rb = b1;
        rc = c1;
        for(int j = a.length - 1; j >= 0; j--) {
            for(int i = a.length - 2; i >= 0; i-= 2) {
                int code = a[i];
                int value = a[i + 1];
                if (code == 0) {
                    ra = ra * (1L << combo(value));
                } else if (code == 1) {
                    rb = rb ^ value;
                } else if (code == 2) {
                    // min  rb =  combo(value) % 8;
                } else if (code == 3) {
                    //
                } else if (code == 4) {
                    rb = rb ^ rc;
                } else if (code == 5) {
                    if (combo(value) % 8 != a[j]) return false;
                } else if (code == 6) {
                    rb = ra / (1L << combo(value));
                } else if (code == 7) {
                    rc = ra / (1L << combo(value));
                }
            }
        }
        System.out.println("VALID : A = " + ra + ", B = " + rb + ", C = " + rc  );
        return true;
    }

    public static long combo(int value) {
        if (value == 4) return ra;
        if (value == 5) return rb;
        if (value == 6) return rc;
        return value;
    }





}
