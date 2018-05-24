package reentrantLock;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CharlesYang on 2018/4/17.
 */
public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);
//    private static CountDownLatch start;

    private static CountDownLatch start = new CountDownLatch(1){
        @Override
        public void await() throws InterruptedException {
            super.await();
            System.out.println(Thread.currentThread().getName() +  " count down is ok");
        }
    };

    @Test
    public void fair(){

        testLock(fairLock);
    }
    @Test
    public void unfair(){
        testLock(unfairLock);
    }
    private void testLock(Lock lock) {
        start = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Job(lock);
            thread.setName("" + i);
            thread.setDaemon(false);
            thread.start();
        }
        start.countDown();
    }

    private static class Job extends Thread {
        private Lock lock;
        private Job(Lock lock) {
            this.lock = lock;
        }
        public void run(){
            try {
                start.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 2; i++){
                lock.lock();
                System.out.print("lock by " + Thread.currentThread().getName());
                System.out.println(" waiting by " + ((ReentrantLock2)lock).getQueuedThreads());
                if (i == 1){
                    lock.unlock();
                }

            }
        }
        public String toString() {
            return getName();
        }
    }


    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }

        public Collection<Thread> getQueuedThreads(){
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

}
