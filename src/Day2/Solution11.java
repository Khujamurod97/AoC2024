package Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            Long[] a = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toArray(Long[]::new);
            boolean ok = true;
            for(int i = 1 ; i < a.length ; i++) {
                if (a[i] == a[i - 1]) ok = false;
                if ((a[i] - a[i - 1] > 0) != (a[1] - a[0] > 0)) ok = false;
                if (Math.abs(a[i] - a[i - 1]) > 3) ok = false;
                if (ok == false) break;
             }
            if (ok) res ++;

        }
        System.out.println(res);

    }

}
