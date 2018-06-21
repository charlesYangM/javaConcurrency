package latch;

import util.Profiler;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/6/21/021.
 */
public class CountDownLacth {
    private static final CountDownLatch StartGate = new CountDownLatch(1);
    private static final CountDownLatch mainStart = new CountDownLatch(10);

    private static final ArrayList<Thread> threads = new ArrayList<>(10);
    public static void main(String[] args) {

        for (int i = 0; i < 10 ; i++){
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        StartGate.await();//从threadsStart更名为StartGate
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("i am " + Thread.currentThread().getName());
//                        mainStart.countDown(); 为了保证最终的main方法能够运行完毕，应该将此句话放在finally中
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        mainStart.countDown();
                    }
                }
            });
            threads.add(thread);
        }

        for(Thread thread : threads){
            thread.start();
        }

        Profiler.begin();
        StartGate.countDown();
        try {
            mainStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("cost : " + Profiler.end() + " mills ");

    }



}
