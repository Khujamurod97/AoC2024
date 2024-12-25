package Day20;

import java.util.*;


public class Part2_1 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static int distance;

    private static int is = 0, js = 0;
    private static int ie = 0, je = 0;

    private static int[][] dirs = {
            {0, -1},
            {-1, 0},
            {0, 1},
            {1, 0}
    };

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



        boolean start = false;
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


        int count = 0;
        Queue<int[]> q1 = new LinkedList<>();
        q1.add(new int[]{is, js, 0});
        int[][] d1 = new int[n][m];
        while(q1.size() > 0) {
            int[] cur = q1.poll();
            int i = cur[0];
            int j = cur[1];
            if (d1[i][j] == 0 || d1[i][j] > cur[2]) {
                d1[i][j] = cur[2];
                for(int[] dir : dirs) {
                    int i1 = i + dir[0];
                    int j1 = j + dir[1];
                    if (in2(i1, j1)) q1.add(new int[]{i1, j1, cur[2] + 1});
                }
            }
        }
        distance = d1[ie][je];





        Queue<int[]> q2 = new LinkedList<>();
        q2.add(new int[]{is, js, 0});
        int[][] dp2 = new int[n][m];
        while(q2.size() > 0) {
            int[] cur = q2.poll();
            int i = cur[0];
            int j = cur[1];
            if (dp2[i][j] == '#') {


                Queue<int[]> q3 = new LinkedList<>();
                q3.add(new int[]{i, j, 1, 1, -1, -1});
                int[][][] dp3 = new int[n][m][3];
                while(q3.size() > 0) {
                    print(dp3);
                    int[] c = q3.poll();
                    int i1 = c[0];
                    int j1 = c[1];
                    boolean cheating = c[3] == 1;
                    if (cheating && a[i1][j1] == '#' && c[2] > 20) {
                        continue;
                    }
                    if (dp3[i1][j1][0] == 0 || dp3[i1][j1][0] > c[2]) {
                        dp3[i1][j1][0] = c[2];

                        if (a[i1][j1] != '#' && cheating) {
                            cheating = false;
                            c[4] = i1;
                            c[5] = j1;
                            dp3[i1][j1][1] = c[4];
                            dp3[i1][j1][2] = c[5];

                        }
                        for(int[] dir : dirs) {
                            int i2 = i1 + dir[0];
                            int j2 = j1 + dir[1];

                            if (!cheating && in2(i2, j2) || cheating && in(i2, j2)) {
                                q3.add(new int[]{i2, j2, c[2] + 1, c[3], c[4], c[5]});
                            }
                        }
                    }
                }

                int[] d3 = dp3[ie][je];

                if (d3[0] != 0) {
                    int distance1 = dp2[i][j] + d3[0] - 1;
                    if (distance1 < distance && distance - distance1 >= 100) {
                        int d2 = distance - distance1;
                    }

                }
                continue;
            }
            if (dp2[i][j] == 0 || dp2[i][j] > cur[2]) {
                dp2[i][j] = cur[2];
                for(int[] dir : dirs) {
                    int i1 = i + dir[0];
                    int j1 = j + dir[1];
                    if (in(i1, j1)) q2.add(new int[]{i1, j1, cur[2] + 1});
                }
            }
        }


        System.out.println(distance);




    }


    private static int getDistance() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{is, js, 0});
        int[][] dp = new int[n][m];
        while(queue.size() > 0) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (dp[i][j] == 0 || dp[i][j] > cur[2]) {
                dp[i][j] = cur[2];
                for(int[] dir : dirs) {
                    int i1 = i + dir[0];
                    int j1 = j + dir[1];
                    if (in2(i1, j1)) queue.add(new int[]{i1, j1, cur[2] + 1});
                }
            }
        }
        return dp[ie][je];
    }

    private static void calcDistances() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{is, js, 0});
        int[][] dp = new int[n][m];
        while(queue.size() > 0) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (dp[i][j] == '#') {
                int[] d3 = getDistance3(i, j);
                if (d3[0] != 0) {
                    int d1 = dp[i][j] + d3[0] - 1;
                    if (d1 < distance && distance - d1 >= 100) {
                        int d2 = distance - d1;
//                        mp.put(d2, mp.getOrDefault(d2, 0) + 1);
//                        count++;
                    }

                }
                continue;
            }
            if (dp[i][j] == 0 || dp[i][j] > cur[2]) {
                dp[i][j] = cur[2];
                for(int[] dir : dirs) {
                    int i1 = i + dir[0];
                    int j1 = j + dir[1];
                    if (in(i1, j1)) queue.add(new int[]{i1, j1, cur[2] + 1});
                }
            }
        }
    }


    private static int[] getDistance3(int i0, int j0) {
        Queue<int[]> q3 = new LinkedList<>();
        q3.add(new int[]{i0, j0, 1, 1, -1, -1});
        int[][][] dp3 = new int[n][m][3];
        if (a[i0][j0] != '#') return new int[]{0, -1, -1};
        while(q3.size() > 0) {
            print(dp3);
            int[] c = q3.poll();
            int i1 = c[0];
            int j1 = c[1];
            boolean cheating = c[3] == 1;
            if (cheating && a[i1][j1] == '#' && c[2] > 20) {
                continue;
            }
            if (dp3[i1][j1][0] == 0 || dp3[i1][j1][0] > c[2]) {
                dp3[i1][j1][0] = c[2];

                if (a[i1][j1] != '#' && cheating) {
                    cheating = false;
                    c[4] = i1;
                    c[5] = j1;
                    dp3[i1][j1][1] = c[4];
                    dp3[i1][j1][2] = c[5];

                }
                for(int[] dir : dirs) {
                    int i2 = i1 + dir[0];
                    int j2 = j1 + dir[1];

                    if (!cheating && in2(i2, j2) || cheating && in(i2, j2)) {
                        q3.add(new int[]{i2, j2, c[2] + 1, c[3], c[4], c[5]});
                    }
                }
            }
        }

       return dp3[ie][je];
    }

    private static void print(int[][][] dp) {

        for(int t = 0; t < n; t ++) {
            for(int q = 0; q < m; q ++) {
                if (a[t][q] == 'S' || a[t][q] == 'E') {
                    System.out.print(a[t][q]);
                } else if (dp[t][q][0] != 0) {
                    System.out.print('+');
                } else {
                    System.out.print(a[t][q]);
                }
            }
            System.out.println();
        }
        System.out.println();

    }


    public static boolean in2(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && a[x][y] != '#';
    }
    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }






}
