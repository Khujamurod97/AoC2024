package Day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        Long res = 0L;
//        int n = 7;
        int n = 103;
//        int m = 11;
        int m = 101;
        List<int[]> p = new ArrayList<>();
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
            p.add(new int[]{x, y, dx, dy});
        }
        for(int second = 0; second <= 500000; second += 1) {
            int[][] a = new int[n][m];
            for(int[] p1 : p) {
                int x1 = ((p1[0] + second * p1[2]) % m + m) % m;
                int y1 = ((p1[1] + second * p1[3]) % n + n) % n;
                a[y1][x1] ++;
            }

            int connectCount = 0;
            for(int i = 1; i < n - 1; i ++) {
                for(int j = 1; j < m - 1; j ++) {
                    if (a[i][j] == 0) continue;
                    if (a[i - 1][j] != 0 || a[i + 1][j] != 0 || a[i][j - 1] != 0 || a[i][j + 1] != 0 || a[i - 1][j - 1] != 0 || a[i + 1][j + 1] != 0 || a[i - 1][j + 1] != 0 || a[i + 1][j - 1] != 0 ) {
                        connectCount++;
                    }
                }
            }

            if (connectCount > 270) {
                System.out.println("second " + second + " " + connectCount) ;
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        System.out.print((a[i][j] == 0 ? "." : "#"));
                    }
                    System.out.println();
                }
            }

        }




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
