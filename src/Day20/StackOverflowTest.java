package Day20;

// -Xss515m
public class StackOverflowTest {
    private static long step = 0L;

    private static void rec() {
        System.out.println(++step);
        rec();
    }

    public static void main(String[] args) {
        rec();
    }
}
