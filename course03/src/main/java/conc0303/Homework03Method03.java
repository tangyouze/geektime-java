package conc0303;

/**
 * Thread.join 来等待thread结束
 *
 * @author tyz
 */
public class Homework03Method03 {

    static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            Homework03Method03.result = sum();
        });
        thread.start();

        System.out.println("等待 thread.join");
        thread.join();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(42);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
