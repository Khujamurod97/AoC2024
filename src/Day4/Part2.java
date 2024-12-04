package Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    private static int n;
    private static int m;
    private static char[][] array;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            input.add(line);
        }

        array = input.stream().map(String::toCharArray).toArray(char[][]::new);
        n = array.length;
        m = array[0].length;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (eq(i - 1, j - 1, 'M') && eq(i, j, 'A') && eq(i + 1, j + 1, 'S') && eq2(i, j)) res ++;
                if (eq(i + 1, j + 1, 'M') && eq(i, j, 'A') && eq(i - 1, j - 1, 'S') && eq2(i, j)) res ++;

            }
        }


        System.out.println(Arrays.deepToString(array));
        System.out.println(res);

    }

    private static boolean eq( int i, int j, char x) {
        return i >= 0 && i < n && j >= 0 && j < m && array[i][j] == x;

    }
    private static boolean eq2( int i, int j) {
        if (i == 0 || i == n || j == 0 || j == m) return false;
        if (array[i - 1][j - 1] == array[i - 1][j + 1] && array[i + 1][j - 1] == array[i + 1][j + 1]) return true;
        if (array[i - 1][j - 1] == array[i + 1][j - 1] && array[i - 1][j + 1] == array[i + 1][j + 1]) return true;
        return false;

    }

}

