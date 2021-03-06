package conc0303;

/**
 * 通过全局变量设置
 * 主线程通过轮训来判断有没有被处理完成
 * @author tyz
 */
public class Homework03Method01 {

    static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            Homework03Method01.result = sum();
        });
        thread.start();

        while (true) {
            if (Homework03Method01.result != 0) {
                break;
            }
            System.out.println("等待结果...");
            Thread.sleep(10);
        }

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
