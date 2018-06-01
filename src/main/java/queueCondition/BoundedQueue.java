package queueCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CharlesYang on 2018/5/31.
 */
public class BoundedQueue<Item> extends Queue<Item> {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    @Override
    public void enqueue(Item o) {
        lock.lock();
        try {
            /**
             * 这里本来打算用if 但如果是if，就会发生只抢夺一次的情况，而如果是用
             * while，即使多次发生获取不到的情况也会在此等待，而不是if 的直接执行后面的语句
             */
            int MAX_ITEM = 3;
            while (super.size() == MAX_ITEM) {

                /**
                 * 这个notFull 一开始看起来会令人迷惑，为什么不满了还要等待呢。这里就得说一下，java使用的叫做虚方法
                 * 而方法之前的对象叫做接受者(receiver) 所以这里应该解读成 等待 一个 不满 的条件
                 */
                System.out.println("max item enq wait " + Thread.currentThread().getName());
                notFull.await();
                System.out.println("after await");
            }
            super.enqueue(o);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public Item dequeue() {
        lock.lock();//获取锁是为了保证可见性
        try{
            while (isEmpty()){
                notEmpty.await();
            }
            Item item = super.dequeue();
            notFull.signal();
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }
}
