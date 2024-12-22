package Day22;

import java.util.*;


public class Part2 {

    private static class Four {
        private int a;
        private int b;
        private int c;
        private int d;
        public Four(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Four four = (Four) o;
            return a == four.a && b == four.b && c == four.c && d == four.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }

        @Override
        public String toString() {
            return "Four{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        List<Long> list = lines.stream().map(Long::parseLong).toList();
        Map<Long, Long> map = new HashMap<>();
        for (Long l : list) {
            Map<Long, Long> map1 = new HashMap<>();
            System.out.print(l + " ");
            Integer p3 = null;
            Integer p2 = null;
            Integer p1 = null;
            Integer c = null;
            Long lp = l % 10;
            for(int i = 0; i < 2000; i ++) {
                l = oper(l);
                p3 = p2;
                p2 = p1;
                p1 = c;
                c = (int) ((l % 10) - (lp % 10));
                System.out.print(l %10 + " " + c);
                if (p3 != null) {
                    Four seq = new Four(p3, p2, p1, c);
                    Long h = sequenceHash(p3, p2, p1, c);
                    if (!map1.containsKey(h)) {
                        map1.put(h, l % 10);
                    }
                    System.out.print(" " + seq);
                }
                System.out.println();

                lp = l;

            }

            for (Map.Entry<Long, Long> entry : map1.entrySet()) {
                if (map.containsKey(entry.getKey())) {
                   map.put(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }


        }

        Long mx = 0L;
        for (Long value : map.values()) {
            mx = Math.max(mx, value);
        }
        System.out.println(mx);


    }

    private static Long oper(Long l) {
        Long l1 = ((l * 64) ^ l) % 16777216;
        ;
        Long l2 = ((l1 / 32) ^ l1 ) % 16777216;
        Long l3 = ((l2 * 2048)  ^ l2) % 16777216;
       return l3;
    }

    private static Long sequenceHash(Integer a, Integer b, Integer c, Integer d) {
        return (a + 10) * 1000000L + (b + 10) * 10000L + (c + 10) * 100L + (d + 10);
    }


}
