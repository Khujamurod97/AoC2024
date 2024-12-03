package Day3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            Pattern pattern = Pattern.compile("(mul)\\((\\d+)(\\,)(\\d+)\\)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                res +=  Long.valueOf(matcher.group(2)) *  Long.valueOf(matcher.group(4));
            }
        }
        System.out.println(res);

    }

}

//xmux(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))