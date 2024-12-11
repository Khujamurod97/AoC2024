package Day10;

import java.util.*;

public class Part1 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static Set<String> set = new HashSet<>();

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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == '0') {
                    rec(i, j, '0', new int[]{i, j});
                }
            }
        }
        System.out.println(set);
        System.out.println(set.size());


    }

    private static void rec(int i, int j, char c, int[] ints) {
        if (i < 0|| i >= n || j < 0|| j >= m) return;
        if (a[i][j] == c) {
            if (c == '9') {
                set.add(ints[0] +"#" + ints[1] + "#" + i + "#" + j);
                return;
            }
            rec(i + 1, j, (char)(c + 1), ints);
            rec(i - 1, j, (char)(c + 1), ints);
            rec(i, j + 1, (char)(c + 1), ints);
            rec(i, j - 1, (char)(c + 1), ints);

        }
    }


}
