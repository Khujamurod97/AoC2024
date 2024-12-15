package Day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {



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
            Long ax = Long.parseLong(matcher.group(1));
            Long ay = Long.parseLong(matcher.group(2));
            line = scanner.nextLine();
            Pattern pattern1 = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
            Matcher matcher1 = pattern1.matcher(line);
            matcher1.find();
            Long bx = Long.parseLong(matcher1.group(1));
            Long by = Long.parseLong(matcher1.group(2));

            line = scanner.nextLine();
            Pattern pattern2 = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
            Matcher matcher2 = pattern2.matcher(line);
            matcher2.find();
            Long px = Long.parseLong(matcher2.group(1)) + 10000000000000L;
            Long py = Long.parseLong(matcher2.group(2)) + 10000000000000L;

            System.out.println(ax + " " + ay + " " + bx + " " + by + " " + px + " " + py );
            scanner.nextLine();



            Long i = 0L; Long j = 0L;
            Long token = 0L;

            Long i0 = (bx * py - by * px);
            Long i1 = (bx * ay - by * ax);
            if (i0 % i1 == 0 && i0 >= i1) {
                Long j0 = px - ax * i0 / i1;
                Long j1 = bx;
                if (j0 % j1 == 0 && j0 >= j1) {
                    i = i0 / i1;
                    j = j0 / j1;
                    token = 3 * i + j;
                }
            }

            long j0 = py * ax - ay * px;
            long j1 = ax * by - ay * bx;
            boolean ok = false;
            if (j0 % j1 == 0 && j0 >= j1) {
                j = j0 / j1;
                if ((px - bx * j) % ax == 0) {
                    i = (px - bx * j) / ax;
                    if (i >= 0 && j >= 0) ok = true;
                }
            }
            if (ok && (token == 0 || token > 3 * i + j)) {
                token = 3 * i + j;
            }





            System.out.println(token);
            res += token;

        }
        System.out.println(res);




    }








}
