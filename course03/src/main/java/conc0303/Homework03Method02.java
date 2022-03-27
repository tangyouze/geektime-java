package conc0303;

/**
 * 多等一会儿 (不太靠谱, 但是大概率能返回成功)
 *
 * @author tyz
 */
public class Homework03Method02 {

    static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            Homework03Method02.result = sum();
        });
        thread.start();

        System.out.println("等待结果...10s");
        Thread.sleep(10 * 1000);

        // 这是得到的返回值

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
