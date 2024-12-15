package Day15;

import java.util.*;


public class Part2 {

    private static char[][] a;
    private static char[][] b;
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

        a = lines.stream().map(String::toCharArray)
                .map(chars -> {
                   StringBuilder stringBuilder = new StringBuilder();
                    for (char c : chars) {
                        if (c == '#') stringBuilder.append("##");
                        if (c == 'O') stringBuilder.append("[]");
                        if (c == '.') stringBuilder.append("..");
                        if (c == '@') stringBuilder.append("@.");
                    }
                    return stringBuilder.toString().toCharArray();
                })
                .toArray(char[][]::new);
        n = a.length;
        m = a[0].length;




        lines = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        b = lines.stream().map(String::toCharArray).toArray(char[][]::new);


        print(' ');

        int is = 0, js = 0; boolean start = false;
        for(int i = 0; i < n && !start; i ++) {
            for(int j = 0; j < m && !start; j ++) {
                if (a[i][j] == '@') {
                    is = i;
                    js = j;
                    start = true;
                }
            }
        }

        Map<Character, int[]> dir = new HashMap<>();
        dir.put('<', new int[]{0, -1});
        dir.put('>', new int[]{0, 1});
        dir.put('^', new int[]{-1, 0});
        dir.put('v', new int[]{1, 0});

        for(int k = 0; k < b.length; k ++) {
            for(int l = 0; l < b[k].length; l ++) {
                char c = b[k][l];
                int[] d = dir.get(c);
                if (move(is, js, d)) {
                    a[is][js] = '.';
                    is += d[0];
                    js += d[1];

                   // print(c);
                } else {
                    System.out.println("cannot move " + c);
                }

            }
        }

        long res = 0L;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '[') {
                    res += 100 * i + j;
                }
            }
        }
        System.out.println("res " + res);


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

    private static boolean move(int i, int j, int[] d) {
        int i1 = i + d[0];
        int j1 = j + d[1];
        if (!in(i1, j1)) return false;
        if (a[i1][j1] == '#') return false;
        if (a[i1][j1] == '[') {
            if (d[1] != 0) {
                if (move(i1, j1, d)) {
                    a[i1][j1] = a[i][j];
                    return true;
                } else {
                    return false;
                }
            } else {
                if (canMove(i1, j1, d) && canMove(i1, j1 + 1, d)) {
                    move(i1, j1, d); move(i1, j1 + 1, d);
                    a[i1][j1] = a[i][j];
                    a[i1][j1 + 1] = '.';
                    return true;
                } else {
                    return false;
                }
            }

        }
        if (a[i1][j1] == ']') {
            if (d[1] != 0) {
                if (move(i1, j1, d)) {
                    a[i1][j1] = a[i][j];
                    return true;
                } else {
                    return false;
                }
            } else {
                if (canMove(i1, j1, d) && canMove(i1, j1 - 1, d)) {
                    move(i1, j1, d);
                    move(i1, j1 - 1, d);
                    a[i1][j1] = a[i][j];
                    a[i1][j1 - 1] = '.';
                    return true;
                } else {
                    return false;
                }
            }
        }
        a[i1][j1] = a[i][j];
        return true;

    }

    private static boolean canMove(int i, int j, int[] d) {
        int i1 = i + d[0];
        int j1 = j + d[1];
        if (!in(i1, j1)) return false;
        if (a[i1][j1] == '#') return false;
        if (a[i1][j1] == '[') {
            return canMove(i1, j1, d) && canMove(i1, j1 + 1, d);
        }
        if (a[i1][j1] == ']') {
            return canMove(i1, j1, d) && canMove(i1, j1 - 1, d);
        }
        return true;

    }


    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }






}
