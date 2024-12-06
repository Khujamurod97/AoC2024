package Day6;

import java.util.*;

public class Part1 {

    private static char[][] a;
    private static boolean[][] b;
    private static int n, m;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            input.add(line);
        }


        a = input.stream().map(str -> str.toCharArray()).toArray(char[][]::new);
        n = a.length;
        m = a[0].length;

        int i0 = -1, j0 = -1;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '^') {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
        }

        b = new boolean[n][m];
        int ix = -1; int jx = 0;
        boolean lastStepWasLoop = false;
        while(true) {
            b[i0][j0] = true;
            int i1 = i0 + ix;
            int j1 = j0 + jx;
            if (in(i1, j1)== false) break;
            while (in(i1, j1) && a[i1][j1] == '#') {
                if (ix == -1 && jx == 0) {ix = 0; jx = 1;} else
                if (ix == 0 && jx == 1) {ix = 1; jx = 0;} else
                if (ix == 1 && jx == 0) {ix = 0; jx = -1;} else {ix = -1; jx = 0;}
                i1 = i0 + ix;
                j1 = j0 + jx;
            }

            i0 = i1;
            j0 = j1;
        }

        for(int i = 0 ; i < n; i ++) {
            for(int j = 0 ; j < m; j ++) {
                if (b[i][j]) System.out.print('X');
                else System.out.print('.');
                if (b[i][j]) res ++;
            }
            System.out.println();
        }


        System.out.println(res);

    }

    private static boolean in( int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static void fill(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            b[curr[0]][curr[1]] = true;
            if (in(i - 1, j) && !b[i - 1][j]) q.add(new int[]{i - 1, j});
            if (in(i + 1, j) && !b[i + 1][j]) q.add(new int[]{i + 1, j});
            if (in(i, j - 1) && !b[i][j - 1]) q.add(new int[]{i, j - 1});
            if (in(i, j + 1) && !b[i][j + 1]) q.add(new int[]{i, j + 1});
        }
    }


}

