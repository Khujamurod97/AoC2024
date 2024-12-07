package Day7;

import java.math.BigInteger;
import java.util.*;

public class Part1 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            lines.add(line);
        }

        BigInteger res =BigInteger.ZERO;
        for (String line : lines) {
            String[] split = line.split("\\:");
            BigInteger total = new BigInteger(split[0]);
            BigInteger[] arr = Arrays.stream(split[1].trim().split(" ")).map(BigInteger::new).toArray(BigInteger[]::new);
            Queue<BigInteger> q = new LinkedList<>();
            q.add(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                BigInteger item = arr[i];
                int qs = q.size();
                for(int j = 0; j < qs; j++) {
                    BigInteger t = q.poll();
                    q.add(t.add(item));
                    q.add(t.multiply(item));
                }

            }
            boolean ok = false;
            while (!q.isEmpty()) {
                BigInteger t = q.poll();
                if (t.equals(total)) {
                    ok = true;
                    break;
                }
            }
            if (ok) res = res.add(total);
        }
        System.out.println(res);

    }




}
