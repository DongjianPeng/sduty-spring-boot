package top.murphypen.test;

import org.junit.Test;

public class Playground {

    public static void T(Runnable r) {
        try {
            r.run();
        } catch (Exception e) {
            System.out.println("1111111");
            throw e;
        } finally {

        }
    }

    @Test
    public void test() {
        try {
            T(() -> {
                System.out.println(5 / 0);
            });
        } catch (Exception e) {
            System.out.println("2222222");
        }
    }
}
