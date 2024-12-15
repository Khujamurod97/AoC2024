package Day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        Long res = 0L;
//        int n = 7;
        int n = 103;
//        int m = 11;
        int m = 101;
        int second = 100;
        int[][] a = new int[n][m];
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            Pattern pattern = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int dx = Integer.parseInt(matcher.group(3));
            int dy = Integer.parseInt(matcher.group(4));
            System.out.println(x + "," + y + "," + dx + "," + dy);
            int x1 = ((x + second * dx) % m + m) % m;
            int y1 = ((y + second * dy) % n + n) % n;
            a[y1][x1] ++;
        }

        long s1= 0L;
        long s2= 0L;
        long s3= 0L;
        long s4= 0L;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print((a[i][j] == 0 ? "." : a[i][j]) + " ");
                if (i < n / 2) {
                    if (j < m / 2) s1 += a[i][j];
                    if (j > m / 2) s2 += a[i][j];
                } if (i > n / 2) {
                    if (j < m / 2) s3 += a[i][j];
                    if (j > m / 2) s4 += a[i][j];
                }
            }
            System.out.println();
        }

        System.out.println(s1 + " " + s2 + " " + s3 + " " + s4);
        System.out.println(s1 * s2 * s3 * s4);


    }


}
/*
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3
 */
