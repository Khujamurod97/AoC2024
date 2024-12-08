package Day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        char[][] a = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        int n = a.length;
        int m = a[0].length;
        boolean[][] b = new boolean[n][m];
        List<int[]> q = new ArrayList<>();
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] != '.') q.add(new int[]{i, j, a[i][j]});
            }
        }
        for(int i = 0; i < q.size(); i ++) {
            for(int j = i + 1; j < q.size(); j ++) {
                if (q.get(i)[2] != q.get(j)[2]) continue;
                for(int t = 0; t <= 100; t ++) {
                    int ix = Math.abs(q.get(i)[0] - q.get(j)[0]) * t;
                    int jx = Math.abs(q.get(i)[1] - q.get(j)[1]) * t;
                    int i1 = q.get(i)[0] + (q.get(i)[0] < q.get(j)[0] ? -ix : ix);
                    int j1 = q.get(i)[1] + (q.get(i)[1] < q.get(j)[1] ? -jx : jx);
                    if (i1 >= 0 && i1 < n && j1 >= 0 && j1 < m) {
                        b[i1][j1] = true;
                    }
                    int i2 = q.get(j)[0] + (q.get(i)[0] < q.get(j)[0] ? ix : -ix);
                    int j2 = q.get(j)[1] + (q.get(i)[1] < q.get(j)[1] ? jx : -jx);
                    if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < m) {
                        b[i2][j2] = true;
                    }
                }
            }
        }
        long res = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (b[i][j]) res++;
                if (b[i][j]) System.out.print("#"); else System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println(res);

    }




}
