package Day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part_2_3 {

    private static char[][] a;
    private static boolean[][] b;
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

        int i0 = 0;
        int j0 = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (a[i][j] == '^') {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
        }

        int[] curr = {i0, j0, -1, 0};
        long res = 0;
        while(in(curr[0], curr[1])) {
            if (a[curr[0]][curr[1]] == '#') {
                throw new RuntimeException("XATO# " + Arrays.toString(curr));
            }

            System.out.println(Arrays.toString(curr));
            if (hasElementInFront(curr) && hasObstructionInFront(curr)) {
                while(hasElementInFront(curr) && hasObstructionInFront(curr)) {
                    turnRight(curr);
                }
            } else {
                if (hasElementInFront(curr) && canBuildObstruction(curr)) {
                    res ++;
                }
            }
            stepForward(curr);
        }
        System.out.println(res);
    }

    private static boolean canBuildObstruction(int[] base) {
        int[] curr = {base[0], base[1], base[2], base[3]};
        int ni = curr[0] + curr[2];
        int nj = curr[1] + curr[3];
        char c = a[ni][nj];
        a[ni][nj] = '#';
        int[][] history = new int[n][m];
        b = new boolean[n][m];
        while(in(curr[0], curr[1])) {
            if (a[curr[0]][curr[1]] == '#') {
                throw new RuntimeException("XATO " + Arrays.toString(curr));
            }
            b[curr[0]][curr[1]] = true;
            int direction = getDirectionNumber(curr[2], curr[3]);
            if ((history[curr[0]][curr[1]] & direction) > 0) {
                a[ni][nj] = c;
                return true;
            }
            history[curr[0]][curr[1]] |= direction;
            while(hasElementInFront(curr) && hasObstructionInFront(curr)) {
                turnRight(curr);
            }
            stepForward(curr);
        }

        //printb();
        a[ni][nj] = c;
        return false;

    }

    private static int getDirectionNumber(int i, int j) {
        if (i == -1 && j == 0) return 2;
        if (i == 0 && j == 1) return 4;
        if (i == 1 && j == 0) return 8;
        return 16;
    }

    public static void printb() {
        System.out.println();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(b[i][j] ? "+" : a[i][j]);
            }
            System.out.println();
        }

    }

    public static boolean in(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    public static void stepForward(int[] curr) {
        curr[0] += curr[2];
        curr[1] += curr[3];
    }

    public static void turnRight(int[] curr) {
        int i0 = curr[2];
        int j0 = curr[3];
        int i1 = 0, j1 = 0;
        if (i0 == -1 && j0 == 0) {i1 = 0; j1 = 1;} else
        if (i0 == 0 && j0 == 1) {i1 = 1; j1 = 0;} else
        if (i0 == 1 && j0 == 0) {i1 = 0; j1 = -1;} else
        {i1 = -1; j1 = 0;}
        curr[2] = i1;
        curr[3] = j1;
    }

    public static boolean hasObstructionInFront(int[] curr) {
        return a[curr[0] + curr[2]][curr[1] + curr[3]] == '#';
    }

    public static boolean hasElementInFront(int[] curr) {
        return in(curr[0] + curr[2], curr[1] + curr[3]);
    }


}
