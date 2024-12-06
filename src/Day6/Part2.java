package Day6;

import java.util.*;

public class Part2 {

    private static char[][] a;
    private static boolean[][] b;
    private static int n, m;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            input.add(line);
        }


        a = input.stream().map(str -> str.toCharArray()).toArray(char[][]::new);
        n = a.length;
        m = a[0].length;

        int is = -1, js = -1;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if (a[i][j] == '^') {
                    is = i;
                    js = j;
                    break;
                }
            }
        }


        b = new boolean[n][m];
        int[] curr = {is, js, -1, 0};

        while(true) {
            b[curr[0]][curr[1]] = true;
            int[] next = getNext(curr);
            if (in(next[0], next[1])) {
                if (a[next[0]][next[1]] == '#') {
                    next = getRight(curr);
                } else {
                    char tc = a[next[0]][next[1]];
                    a[next[0]][next[1]] = '#';
                    int[] tmp = getRight(curr);
                    boolean ok = false;
                    b = new boolean[n][m];
                    for(int i = 0; i < 100000; i ++) {
                        b[tmp[0]][tmp[1]] = true;
                        if (tmp[0] == curr[0] && tmp[1] == curr[1]) {
                            ok = true;
                            break;
                        }
                        int[] next1 = getNext(tmp);
                        if (in(next1[0], next1[1])) {
                           if (a[next1[0]][next1[1]] == '#') {
                               next1 = getRight(tmp);
                               if (!in(next1[0], next1[1])) {break;}
                           }
                        } else {
                            break;
                        }
                        tmp = next1;
                    }
                    if (ok) {

//                        System.out.println();
//                        for(int i = 0 ; i < n; i ++) {
//                            for(int j = 0 ; j < m; j ++) {
//                                if (b[i][j]) System.out.print(" X");
//                                else System.out.print(" .");
//                                if (b[i][j]) res ++;
//                            }
//                            System.out.println();
//                        }

                        res ++;
                    }
                    a[next[0]][next[1]] = tc;
                }
            } else {
                break;
            }
            curr = next;
        }






        System.out.println(res);

    }

    private static boolean in( int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static int[] getNext(int[] c) {
        return new int[]{c[0] + c[2], c[1] + c[3], c[2], c[3]};
    }

    private static int[] getRight(int[] c) {
        int ix = 0, jx = 0;
        if (c[2] == -1 && c[3] == 0) {ix = 0; jx = 1;} else
        if (c[2] == 0 && c[3] == 1) {ix = 1; jx = 0;} else
        if (c[2] == 1 && c[3] == 0) {ix = 0; jx = -1;} else {ix = -1; jx = 0;}
        return new int[]{c[0] + ix, c[1] + jx, ix, jx};
    }

    private static int getDir(int ix, int jx) {
        if (ix == -1 && jx == 0) return 1;
        if (ix == 1 && jx == 0) return 2;
        if (ix == 0 && jx == 1) return 4;
        if (ix == 0 && jx == -1) return 8;
        return 0;


    }



}

/*
.........................................................................................................X........................
......XXXXXXXXXXXXXXXXXXXXXXXXXXXX.......................................................................X........................
......X..........................X..............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.................X........................
......X..........................X..............X......................................X.................X........................
......X........................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........X........................
......X........................X.X..............X......................................X.....X...........X........................
......X........................X.X..............X....................................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..........
......X........................X.X..............X....................................X.X.....X...........X.............X..........
......X.......XXX..............X.X..............X....................................X.X.....X...........X.............X..........
......X.......X.XXXXXXXXX......X.X..............X....................................X.X.....X...........X.............X..........
......X.......X.XX......X......X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.......X..........
......X.......X.XX......X......X.X.X............X....................................X.X.....X...........X.....X.......X..........
......X.......X.XX......X......X.X.X............X....................................X.X.....X...........X.....X.......X..........
......X.......X.XX.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.....
......XXXXXXXXXXXX.....XX......X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........X.....X.......X....X.....
..............X..X.....XX......X.X..............X........................X...........X.XX................X.....X.......X....X.....
..............X..X.....XX......X.X..............X........................X...........X.XX................X.....X.......X....X.....
..............X..X.....XX......X.X..............X...........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX........XXXXXXXXXXXXXXXXXXXX.....
..............X..X.....XX......X.X.......XXXX...X...........X............X...........X.XX.......X.......XXXXXXXXXXXXXXXXXXXX......
..............X..X.....XX......X.X.......X..X...X.XXXXXXXXXXXXXXXXXXXXXX.X...........X.XX.......X.......X......X.......X...X......
..............X..X.....XX......X.X.......X..X...X.X.........X..........X.X...........X.XX.......X.......X......X.......X...X......
..............X..X.....XX......X.X.......X..X...X.X.........X..........X.X........XXXXXXXXXXXX..X.......X......X.......X...X......
..............X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X..X.......X......X.......X...X......
............XXXXXXXXXXXXXXXX...X.X.......X..X...X.X.........X..........X.X........X..X..X....X..X.......X......X.......X...X......
............X.X........XX..X...X.X.......X..X...X.X.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X..X.......X......X.......X...X......
............X.XXXXXXXXXXXXXX...X.X.......X..X...X.X.....X...X..........X.X........X..X..X..X.X..X.......X......X.......X...X......
............X..........XX......X.X.......X..X...X.X.....X...X..........X.X........X..X..X..X.X..X.......X......X.......X...X......
............X..........XX......X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX......X..XXXXXXXXXXXXXXXXXXXXXXXXXXX.......X...X......
............X..........XX......X.X.X.....X..X...X.X.....X...X..........X.X.X......X.....X..X.X..X.......X..............X...X......
............X..........XX......X.X.X.....X..X...X.X.....X...X..........X.X.X......X.....X..X.XXXXXXXXXXXXXXXXXXXXXX....X...X......
.........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX........X....X...X......
.........X..X..........XX......X.X.X.....X..X...X.X.....X...X..........X.X.X......X.....X..X.XX.X.......XX........X....X...X......
.........X..X.......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X..X.XX.X.......XX........X....X...X......
.........X..X.......X..XX......X.X.X.....X..X...X.X....XXX..X..........X.X.X......X...X.X..X.XX.X.......XX........X....X...X......
.........X..X.......X..XX......X.X.X.....X..X...X.X.....X...X..........X.X.X......X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X......
.........X..X.......X..XX......X.X.X.....X..X...X.X.........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX......
.........X..X.......X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..XX........X....XX.........
.........X..X.......X..XXX.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..XX........X....XX.........
.........X..X.......X..XXX.......X.X.....X..X...X.X....................X.X.X......X.X.X.X..X.XX.X.......XXXXXXXXXXXXXXXXX.........
.........X..X.......X..XXX.......X.X.....X..X...X.X....................X.X.X......X.X.X.X..X.XX.X....XXXXXXXXXXXXXXXX...X.........
.........X..X.......X..XXX.......X.X.....X..X...XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X....X...X........X.X...X.........
.........X..X.......X..XXX.......X.X.....X..X.....X....................X.X.X......X.X.X.X..X..X.X....X...X........X.X...X.........
.........X..X.......X..XXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X....X...X........X.X...X.........
.........X..X.......X..XXX.X.....X.X.....X..X.....X....................X.X.X......X.X.X.X..XX.X.X....X...X........X.X...X.........
.........X..XXXXXXXXXXXXXX.X.....X.X.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X......X.X.X.X..XX.X.X....X...X........X.X...X.........
.........X..........X..X.X.X.....X.X........X.....X....................X...X......X.X.X.X..XX.X.X....X...X........X.X...X.........
.........X..........X..X.X.X.....X.X........X.....X........XXXXXXXXXXXXXXXXXXXXXX.X.XXXXXXXXXXXXX....X...X........X.X...X.........
.........X..........X..X.X.X.....X.X........X.....X........X...........X...X....X.X...X.X..XX.X......X...X........X.X...X.........
.........X..........X..X.X.X.....X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X.X...X.........
.........X..........X..X.X.X.....X.X.X......X.....X........X...........X...X....X.X...X.X..XX.X......X...X....X...X.X...X.........
.........X..........X..X.X.X.....X.X.X......X.....X........X...........X...X....X.X...X.X..XX.X......X...X....X...X.X...X.........
.........X..........X..X.X.X.....X.X.X......X.....X........X...........X...X....X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X...X.........
.........X..........X..X.X.X.....X.X.X......X.....X........X...........X..XXXXXXXXXXXXXXXX.XX.X......X...X....X.....X...X.........
.........X..........X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X.....X...X.........
.........X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X..XX....X.....X.XX.XX.X......X........X.....X...X.........
.........X....X.....X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X.....X.XX.XX.X......X........X.....X...X.........
.........X....X.....X......X.....X.X.X......X.....X........X.......X...X..X.....X.....X.XX.XX.X......X........X.....X...X.........
.........X....X.....X......X.....X.X.X......X.....X........X.......X...X..X.....X.....X.XX.XX.X......X........X.....X...X.........
....XXXXXXXXXXXXXXXXXXXXXXXXX....X.X.X......X.....X........X.......X...X..X.....X.....X.XX.XX.X......X........X.....X...X.........
....X....X....X.....X......XX....X.X.X...XXXXXXXXXXXXXXXXXXXXXXXXX.X...X..X.....X.....X.XX.XX.X......X........X.....X...X.........
....X....X....X.....X......XX....X.X.X...X..X.....X........X.....X.X...X..X.....X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.........
....X....X....X.....X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X..X..X.XX.XX.X......X........X.....X.X.X.........
....X....X....X.....X....X.XX....X.X.X...X..X.....X........X.....X.X...X..X...X.X..X..X.XX.XX.X......X........X.....X.X.X.........
.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X..X...X.X..X..X.XX.XX.X......X........X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X.....X........X.....X.XX..X..X...X.X..X..X.XX.XX.X......X........X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X.....X........X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X.....X........X..X..X.XX..X..X...X.X..X..X.XX.XX.X......X......X.X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X......X......X.X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X..............X..X..X.XX..X..X...X.X..X..X.XX.X..X......X......X.X.....X.X.X.........
.X..X....X....X.....X....X.XX....X.X.X...X..X.......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X.X.X.........
.X..X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XX.X..X......X......X.XX....X.X.X.........
.X..X.........X.....X....X.XX....X.X.X...X..X.......X......X..X..X.XX..X..X...X.X..X....XX.X..X......X......X.XX....X.X.X.........
.X..X.........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.......X......X..X..X.XX..X..X...X.X..X....XX.X..X......X......X.XX....X.X.X.........
.X..X...............X....X.XX....X.X.X...X..........X......X..X..X.XX..X..X...X.X..X....XX.X..X......X......X.XX....X.X.X.........
.X..X...............X.XXXXXXXXXXXXXXXXXXXXX.........X......X..X..X.XX..X..X...X.X..X....XX.X..X......X......X.XX....X.X.X.........
.X..X...............X.X..X.XX....X.X.X...XX......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.....X......X.XX....X.X.X.........
.X..X...............X.X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XX..X..X...X.X..X....XX.X..XX.....X......X.XX....X.X.X.........
.X..X...............X.X....XX....X.X.X...XX......X..X......X..X....XX..X..X...X.X..X....XX.X..XXXXXXXXXXXXXXXXXXXXXXXXXXX.........
.X..X...............X.X....XX....X.X.X...XX...XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X.X...........
.X..X...............X.X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X....XX.X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....X....X.X.X...XX...X..X..X......X..X....XX..X..X...X....X....XX.X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....X....X.X.X...XX...X..X..X......X..X....XX..X..X...X....X....XX.X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XXXXXXXXXXXXXXXXXXXXXXXXXX.....X..X....XX..X..X...X....X....XX.X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XX...X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..X..XX.....X..X....XX..X..X...X....X....X..X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..X..XX.....X..X....XX..X..X...X....X....X..X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..X..XX.....X..X....XX..X..X...X....X....X..X...X.....X......X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..X..XX.....X..X....XX..X..X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..X..XX.....X..X....XX..X..X.X.X....X....X..X...X.....X....X.X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X....X..X...X.....X....X.X.XX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X.....XX.....X..X....XX..X..X.X......X....XXXXXXXXXXXXXXXXXXXXXXXX.X..X.X...........
.X..X...............X.X.....XX...X...X...XX...X.....XX.....X..X....XX..X..X.X......X.......X...X.....X....X.X.X..X..X.X...........
.X..X...............X.X.....XX...XXXXXXXXXXXXXXXXXXXXXX....X..X....XX..X..X.X......X.......X...X.....X....X.X.X..X..X.X...........
.X..X...............X.X.....XX...XX..X...XX...X.....XXX....X..X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X....X.X.X..X..X.X...........
.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX...XX..X...XX...X.....XXX....X..X..X.XX..X..X.X......X.......X...X...X.X....X.X.X..X..X.X...........
....X...............X.X......X...XX..X...XX...X.....XXX....X..X..X.XX..X..X.X......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X...........
....X...............X.XXXXXXXXXXXXX..X...XX...X.....XXX....X..X..X.XX..X..X.X..............X...X...X.X....X.X.X..X....X...........
....X...............X........X....X..X...XX...X.....XXX....X..X..X.XX..X..X.X..............X...X...X.X....X.X.X..X....X...........
....XXXXXXXXXXXXXXX.X........X....X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X..X.X..............X...X...X.X....X.X.X..X....X...........
....XX............X.X........X....X......XX...X.....XXX....X..X..X..X..X..X.X..............X...X...X.X....X.X.X..X....X...........
....XX............X.X........X....X......XX...X.....XXX....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X...X.X....X.X.X..X....X...........
....XX............X.X........X....X......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.................X...X.XXXXXX.X.X..X....X...........
....XX............X.X........X....X......XXX..X.....XXX.......X..X..X..X..X.XX.................X...X........X.X..X....X...........
....XX............X.X........XXXXXXXXXXXXXXX..X.....XXX.......X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........
....XX............X.X.............X......X.X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X..X................
....XX............X.X.............X......X.X........XXX.......X.....X..X..X.XX.................X...X..........X..X................
....XX............X.X.............X......X.X........XXX.......X.....X..X..X.XX.................X...X..........X..X................
....XX............X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X..........X..X................
....XX............X...............X......X.X........XXX.......X.....X..X..X.XX.....................X..........X..X................
....XX............X...............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X..X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X................
....XX............X......................X.X........XXX.......X........X..X..X.....................X.............X................
....XX............X......................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX..X..X.....................X.............X................
....XX............X........................X........XXX.......X...........X..X.....................X.............X................
....XX............X........................X........XXX.......X...........X..X.....................X........XXXXXXXXXXXXXXX.......
....XX............X........................XXXXXXXXXXXX.......X...........X..X.....................X........X....X........X.......
....XX............X.................................XX........X...........X..X.....................X........X....X........X.......
....XX............X.................................XX........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX........X....X........X.......
....XX............X.................................XX....................X..X..............................XXXXXX........X.......
....XX............X.................................XX....................X..X............................................X.......
....XXXXXXXXXXXXXXX.................................XX....................X..X............................................X.......
.....X..............................................XX....................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.......
.....X..............................................XX.......................X....................................................
.....X..............................................XX.......................X....................................................
.....X..............................................XX.......................X....................................................
.....X..............................................XXXXXXXXXXXXXXXXXXXXXXXXXX....................................................
.....X...............................................X............................................................................
.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX............................................................................
..................................................................................................................................
..................................................................................................................................
..................................................................................................................................
 */

