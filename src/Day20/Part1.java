package Day20;

import java.util.*;


public class Part1 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static int distance;

    private static int is = 0, js = 0;
    private static int ie = 0, je = 0;
    private static int ir = 0, jr = 0;

    private static Set<Long> st = new HashSet<>();

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




        int[][] v = new int[n][m];
        ir = -1; jr = -1;
        rec(is, js, v, 1);

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '#') {
                    a[i][j] = '.';
                    ir = i;
                    jr = j;
                    rec(is, js, new int[n][m], 1);
                    a[i][j] = '#';
                }
            }
        }

        System.out.println(st.size());

    }


    public static void rec(int i1, int j1, int[][] v, int step) {
        if (a[i1][j1] == '#') return;
        v[i1][j1] = step;
        if (i1 == ie && j1 == je) {
            if (ir != -1 && jr != -1) {
                int dist = v[ie][je];
                int ir2 = 0, jr2 = 0;
                for(int i = 0; i < 4; i ++) {
                    ir2 = ir + dirs[i][0];
                    jr2 = jr + dirs[i][1];
                    if (in2(ir2, jr2) && v[ir2][jr2] == v[ir][jr] + 1) {

                        Long pos = (ir * n + jr < ir2 * n + jr2) ?
                                ir * 1000000000L + jr * 1000000L + ir2 * 1000L + jr2 :
                                ir2 * 1000000000L + jr2 * 1000000L + ir * 1000L + jr;
                        if (distance > dist && distance - dist <= 100) st.add(pos);
                        //System.out.println(ir + " " + jr + " " + ir2 + " " + jr2);
                    }
                }
            } else {
                distance = v[ie][je];
            }
        }
        for(int i = 0; i < 4; i ++) {
            int i2 = i1 + dirs[i][0];
            int j2 = j1 + dirs[i][1];
            if (in2(i2, j2) && v[i2][j2] == 0) rec(i2, j2, v, step + 1);
        }
        v[i1][j1] = 0;
    }



    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static boolean in2(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && a[x][y] != '#';
    }






}
