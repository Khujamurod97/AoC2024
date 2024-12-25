package Day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();
        while (true) {
            List<String> lines= new ArrayList<>();
            for(int i = 0; i < 7; i ++) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            List<int[]> t = getArray(lines);
            if (t.get(0).length > 0) a.add(t.get(0));
            if (t.get(1).length > 0) b.add(t.get(1));
            String s = scanner.nextLine();
            if (s.equals("x")) break;

        }

        System.out.println("a ");
        for (int[] ints : a) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("b ");
        for (int[] ints : b) {
            System.out.println(Arrays.toString(ints));
        }

        int count = 0;
        for (int[] c : a) {
            for (int[] d : b) {
                boolean ok = true;
                for(int i = 0; i < 5 && ok; i ++) {
                    if (c[i]+ d[i] > 5) ok = false;
                }
                if (ok)count ++;
            }
        }
        System.out.println(count);
    }


    private static List<int[]> getArray(List<String> lines) {
        List<int[]> res = new ArrayList<>();
        char[][] a = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        if (a[0][0] == '#' && a[6][4] == '.') {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 5; j ++) {
                int count = 0;
                for(int i = 1; i < 7; i ++) {
                    if (a[i][j] == '#') count ++;
                }
                list.add(count);
            }
            int[] array = list.stream().mapToInt(i -> i).toArray();
            res.add(array);
            res.add(new int[]{});
            return res;
        } else {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 5; j ++) {
                int count = 0;
                for(int i = 1; i < 6; i ++) {
                    if (a[i][j] == '#') count ++;
                }
                list.add(count);
            }
            int[] array = list.stream().mapToInt(i -> i).toArray();
            res.add(new int[]{});
            res.add(array);
            return res;
        }

    }
}
