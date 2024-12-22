package Day20;

import java.util.*;


public class Part1_2 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static int distance;

    private static int is = 0, js = 0;
    private static int ie = 0, je = 0;

    private static Set<Long> st = new HashSet<>();
    private static long CNT = 0L;

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
        distance = getDistance();
        System.out.println(distance);
        Set<String> st = new HashSet<>();
        getDistance2();



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


    private static int getDistance2() {
        rec(is, js, new int[n][m], 1, 2, -1, -1, -1);
        System.out.println("SET SIZE " + st.size());
        System.out.println("CNT " + CNT);
        return 0;
    }

    private static void rec(int i, int j, int[][] v, int nextStep, int limit, int p1, int p2, int di) {
        //System.out.println(i + " " + j);
        if (i == ie && j == je) {
            if (distance > nextStep - 1 && (distance - (nextStep - 1) <= 100)) {
                st.add(p1 * 1000000l + p2);

                CNT ++;
                System.out.println(distance - nextStep + 1);
//                for(int t = 0; t < n; t ++) {
//                    for(int q = 0; q < m; q ++) {
//                        if (a[t][q] == 'S' || a[t][q] == 'E') {
//                            System.out.print(a[t][q]);
//                        } else if (v[t][q] != 0) {
//                            if (t * 1000 + q == p1) {
//                                System.out.print('1');
//                            } else if (t * 1000 + q == p2) {
//                              System.out.print('2');
//                            } else {
//                                System.out.print('.');
//                            }
//                        } else {
//                            System.out.print(a[t][q]);
//                        }
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
            return;
        }
        v[i][j] = nextStep;
        for(int dd = 0; dd < dirs.length; dd ++) {
            int[] dir = dirs[dd];
            int i1 = i + dir[0];
            int j1 = j + dir[1];
            if (in(i1, j1) && v[i1][j1] == 0 && nextStep < distance) {
                if (limit == 2) {
                    if (a[i1][j1] == '#') {
                        rec(i1, j1, v, nextStep + 1, 1, i1 * 1000 + j1, -1, dd) ;
                    } else {
                        rec(i1, j1, v, nextStep + 1, 2, -1, -1, dd) ;
                    }
                } else if (limit == 1) {
                    if (a[i1][j1] != '#' && dd == di) {
                        rec(i1, j1, v, nextStep + 1, 0, p1, i1 * 1000 + j1, dd) ;
                    }
                } else if (a[i1][j1] != '#') {
                    rec(i1, j1, v, nextStep + 1, 0, p1, p2, dd) ;
                }
            }
        }
        v[i][j] = 0;
    }


    public static boolean in2(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && a[x][y] != '#';
    }
    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }






}
