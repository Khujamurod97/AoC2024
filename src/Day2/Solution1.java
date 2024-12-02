package Day2;

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> a = new ArrayList<>();
        List<Long> b = new ArrayList<>();
        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            List<Long> list = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
            if (list.get(0) == list.get(1)) continue;
            boolean increases = list.get(0) < list.get(1);

            boolean ok = true;
            for(int i = 1; i < list.size(); i++) {
                if (increases && list.get(i - 1) >= list.get(i))  {
                    ok = false;
                    break;
                }
                long abs = Math.abs(list.get(i) - list.get(i - 1));
                if (abs > 3 || abs < 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println(line);
                res ++;
            }

        }
        System.out.println(res);

    }

}
