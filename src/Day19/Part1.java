package Day19;

import java.util.*;


public class Part1 {

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

        int count = 0;
        for(String word : words) {

            int l = word.length();
            boolean[] v = new boolean[l];
            for(int i = 0; i < l; i++) {
                for(int j = 0; j < patterns.length; j++) {
                    int l1 = patterns[j].length();
                    if (i + 1 < l1) continue;
                    if (word.substring(i - l1 + 1, i + 1).equals(patterns[j])) {
                        if (i + 1 == l1 || v[i - l1] == true) {
                            v[i] = true;
                        }
                    }
                }
            }
            if (v[l - 1] == true) {
                System.out.println(word);
                count++;
            }
        }
        System.out.println(count);
    }

}
