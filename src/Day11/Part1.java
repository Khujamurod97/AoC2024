package Day11;

import java.util.*;

public class Part1 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Long> list = Arrays.stream(line.split(" ")).map(Long::valueOf).toList();
        long count = 0L;
        for (Long l : list) {
            Stack<Long[]> stack = new Stack<>();
            stack.add(new Long[]{l, 25L});
            while (!stack.isEmpty()) {
                Long[] arr = stack.pop();
                if (arr[1] == 0) {
                    count ++;
                    System.out.print(arr[0] + " ");
                } else {
                    if (arr[0] == 0L) {
                        stack.push(new Long[]{1L, arr[1] - 1});
                    } else {
                        String s = arr[0].toString();
                        if (s.length() % 2 == 0) {
                            stack.push(new Long[]{Long.valueOf(s.substring(s.length() / 2, s.length())), arr[1] - 1});
                            stack.add(new Long[]{Long.valueOf(s.substring(0, s.length() / 2)), arr[1] - 1});
                        } else {
                            stack.add(new Long[]{ arr[0] * 2024, arr[1] - 1 });
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println(count);



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
