package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/8.
 *  这个例子演示的是在抛出Exception的过程中 会将中断标识为清除，还原为false
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "sleepThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

    }

    static class SleepRunner implements Runnable{

        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable{

        public void run() {
            while (true);
        }
    }

}
