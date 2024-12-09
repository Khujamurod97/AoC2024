package Day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        char[] a = line.toCharArray();

        Long s = 0L;
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < a.length; i ++) {
            int t = a[i] - '0';
            int num = (i % 2 == 0) ? (i / 2) : -1;
            list.add(new int[]{t,num});
        }

        for (int[] ints : list) {
            for(int t1 = 0; t1 < ints[0]; t1 ++) {
                System.out.print((ints[1] == -1 ? '.' : ((char)('0' + ints[1]) )));
            }
        }
        System.out.println();

        List<int[]> list1 = new ArrayList<>();

        int l = list.size();
        for(int i = 0; i < l; i ++) {
            if (list.get(i)[1] != -1) {
                list1.add(new int[]{list.get(i)[0], list.get(i)[1]});
                continue;
            }
            int t = list.get(i)[0];
            for(int j = l - 1; j > i; j --) {
                if (list.get(j)[1] == -1) continue;
                if (list.get(j)[0] <= t) {
                    t -= list.get(j)[0];
                    list1.add(new int[]{list.get(j)[0], list.get(j)[1]});
                    list.get(j)[1] = -1;
                }
            }
            if (t > 0) {
                list1.add(new int[]{t, -1});
            }
        }

        for (int[] ints : list1) {
            for(int t1 = 0; t1 < ints[0]; t1 ++) {
                System.out.print((ints[1] == -1 ? '.' : ((char)('0' + ints[1]) )));
            }
        }
        System.out.println();
        long res = 0;
        int index = 0;
        for(int k = 0; k < list1.size(); k ++) {
            for(int k1 = 0; k1 < list1.get(k)[0]; k1 ++) {
                if (list1.get(k)[1] != -1)
                    res += index * list1.get(k)[1];
                index ++;
            }
        }
        System.out.println(res);

    }





}
