package Day2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            Long[] b = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toArray(Long[]::new);
            boolean OKOK = false;
            System.out.println( Arrays.toString(b));
            int len = b.length;
            for(int i1 = 0; i1 < len; i1 ++) {
                Long[] a = new Long[len - 1];
                for(int j = 0; j < len; j ++) {
                    System.out.println(j + " " + i1);
                    if (j < i1)  a[j] = b[j];
                    if (j > i1) a[j - 1] = b[j];
                }
                boolean ok = true;
                for(int i = 1 ; i < a.length ; i++) {
                    if (a[i] == a[i - 1]) ok = false;
                    if ((a[i] - a[i - 1] > 0) != (a[1] - a[0] > 0)) ok = false;
                    if (Math.abs(a[i] - a[i - 1]) > 3) ok = false;
                    if (ok == false) break;
                }
                if (ok) {
                    OKOK = true;
                    break;
                }



            }
            if (OKOK) res ++;

        }
        System.out.println(res);

    }

}
