package Day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        char[] a = line.toCharArray();

        Long s = 0L;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < a.length; i ++) {
            int t = a[i] - '0';
            int num = (i % 2 == 0) ? (i / 2) : -1;
            while(t -- > 0)  {
                list.add(num);
            }
        }
        System.out.println(list);
        int l = list.size();
        int i = 0; int j = list.size() - 1;
        while(i < j) {
            while(i < l && list.get(i) != -1) i ++;
            while(j >= 0 && list.get(j) == -1) j --;
            if (i < j) {
                int tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
            }
        }
        System.out.println(list);
        long res = 0;

        for(int k = 0; k < list.size(); k ++) {
            if (list.get(k) == -1) break;
            res += k * list.get(k);
        }
        System.out.println(res);

    }





}
