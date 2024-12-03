package Day3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Long res = 0L;
        String lastCommand = "DO";

        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            Pattern pattern = Pattern.compile("(mul)\\((\\d+)(,)(\\d+)\\)");
            Matcher matcher = pattern.matcher(line);
            TreeMap<Integer, Operation> treeMap = new TreeMap<Integer, Operation>();
            while (matcher.find()) {
                treeMap.put(matcher.end(), new Operation("MUL", Long.valueOf(matcher.group(2)) *  Long.valueOf(matcher.group(4))));
            }

            Pattern pattern1 = Pattern.compile("(do\\(\\))");
            Matcher matcher1 = pattern1.matcher(line);
            while (matcher1.find()) {
                treeMap.put(matcher1.end(), new Operation("DO", null));
            }

            Pattern pattern2 = Pattern.compile("(don\\'t\\(\\))");
            Matcher matcher2 = pattern2.matcher(line);
            while (matcher2.find()) {
                treeMap.put(matcher2.end(), new Operation("DON'T", null));
            }

            for (Integer i : treeMap.navigableKeySet()) {
                Operation operation = treeMap.get(i);
                if (operation.type.equals("DO") || operation.type.equals("DON'T")) {
                    lastCommand = operation.type;
                } else if (lastCommand.equals("DO")) {
                    res += operation.value;
                }
                System.out.println(i + " " + operation.type + " " + operation.value);
            }





        }
        System.out.println(res);

    }

    private static class Operation {
        public String type;

        public Operation(String type, Long value) {
            this.type = type;
            this.value = value;
        }

        public Long value;
    }
}

//xmux(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
