package queueCondition;

import junit.framework.TestCase;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/5/31.
 */

public class TestQueue extends TestCase{


    public void testBoundedQueue(){
        BoundedQueue<String> q = new BoundedQueue<String>();
        q.enqueue("qq");
        q.enqueue("qq");
        q.enqueue("qq");
        q.enqueue("qq");
        q.enqueue("qq");
        q.enqueue("qq");
        while (!q.isEmpty()){
            System.out.println(q.dequeue());
        }
    }

    public static void main(String[] args) {
//        Queue<String> q = new Queue<String>();
        final BoundedQueue<String> q = new BoundedQueue<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

//                System.out.println("ha");//有意思 加不加这句话尽然会对我们的程序有影响 2018-5-31  这个问题主要是因为
                                        //之前不是do while的结构，而是使用while的结构，使用while的情况下如果 t1 先运行
                                        //这个时候while 判断为空，则不进入循环，之后t1也就运行完毕，也就没有出队的请求了

                                        //后来经过猜想，
//                do{
//                    System.out.println(q.dequeue());
//                    try {
//                        TimeUnit.SECONDS.sleep(1);//如果不加这一秒的睡眠，那么会发生出队的线程一直消费 直到 没有数据可以出队
//                                                //t1 会因为条件判断 队列为空 并退出t1 线程 也就没有了消费者 发生这样的情况
//                                                //主要原因为 抢占锁的线程 虽然释放锁，但在非公平的情况下，刚释放锁的线程
//                                                // 在之后的竞争中获得锁的概率要比其他线程大得多
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } while (!q.isEmpty());
                while (true){//this is exactly what we want
//                    System.out.println("true");
                    System.out.println(q.dequeue());
                }
            }
        },"thread 1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                q.enqueue("qq");
                q.enqueue("qq");
                q.enqueue("qq");
                q.enqueue("qq");
                q.enqueue("qq");
                q.enqueue("qq");
            }
        },"thread 2");
        t2.start();


    }
}
