package Day18;

import java.util.*;


public class Part2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        int n = 7;
//        int m = 7;
//        int t = 12;
        int n = 71;
        int m = 71;
        int t = 1024;


        List<int[]> ord = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            int[] array = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            ord.add(array);
        }





        for(int t1 = t; t1 < ord.size(); t1++) {
            int[][] a = new int[n][m];
            for(int i = 0; i <= t1; i++) {
                int[] ints = ord.get(i);
                a[ints[1]][ints[0]] = -1;
            }

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0, 0});
            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int i = curr[0];
                int j = curr[1];
                if (i < 0 || i >= n || j < 0 || j >= m) continue;
                if (a[i][j] == -1) continue;
                if (a[i][j] == 0 || a[i][j] > curr[2]) {
                    a[i][j] = curr[2];
                    q.add(new int[]{i + 1, j, curr[2] + 1});
                    q.add(new int[]{i - 1, j, curr[2] + 1});
                    q.add(new int[]{i, j + 1, curr[2] + 1});
                    q.add(new int[]{i, j - 1, curr[2] + 1});
                }
            }
            if (a[n - 1][m - 1] == 0) {
                int i = ord.get(t1)[1];
                int j = ord.get(t1)[0];


                System.out.println("###" + ord.get(t1)[0] + "," + ord.get(t1)[1] + " " + a[ord.get(t1)[1]][ord.get(t1)[0]]);

                extracted(n, m, a);
                return;
            }







        }










    }

    private static void extracted(int n, int m, int[][] a) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (a[i][j] == -1) {
                    System.out.print('#');
                } else if (a[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('O');
                }
            }
            System.out.println();
        }
        System.out.println();
    }


}
