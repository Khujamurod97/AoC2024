package Day16;

import java.util.*;


public class Part1 {

    private static char[][] a;
    private static int n;
    private static int m;

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


        int[][][] dp = new int[4][n][m];
        for(int i = 0; i < 4; i ++) {
            for(int j = 0; j < n; j ++) {
                for(int k = 0; k < m; k ++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{2, is, js, 0});
//        dp[1][is][js] = 0;
        //System.out.println(is + " " + js);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (!in(curr[1], curr[2])) continue;
            if (a[curr[1]][curr[2]] == '#') continue;
            if (dp[curr[0]][curr[1]][curr[2]] < curr[3]) continue;
            //System.out.println(curr[1] + " " + curr[2]);
            if (dp[curr[0]][curr[1]][curr[2]] == 0 || dp[curr[0]][curr[1]][curr[2]] > curr[3] ) {
                dp[curr[0]][curr[1]][curr[2]] = curr[3];
            }

            int[] dir = dirs[curr[0]];
            if (in2(curr[1] + dir[0], curr[2] + dir[1])) q.add(new int[]{curr[0], curr[1] + dir[0], curr[2] + dir[1], curr[3] + 1});
            int[] dir1 = dirs[(curr[0] + 1) % 4];
            if (in2(curr[1] + dir1[0], curr[2] + dir1[1])) q.add(new int[]{(curr[0] + 1) % 4, curr[1] + dir1[0], curr[2] + dir1[1], curr[3] + 1001});
            int[] dir2 = dirs[(curr[0] + 3) % 4];
            if (in2(curr[1] + dir2[0], curr[2] + dir2[1])) q.add(new int[]{(curr[0] + 3) % 4, curr[1] + dir2[0], curr[2] + dir2[1], curr[3] + 1001});

        }

        int res = dp[0][ie][je];
        for(int i = 1; i < 4; i ++) {
            if (dp[i][ie][je] < res ) {
                res = dp[i][ie][je];
            }
        }

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '#') {
                    System.out.print("#");
                }else  if (dp[0][i][j] != Integer.MAX_VALUE || dp[1][i][j] != Integer.MAX_VALUE || dp[2][i][j] != Integer.MAX_VALUE || dp[3][i][j] != Integer.MAX_VALUE) {
                    System.out.print('-');
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println(res);

    }

    private static void print(char c) {
        System.out.println("move " + c);
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static boolean in2(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && a[x][y] != '#';
    }






}
