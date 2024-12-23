package Day23;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.isBlank()) break;
            lines.add(line);
        }
        String[][] a = lines.stream().map(str -> str.split("-")).toArray(String[][]::new);
        Map<String, Integer> index = new HashMap<>();
        int lastIndex = 0;
        List<String> list = new ArrayList<>();

        Map<Integer, Set<Integer>> mp = new HashMap<>();
        Set<Set<Integer>> q = new HashSet<>();
        for (String[] r : a) {
            if (!index.containsKey(r[0])) { list.add(r[0]); index.put(r[0], lastIndex ++); }
            if (!index.containsKey(r[1])) { list.add(r[1]); index.put(r[1], lastIndex ++); }
            int i = index.get(r[0]);
            int j = index.get(r[1]);

            if (!mp.containsKey(i)) {mp.put(i, new HashSet<>());}
            mp.get(i).add(j);
            if (!mp.containsKey(j)) {mp.put(j, new HashSet<>());}
            mp.get(j).add(i);
            HashSet<Integer> set1 = new HashSet<>();
            set1.add(i);
            set1.add(j);
            q.add(set1);
        }


        Set<Integer> resultIntegerSet = new HashSet<>();
        for(int i = 3; i <= list.size(); i++) {
            Set<Set<Integer>> q1 = new HashSet<>();
            System.out.println(q.size());
            for(Set<Integer> st : q) {
                for(int k = 0; k < list.size(); k++) {
                    if (st.contains(k)) continue;
                    boolean ok = true;
                    for (Integer i1 : st) {
                        if (!mp.get(k).contains(i1)) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        HashSet<Integer> st1 = new HashSet<>();
                        st1.addAll(st);
                        st1.add(k);
                        q1.add(st1);
                    }

                }
            }
            if (q1.size() == 1) {
                resultIntegerSet = q1.stream().findFirst().get();
                break;
            }
            q = q1;
        }

        TreeSet<String> resultSet = new TreeSet<>();
        for (Integer i : resultIntegerSet) {
            resultSet.add(list.get(i));
        }
        String collect = resultSet.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
