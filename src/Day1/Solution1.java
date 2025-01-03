package Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> a = new ArrayList<>();
        List<Long> b = new ArrayList<>();
        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            String[] s = line.split("\\s+");
            a.add(Long.parseLong(s[0]));
            b.add(Long.parseLong(s[1]));
        }
        a.sort(Long::compareTo);
        b.sort(Long::compareTo);
        for(int i = 0; i < a.size(); i ++) {
            res += Math.abs(a.get(i) - b.get(i));
        }
        System.out.println(res);

    }

}
