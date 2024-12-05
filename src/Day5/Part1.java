package Day5;

import java.util.*;
import java.util.stream.Stream;

public class Part1 {

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

        List<String> input2 = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            input2.add(line);
        }

        for (String s : input2) {
            Integer[] array1 = Arrays.stream(s.split("\\,")).map(Integer::valueOf).toArray(Integer[]::new);
            boolean OK = true;
            for(int i = 0; i < array1.length; i++) {
                for(int j = i + 1; j < array1.length; j++) {
                    boolean ok = false;
                    for(int k = 0; k < list.size(); k ++) {
                        if (list.get(k)[0] == array1[i] && list.get(k)[1] == array1[j]) ok = true;
                    }
                    if (!ok) OK = false;
                }
            }
            if (OK) {
                res += array1[array1.length / 2];
            }
        }

        System.out.println(res);

    }


}

