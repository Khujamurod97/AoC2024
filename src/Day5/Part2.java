package Day5;

import java.util.*;

public class Part2 {

    private static char[][] array;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long res = 0L;
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            input.add(line);
        }

        List<Integer[]> list = input.stream().map(str -> Arrays.stream(str.split("\\|")).map(Integer::valueOf).toArray(Integer[]::new)).toList();
        Map<Integer, Set<Integer>> next = new HashMap<>();
        Map<Integer, Set<Integer>> prev = new HashMap<>();
        for (Integer[] integers : list) {
            if (!next.containsKey(integers[0])) next.put(integers[0], new HashSet<>());
            if (!next.containsKey(integers[1])) next.put(integers[1], new HashSet<>());
            if (!prev.containsKey(integers[0])) prev.put(integers[0], new HashSet<>());
            if (!prev.containsKey(integers[1])) prev.put(integers[1], new HashSet<>());
            next.get(integers[0]).add(integers[1]);
            prev.get(integers[1]).add(integers[0]);
        }



        List<String> input2 = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            input2.add(line);
        }

        for (String s : input2) {
            Integer[] array1 = Arrays.stream(s.split("\\,")).map(Integer::valueOf).toArray(Integer[]::new);
            boolean changed = false;
            for(int i = 0; i < array1.length; i++) {
                for(int j = i + 1; j < array1.length; j++) {
                    if (next.get(array1[j]).contains(array1[i])) {
                        changed = true;
                        int t = array1[i];
                        array1[i] = array1[j];
                        array1[j] = t;
                    }
                }
            }
            if (changed) {
                res += array1[array1.length / 2];
            }
        }

        System.out.println(res);

    }


}

