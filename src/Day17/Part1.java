package Day17;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part1 {

    private static int ra;
    private static int rb;
    private static int rc;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ra = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        rb = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        rc = Integer.parseInt(scanner.nextLine().split(" ")[2]);
        scanner.nextLine();
        int[] a = Arrays.stream(scanner.nextLine().split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < a.length) {
            int code = a[i];
            if (i + 1 == a.length) break;
            int value = a[i + 1];
            if (code == 0) {
                ra = ra / (1 << combo(value));
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
                int newValue = combo(value) % 8;
                if (!sb.isEmpty()) sb.append(",");
                sb.append(newValue);
            } else if (code == 6) {
                rb = ra / (1 << combo(value));
            } else if (code == 7) {
                rc = ra / (1 << combo(value));
            } else {
                throw new IllegalArgumentException("Invalid combination");
            }
            i += 2;
        }
        System.out.println(sb);
    }
    public static int combo(int value) {
        if (value == 4) return ra;
        if (value == 5) return rb;
        if (value == 6) return rc;
        return value;
    }





}
