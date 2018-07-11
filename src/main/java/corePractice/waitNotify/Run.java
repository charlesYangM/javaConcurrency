package corePractice.waitNotify;

import util.Profiler;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/10/010.
 */
public class Run {
    static volatile boolean ready = false;
    public static void main(String[] args) {

        final User u = new User();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("t1 stared ....");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    synchronized (u) {
                        while(!ready){//这次之所以是循环，是为了保证再一次的申请的情况，何时会再一次申请？
                                        //wait返回之后，查看ready发现还是没有被更新。这说明存在另外一个线程唤醒了wait
                                        //所以这里不是if 而是while
                            System.out.println("我不应该被执行");
                            u.wait();

                        }
                        //TODO 这个地方之后才是我们的目标动作应该存放的地方

                        //为什么代码的目标动作要和wait存放在一起？
                        //因为如果不放在一起可能产生竞态，目标动作被执行前的那一刻
                        //其他线程对共享变量的更新又使得保护条件重新不成立
                        System.out.println("wait finish");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start .....");
                synchronized (u) {
                    ready = true;
                    u.notify();

                    System.out.println("notify finished ");
                }
            }
        });


        t1.start();
        for (int i = 0; i < 7; i++){
            synchronized (u){
                u.notifyAll();
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t2.start();//t2的启动如果放在最后，则可以保证没有信号丢失，
                    // 如果与t1线程同时启动则会造成信号丢失，当然这可以通过while判断保护条件解决
        Profiler.begin();
        try {
            t1.join(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Profiler.end()/1000.0);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " sleep finished");
    }
}
