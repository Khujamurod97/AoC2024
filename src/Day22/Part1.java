package Day22;

import java.util.*;


public class Part1 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        List<Long> list = lines.stream().map(Long::parseLong).toList();
        Long sum = 0L;
        for (Long l : list) {
            System.out.print(l + " ");
            for(int i = 0; i < 2000; i ++) {
                l = oper(l);
            }
            System.out.println(l);
            sum += l;
        }
        System.out.println(sum);


    }

    private static Long oper(Long l) {
        Long l1 = ((l * 64) ^ l) % 16777216;
        ;
        Long l2 = ((l1 / 32) ^ l1 ) % 16777216;
        Long l3 = ((l2 * 2048)  ^ l2) % 16777216;
       return l3;
    }


}
