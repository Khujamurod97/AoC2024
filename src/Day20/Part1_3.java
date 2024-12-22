package Day20;

import java.util.*;


public class Part1_3 {

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
        Map<Integer, Integer> mp = new TreeMap<>();
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '#') {
                    a[i][j] = '.';
                    int d1 = getDistance();
                    if (d1 < distance && distance - d1 >= 100) {
                        int d2 = distance - d1;
                        mp.put(d2, mp.getOrDefault(d2, 0) + 1);
                        count++;
                    }
                    a[i][j] = '#';
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println(count);




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



    public static boolean in2(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && a[x][y] != '#';
    }
    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }






}
