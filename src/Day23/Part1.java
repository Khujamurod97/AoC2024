package Day23;

import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.isBlank()) break;
            list.add(line);
        }
        String[][] a = list.stream().map(str -> str.split("-")).toArray(String[][]::new);
        Map<String, Set<String>> mp = new HashMap<>();
        Set<String> st = new HashSet<>();
        for (String[] r : a) {
            if (!mp.containsKey(r[0])) {mp.put(r[0], new HashSet<>());}
            mp.get(r[0]).add(r[1]);
            if (!mp.containsKey(r[1])) {mp.put(r[1], new HashSet<>());}
            mp.get(r[1]).add(r[0]);
            st.add(r[0]);
            st.add(r[1]);
        }
        List<String> comps = st.stream().toList();
        int count = 0;
        for(int i = 0; i < comps.size(); i++) {
            for(int j = i + 1; j < comps.size(); j++) {
                for(int k = j + 1; k < comps.size(); k++) {
                    if (comps.get(i).startsWith("t") || comps.get(j).startsWith("t") || comps.get(k).startsWith("t")) {
                        if (mp.get(comps.get(i)).contains(comps.get(j))
                                && mp.get(comps.get(j)).contains(comps.get(k))
                                && mp.get(comps.get(i)).contains(comps.get(k))
                        ) {
                            count ++;
                        }

                    }
                }
            }
        }
        System.out.println(count);
    }
}
