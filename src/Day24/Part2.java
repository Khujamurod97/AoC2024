package Day24;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> values = new HashMap<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.isBlank()) break;
            Pattern pattern = Pattern.compile("([a-z0-9]+): (\\d+)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                values.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
            } else {
                throw new IllegalArgumentException("Invalid input");
            }
        }

        List<String[]> commands = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.isBlank()) break;
            Pattern pattern = Pattern.compile("([a-z0-9]+) ([A-Z]+) ([a-z0-9]+) -> ([a-z0-9]+)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                commands.add(new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)});
            } else {
                throw new IllegalArgumentException("Invalid input");
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        for (String[] command : commands) {
            String a = command[0];
            String o = command[1];
            String b = command[2];
            String c = command[3];
            if (!values.containsKey(a) || !values.containsKey(b)) {
                queue.add(command);
                values.remove(c);
                continue;
//                throw new IllegalArgumentException("value not found " + a);
            }
            if (o.equals("OR")) {
                values.put(c, values.get(a) | values.get(b));
            } else if (o.equals("AND")) {
                values.put(c, values.get(a) & values.get(b));
            } else if (o.equals("XOR")) {
                values.put(c, values.get(a) ^ values.get(b));
            } else {
                throw new IllegalArgumentException("Invalid operation " + c);
            }

//            Queue<String[]> newQueue = new LinkedList<>();
//
//            while (!queue.isEmpty()) {
//                String[] pool = queue.poll();
//                String a1 = pool[0];
//                String o1 = pool[1];
//                String b1 = pool[2];
//                String c1 = pool[3];
//                if (!values.containsKey(a1) || !values.containsKey(b1)) {
//                    newQueue.add(pool);
//                   continue;
//                }
//                if (o1.equals("OR")) {
//                    values.put(c1, values.get(a1) | values.get(b1));
//                } else if (o1.equals("AND")) {
//                    values.put(c1, values.get(a1) & values.get(b1));
//                } else if (o1.equals("XOR")) {
//                    values.put(c1, values.get(a1) ^ values.get(b1));
//                } else {
//                    throw new IllegalArgumentException("Invalid operation " + c);
//                }
//            }
//            queue = newQueue;
        }

        while(!queue.isEmpty()) {
            Queue<String[]> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                String[] pool = queue.poll();
                String a1 = pool[0];
                String o1 = pool[1];
                String b1 = pool[2];
                String c1 = pool[3];
                if (!values.containsKey(a1) || !values.containsKey(b1)) {
                    newQueue.add(pool);
                    continue;
                }
                if (o1.equals("OR")) {
                    values.put(c1, values.get(a1) | values.get(b1));
                } else if (o1.equals("AND")) {
                    values.put(c1, values.get(a1) & values.get(b1));
                } else if (o1.equals("XOR")) {
                    values.put(c1, values.get(a1) ^ values.get(b1));
                } else {
                    throw new IllegalArgumentException("Invalid operation ");
                }
            }
            queue = newQueue;
        }


        List<String[]> list = commands.stream()
                .filter(strings -> /*strings[1].equals("AND") && */(strings[0].startsWith("x") || strings[0].startsWith("y")))
                .peek(strings -> {
                    if (strings[0].startsWith("y")) {
                        String t = strings[0];
                        strings[0] = strings[2];
                        strings[2] = t;
                    }
                })
                .sorted(Comparator.comparing((String[] a) -> a[0]).thenComparing(a -> a[2]))
                .peek(strings -> System.out.println(strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3]))
//                .map(strings -> strings[0])
                .toList();
        int d = 0;
        for (String[] a : list.reversed()) {
            d = d + values.get(a[0]) + values.get(a[2]);
            System.out.print(Arrays.toString(a) + " " + (values.get(a[0]) & values.get(a[2])) + " " + d % 2);
            System.out.println();
            d /= 2;
        }
        System.out.println(d);

//        TreeSet<String> set = new TreeSet<>();
//        for (String[] a : list) {
//            String z = "z"+a[0].substring(1) ;
//            if (!a[3].equals(z)) {
//                set.add(a[3]);
//                set.add(z);
//            }
//        }
//        String collect = set.stream().collect(Collectors.joining(","));
//        System.out.println(collect);


    }
}
