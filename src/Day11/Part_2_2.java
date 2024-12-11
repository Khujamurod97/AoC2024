package Day11;

import java.util.*;

public class Part_2_2 {


    public static class PairLong /*implements Comparable<PairLong>*/ {
        public Long first;
        public Long second;
        public PairLong(Long first, Long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairLong pairLong = (PairLong) o;
            return Objects.equals(first, pairLong.first) && Objects.equals(second, pairLong.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }


    }

    private static Map<PairLong, Long> map = new HashMap<>();

    private static Long rec(PairLong pair) {
        if (map.containsKey(pair)) {
            return map.get(pair);
        }
        if (pair.second == 0) {
            map.put(pair, 1L);
            return 1L;
        }
        if (pair.first == 0) {
            Long res = rec(new PairLong(1L, pair.second - 1));
            map.put(pair, res);
            return res;
        }
        String s = pair.first.toString();
        if (s.length() % 2 == 0) {
            Long res = rec( new PairLong(Long.valueOf(s.substring(0, s.length() / 2)), pair.second - 1)) +
                    rec(new PairLong(Long.valueOf(s.substring(s.length() / 2, s.length())), pair.second - 1));
            map.put(pair, res);
            return res;
        }
        Long res = rec(new PairLong(pair.first * 2024, pair.second - 1));
        map.put(pair, res);
        return res;
    }

    public static void main(String[] args) {

        Map<String, Long> cache = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Long> list = Arrays.stream(line.split(" ")).map(Long::valueOf).toList();
        long count = 0L;
        for (Long l : list) {
            count += rec(new PairLong(l, 75L));
        }
        System.out.println(count);




    }



}
