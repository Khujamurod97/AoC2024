package Day12;

import java.util.*;

public class Part2 {

    private static char[][] a;
    private static int n;
    private static int m;
    private static boolean[][] visited;

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
        visited = new boolean[n][m];


        long res = 0l;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] borderMap = new boolean[n][m];
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                int count = 0;
                int lenght = 0;
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int x = cur[0];
                    int y = cur[1];
                    count ++;
                    if (!in(x - 1, y) || a[x - 1][y] != a[x][y]) { lenght ++; borderMap[x][y] = true; }
                    if (!in(x + 1, y) || a[x + 1][y] != a[x][y]) { lenght ++; borderMap[x][y] = true; }
                    if (!in(x, y - 1) || a[x][y - 1] != a[x][y]) { lenght ++; borderMap[x][y] = true; }
                    if (!in(x, y + 1) || a[x][y + 1] != a[x][y]) { lenght ++; borderMap[x][y] = true; }

                    int x1, y1;
                    x1 = x - 1; y1 = y;
                    if (in(x1, y1) && a[x][y] == a[x1][y1] && !visited[x1][y1]) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }
                    x1 = x + 1; y1 = y;
                    if (in(x1, y1) && a[x][y] == a[x1][y1] && !visited[x1][y1]) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }
                    x1 = x; y1 = y - 1;
                    if (in(x1, y1) && a[x][y] == a[x1][y1] && !visited[x1][y1]) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }
                    x1 = x; y1 = y + 1;
                    if (in(x1, y1) && a[x][y] == a[x1][y1] && !visited[x1][y1]) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }

                }
                for(int i1 = 0; i1 < n; i1++) {
                    for(int j1 = 0; j1 < m; j1++) {
                        if (j1 > 0 && borderMap[i1][j1] && borderMap[i1][j1-1]) {
                            if (!in(i1 - 1,j1) || a[i1-1][j1] != a[i1][j1] && a[i1-1][j1-1] != a[i1][j1]) lenght --;
                            if (!in(i1 + 1, j1) || a[i1+1][j1] != a[i1][j1] && a[i1+1][j1-1] != a[i1][j1] ) lenght --;
                        }
                        if (i1 > 0 && borderMap[i1][j1] && borderMap[i1-1][j1]) {
                            if (!in(i1,j1-1) || a[i1][j1-1] != a[i1][j1] && a[i1-1][j1-1] != a[i1][j1]) lenght --;
                            if (!in(i1, j1+1) || a[i1][j1+1] != a[i1][j1] && a[i1-1][j1+1] != a[i1][j1] ) lenght --;
                        }
                    }
                }



                res += count * lenght;
                System.out.println(a[i][j] + " " + count + " " + lenght);
            }
        }
        System.out.println(res);


    }

    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }






}
