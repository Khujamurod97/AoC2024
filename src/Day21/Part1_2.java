package Day21;

import java.util.*;

public class Part1_2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] numericKeypad = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
                {'#', '0', 'A'}
        };

        char[][] directionalKeypad = {
                {'#', '^', 'A'},
                {'<', 'v', '>'}
        };

        int[][] dirs = {
                {-1, 0, '^'},
                { 1, 0, 'v'},
                {0, -1, '<'},
                {0,  1, '>'}
        };


        Map<String, List<String>> numberPathMap = new HashMap<>();
        Map<String, List<String>> directionPathMap = new HashMap<>();


        calcNumericPathMap(numericKeypad, numberPathMap);

        calcNumericPathMap(directionalKeypad, directionPathMap);

        Long res = 0L;
        while(true) {
            String s = scanner.nextLine();
            if (s.isBlank()) break;
            List<String> s1 = next(List.of(s), numberPathMap);
            List<String> s2 = next(s1, directionPathMap);
            List<String> s3 = next(s2, directionPathMap);
            int len = s3.getFirst().length();
            for (String string : s3) {
                len = Math.min(len, string.length());
            }
            System.out.println(Long.valueOf(s.substring(0, s.length() - 1)));
            res += Long.valueOf(s.substring(0, s.length() - 1)) * len;
        }
        System.out.println(res);

    }

    private static List<String> next(List<String> ss,  Map<String, List<String>> pathMap) {
        List<String> res = new ArrayList<>();
        for (String s : ss) {
            char prev = 'A';
            LinkedList<String> list = new LinkedList<>();
            list.add("");
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                int len = list.size();
                for(int i = 0; i < len; i++) {
                    List<String> strings = pathMap.get("" + prev + c);
                    String str = list.poll();
                    for (String string : strings) {
                        list.add(str + string + "A");
                    }
                }
                prev = c;
            }
            res.addAll(list);
        }
        return res;
    }


    private static void calcNumericPathMap(char[][] keypad, Map<String, List<String>> pathMap) {
        int n = keypad.length;
        int m = keypad[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (keypad[i][j] == '#') continue;

                boolean[][] visited = new boolean[n][m];

                Queue<int[]> queue = new LinkedList<>();
                Queue<String> stringQueue = new LinkedList<>();
                queue.add(new int[]{i, j, 0});
                stringQueue.add("");
                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    String currentString = stringQueue.poll();
                    int i1 = current[0];
                    int j1 = current[1];
                    String key = "" + keypad[i][j] + keypad[i1][j1];
                    if (pathMap.containsKey(key)) {
                        String first = pathMap.get(key).getFirst();
                        if (first.length() > currentString.length()) {
                            ArrayList<String> list = new ArrayList<>();
                            list.add(currentString);
                            pathMap.put(key, list);
                        } else if (first.length() == currentString.length()) {
                            pathMap.get(key).add(currentString);
                        } else {
                            continue;
                        }
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(currentString);
                        pathMap.put(key, list);
                    }
                    visited[i1][j1] = true;


                    if (canGo(i1, j1 - 1, n, m, visited, keypad)) {
                        queue.add(new int[]{i1, j1 - 1});
                        stringQueue.add(currentString + "<");
                    }
                    if (canGo(i1 + 1, j1, n, m, visited, keypad)) {
                        queue.add(new int[]{i1 + 1, j1});
                        stringQueue.add(currentString + "v");
                    }
                    if (canGo(i1 - 1, j1, n, m, visited, keypad)) {
                        queue.add(new int[]{i1 - 1, j1});
                        stringQueue.add(currentString + "^");
                    }
                    if (canGo(i1, j1 + 1, n, m, visited, keypad)) {
                        queue.add(new int[]{i1, j1 + 1});
                        stringQueue.add(currentString + ">");
                    }
                }
            }
        }
    }


    private static boolean canGo(int i, int j, int n, int m, boolean[][] visited, char[][] a) {
        if (i < 0 || i >= n || j < 0 || j >= m) return false;
//        if (visited[i][j]) return false;
        if (a[i][j] == '#') return false;
        return true;
    }


}
