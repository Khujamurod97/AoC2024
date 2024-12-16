package Day16;

import java.sql.SQLOutput;
import java.util.*;


public class Part2 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static Set<Integer>[][][] s;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        a = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        n = a.length;
        m = a[0].length;



        int is = 0, js = 0; boolean start = false;
        int ie= 0, je = 0;
        for(int i = 0; i < n && !start; i ++) {
            for(int j = 0; j < m && !start; j ++) {
                if (a[i][j] == 'S') {
                    is = i;
                    js = j;
                }
                if (a[i][j] == 'E') {
                    ie = i;
                    je = j;
                }
            }
        }

        int[][] dirs = {
                {0, -1},
                {-1, 0},
                {0, 1},
                {1, 0}
        };


       int dp[][][] = new int[4][n][m];
       Set<Integer>[][][] s = new Set[4][n][m];
       for(int i = 0; i < n; i ++) {
           for(int j = 0; j < m; j ++) {
               for(int k = 0; k < 4; k ++) {
                   dp[k][i][j] = Integer.MAX_VALUE;
                   s[k][i][j] = new HashSet<>();
                   s[k][i][j].add(i * 10000 + j);
               }
           }
       }

       Queue<int[]> q = new LinkedList<>();
       q.add(new int[]{is, js, 2, 0});
       Queue<Set<Integer>> sq = new LinkedList<>();
       sq.add(new HashSet<>());
       while(!q.isEmpty()) {
           int[] c = q.poll();
           Set<Integer> ps = sq.poll();
           if (a[c[0]][c[1]] == '#') continue;
           if (dp[c[2]][c[0]][c[1]] < c[3]) continue;
           if (dp[c[2]][c[0]][c[1]] == c[3]) {
               s[c[2]][c[0]][c[1]].addAll(ps);
           } else {
               s[c[2]][c[0]][c[1]].clear();
               s[c[2]][c[0]][c[1]].add(c[0] * 10000 + c[1]);
               s[c[2]][c[0]][c[1]].addAll(ps);
           }
           dp[c[2]][c[0]][c[1]] = c[3];
           int[] d = dirs[c[2]];
           q.add(new int[]{c[0] + d[0], c[1] + d[1], c[2], c[3] + 1});
           sq.add(s[c[2]][c[0]][c[1]]);
           int[] dl = dirs[(c[2] + 1) % 4];
           q.add(new int[]{c[0] + dl[0], c[1] + dl[1], (c[2] + 1) % 4, c[3] + 1001});
           sq.add(s[c[2]][c[0]][c[1]]);
           int[] dr = dirs[(c[2] + 3) % 4];
           q.add(new int[]{c[0] + dr[0], c[1] + dr[1], (c[2] + 3) % 4, c[3] + 1001});
           sq.add(s[c[2]][c[0]][c[1]]);
       }





       int res = dp[0][ie][je];
       Set<Integer> resSet = s[0][ie][je];
       for(int i = 1; i < 4; i ++) {
           if (dp[i][ie][je] < res) {
               res = dp[i][ie][je];
               resSet = s[i][ie][je];
           }
       }
        System.out.println(res);

        int oCount = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '#') {
                    System.out.print("#");
                } else if (resSet.contains(i * 10000 + j)) {
                    System.out.print("O");
                    oCount++;
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("COUNT:" + oCount);
    }









}
