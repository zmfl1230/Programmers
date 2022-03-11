package BitMask;

public class Test {
    public static void main(String[] args) {
        int test = 1;
        System.out.println(test << 2);

        System.out.println(1 >> 2);
        System.out.println(13 >> 1);

        // 3개의 비트로 제한
        System.out.println(2 << 2);
        System.out.println((2 << 2) & ~(1 << 3));
    }
}
