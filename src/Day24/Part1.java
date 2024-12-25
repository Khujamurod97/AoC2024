package Day24;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
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

        TreeSet<String> set = new TreeSet<>();
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            if (entry.getKey().startsWith("z")) {
                set.add(entry.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : set) {
            sb.append(values.get(s));
        }
        sb = sb.reverse();

        System.out.println(sb);

        BigInteger result = BigInteger.ZERO;
        for (char c : sb.toString().toCharArray()) {
            result = result.multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(c == '1' ? 1 : 0));
        }
        System.out.println(result);

    }
}
