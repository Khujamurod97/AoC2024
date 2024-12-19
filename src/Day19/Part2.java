package Day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Part2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] patterns = scanner.nextLine().split(", ");
        scanner.nextLine();
        List<String> words = new ArrayList<>();
        while(true) {
            String word = scanner.nextLine();
            if (word.isBlank()) break;
            words.add(word);
        }

        long count = 0;
        for(String word : words) {

            int l = word.length();
            long[] v = new long[l];
            for(int i = 0; i < l; i++) {
                for(int j = 0; j < patterns.length; j++) {
                    int l1 = patterns[j].length();
                    if (i + 1 < l1) continue;
                    if (word.substring(i - l1 + 1, i + 1).equals(patterns[j])) {
                        if (i + 1 == l1) {
                            v[i] ++;
                        } else if (v[i - l1] > 0) {
                            v[i] += v[i - l1];
                        }
                    }
                }
            }
            if (v[l - 1] > 0) {
                System.out.println(v[l - 1] + " " + word);
                count+= v[l - 1];
            }
        }
        System.out.println(count);
    }

}
