package selfLock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by CharlesYang on 2018/4/17.
 */
public class TwinsLockTest {
    @Test
    public void test(){
        final Lock lock  = new TwinsLock();
        class Worker extends Thread{
            public Worker(String s) {
                super(s);
            }

            public void run(){
                while(true){
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++){
            Worker w = new Worker("thread " + i);
            w.setDaemon(true);
            w.start();
        }
        for (int i = 0; i < 10; i++){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
