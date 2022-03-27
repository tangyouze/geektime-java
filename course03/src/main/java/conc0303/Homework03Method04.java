package conc0303;

import java.util.concurrent.*;

/**
 * 使用 executorService
 *
 * @author tyz
 */
public class Homework03Method04 {

    static int result = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<Integer> r = es.submit(new Callable<Integer>() {
            //            Integer result = sum();
//            return ResultSet
//                  });
            @Override
            public Integer call() throws Exception {
                return sum();

            }

            ;
        });


//        thread.start();
//
//        System.out.println("等待 thread.join");
//        thread.join();
        result = r.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        es.shutdown();

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
