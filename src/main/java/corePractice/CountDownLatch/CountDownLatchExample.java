package corePractice.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by CharlesYang on 2018/7/11/011.
 */
public class CountDownLatchExample {
    private static final CountDownLatch latch =
            new CountDownLatch(4);

    private static int data;

    public static void main(String[] args) throws InterruptedException {
        Thread workderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++){
                    data = i;
                    latch.countDown();
                }
            }
        });

        workderThread.start();
        latch.await();
//        TimeUnit.SECONDS.sleep(3);
        System.out.printf("it's done data = %d", data);
    }
}
