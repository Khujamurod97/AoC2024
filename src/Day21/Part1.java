package Day21;

import java.util.*;

public class Part1 {

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


        Map<String, String> numberPathMap = new HashMap<>();
        Map<String, String> directionPathMap = new HashMap<>();


        calcNumericPathMap(numericKeypad, numberPathMap);

        calcDirectionalPathMap(directionalKeypad, directionPathMap);

        Long res = 0L;
        while(true) {
            String s = scanner.nextLine();
            if (s.isBlank()) break;
            String s1 = next(s, numberPathMap);
            String s2 = next(s1, directionPathMap);
            String s3 = next(s2, directionPathMap);
            System.out.println(Long.valueOf(s.substring(0, s.length() - 1)));
            res += Long.valueOf(s.substring(0, s.length() - 1)) * s3.length();
        }
        System.out.println(res);

    }

    private static String next(String s,  Map<String, String> pathMap) {
        char prev = 'A';
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(pathMap.get("" + prev + c) + "A");
            prev = c;
        }
        System.out.println(sb.length() + " " + sb);
        return sb.toString();
    }


    private static void calcDirectionalPathMap(char[][] directionalKeypad, Map<String, String> pathMap) {
        for(int i = 0; i < directionalKeypad.length; i++) {
            for(int j = 0; j < directionalKeypad[i].length; j++) {
                if (directionalKeypad[i][j] == '#') continue;

                boolean[][] visited = new boolean[directionalKeypad.length][directionalKeypad[i].length];

                Queue<int[]> queue = new LinkedList<>();
                Queue<String> stringQueue = new LinkedList<>();
                queue.add(new int[]{i, j, 0});
                stringQueue.add("");
                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    String currentString = stringQueue.poll();
                    int i1 = current[0];
                    int j1 = current[1];
                    pathMap.put("" + directionalKeypad[i][j]+ directionalKeypad[i1][j1], currentString);
                    visited[i1][j1] = true;
                    char lastChar = currentString.length() > 0  ?currentString.charAt(currentString.length() - 1) : 'A';
                    String charSequence = ">^v<";
                    if (lastChar == '^') charSequence = "^v><";
                    if (lastChar == 'v') charSequence = "v^><";
                    if (lastChar == '<') charSequence = "<v^>";
                    if (lastChar == '>') charSequence = ">v^<";

                    for (char c : charSequence.toCharArray()) {
                        if (c == 'v' && canGo(i1 + 1, j1, 2, 3, visited, directionalKeypad)) {
                            queue.add(new int[]{i1 + 1, j1});
                            stringQueue.add(currentString + "v");
                        }
                        if (c == '<' && canGo(i1, j1 - 1, 2, 3, visited, directionalKeypad)) {
                            queue.add(new int[]{i1, j1 - 1});
                            stringQueue.add(currentString + "<");
                        }
                        if (c == '^' && canGo(i1 - 1, j1, 2, 3, visited, directionalKeypad)) {
                            queue.add(new int[]{i1 - 1, j1});
                            stringQueue.add(currentString + "^");
                        }
                        if (c == '>' && canGo(i1, j1 + 1, 2, 3, visited, directionalKeypad)) {
                            queue.add(new int[]{i1, j1 + 1});
                            stringQueue.add(currentString + ">");
                        }
                    }

                }
            }
        }
    }

    private static void calcNumericPathMap(char[][] numericKeypad, Map<String, String> pathMap) {
        for(int i = 0; i < numericKeypad.length; i++) {
            for(int j = 0; j < numericKeypad[i].length; j++) {
                if (numericKeypad[i][j] == '#') continue;

                boolean[][] visited = new boolean[numericKeypad.length][numericKeypad[i].length];

                Queue<int[]> queue = new LinkedList<>();
                Queue<String> stringQueue = new LinkedList<>();
                queue.add(new int[]{i, j, 0});
                stringQueue.add("");
                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    String currentString = stringQueue.poll();
                    int i1 = current[0];
                    int j1 = current[1];
                    pathMap.put("" + numericKeypad[i][j]+ numericKeypad[i1][j1], currentString);
//                    System.out.println(numericKeypad[i][j] + " " + numericKeypad[i1][j1] + " " + currentString);
                    visited[i1][j1] = true;
                    char lastChar = currentString.length() > 0  ?currentString.charAt(currentString.length() - 1) : 'A';
                    String charSequence = ">^v<";
                    if (lastChar == '^') charSequence = "^v><";
                    if (lastChar == 'v') charSequence = "v^><";
                    if (lastChar == '<') charSequence = "<v^>";
                    if (lastChar == '>') charSequence = ">v^<";
                    for (char c : charSequence.toCharArray()) {
                        if (c == 'v' && canGo(i1 + 1, j1, 4, 3, visited, numericKeypad)) {
                            queue.add(new int[]{i1 + 1, j1});
                            stringQueue.add(currentString + "v");
                        }
                        if (c == '<' && canGo(i1, j1 - 1, 4, 3, visited, numericKeypad)) {
                            queue.add(new int[]{i1, j1 - 1});
                            stringQueue.add(currentString + "<");
                        }
                        if (c == '^' && canGo(i1 - 1, j1, 4, 3, visited, numericKeypad)) {
                            queue.add(new int[]{i1 - 1, j1});
                            stringQueue.add(currentString + "^");
                        }
                        if (c == '>' && canGo(i1, j1 + 1, 4, 3, visited, numericKeypad)) {
                            queue.add(new int[]{i1, j1 + 1});
                            stringQueue.add(currentString + ">");
                        }
                    }
//
//                    if (canGo(i1, j1 - 1, 4, 3, visited, numericKeypad)) {
//                        queue.add(new int[]{i1, j1 - 1});
//                        stringQueue.add(currentString + "<");
//                    }
//                    if (canGo(i1 + 1, j1, 4, 3, visited, numericKeypad)) {
//                        queue.add(new int[]{i1 + 1, j1});
//                        stringQueue.add(currentString + "v");
//                    }
//                    if (canGo(i1 - 1, j1, 4, 3, visited, numericKeypad)) {
//                        queue.add(new int[]{i1 - 1, j1});
//                        stringQueue.add(currentString + "^");
//                    }
//                    if (canGo(i1, j1 + 1, 4, 3, visited, numericKeypad)) {
//                        queue.add(new int[]{i1, j1 + 1});
//                        stringQueue.add(currentString + ">");
//                    }
                }
            }
        }
    }


    private static boolean canGo(int i, int j, int n, int m, boolean[][] visited, char[][] a) {
        if (i < 0 || i >= n || j < 0 || j >= m) return false;
        if (visited[i][j]) return false;
        if (a[i][j] == '#') return false;
        return true;
    }


}
