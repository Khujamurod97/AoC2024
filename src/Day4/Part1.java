package Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
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
                if (eq(i, j, 'X') && eq(i, j + 1, 'M') && eq(i, j + 2, 'A') && eq(i, j + 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i, j - 1, 'M') && eq(i, j - 2, 'A') && eq(i, j - 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i + 1, j + 1, 'M') && eq(i + 2, j + 2, 'A') && eq(i + 3, j + 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i - 1, j + 1, 'M') && eq(i - 2, j + 2, 'A') && eq(i - 3, j + 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i - 1, j - 1, 'M') && eq(i - 2, j - 2, 'A') && eq(i - 3, j - 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i + 1, j - 1, 'M') && eq(i + 2, j - 2, 'A') && eq(i + 3, j - 3, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i + 1, j, 'M') && eq(i + 2, j, 'A') && eq(i + 3, j, 'S')) res ++;
                if (eq(i, j, 'X') && eq(i - 1, j, 'M') && eq(i - 2, j, 'A') && eq(i - 3, j , 'S')) res ++;

            }
        }


        System.out.println(Arrays.deepToString(array));
        System.out.println(res);

    }

    private static boolean eq( int i, int j, char x) {
        return i >= 0 && i < n && j >= 0 && j < m && array[i][j] == x;

    }

}

