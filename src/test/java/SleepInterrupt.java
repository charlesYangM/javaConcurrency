import threadPool.ThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/5/24.
 */
public class SleepInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getLocalizedMessage());
                }
            }
        };

        Thread t1 = new Thread(script);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }


}
