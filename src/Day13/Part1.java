package Day13;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        Long res = 0L;
        while(true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            Pattern pattern = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            int ax = Integer.parseInt(matcher.group(1));
            int ay = Integer.parseInt(matcher.group(2));
            line = scanner.nextLine();
            Pattern pattern1 = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
            Matcher matcher1 = pattern1.matcher(line);
            matcher1.find();
            int bx = Integer.parseInt(matcher1.group(1));
            int by = Integer.parseInt(matcher1.group(2));

            line = scanner.nextLine();
            Pattern pattern2 = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
            Matcher matcher2 = pattern2.matcher(line);
            matcher2.find();
            int px = Integer.parseInt(matcher2.group(1));
            int py = Integer.parseInt(matcher2.group(2));

            System.out.println(ax + " " + ay + " " + bx + " " + by + " " + px + " " + py );
            scanner.nextLine();


            long tokens = 0;
            for(int i = 0; i <= 100; i ++) {
                for(int j = 0; j <= 100; j ++) {
                    if ((ax * i + bx * j) == px && (ay * i + by * j) == py) {
                        if (tokens == 0 || tokens > 3 * i + 1 * j) {
                            tokens = 3 * i + 1 * j;
                        }
                    }
                }
            }
            System.out.println(tokens);
            res += tokens;

        }
        System.out.println(res);




    }








}
