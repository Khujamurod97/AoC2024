package Day1;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> a = new ArrayList<>();
        Long res = 0L;
        Map<Long, Long> count = new HashMap<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            String[] s = line.split("\\s+");
            a.add(Long.parseLong(s[0]));
            count.put(Long.parseLong(s[1]), count.getOrDefault(Long.parseLong(s[1]), 0L) + 1);
        }
        a.sort(Long::compareTo);
        for(int i = 0; i < a.size(); i ++) {
            res += a.get(i) * count.getOrDefault(a.get(i), 0L);
        }
        System.out.println(res);

    }

}
