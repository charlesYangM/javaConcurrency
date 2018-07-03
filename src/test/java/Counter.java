import util.Profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CharlesYang on 2018/5/15.
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);

//    SelfDef selfDef = new SelfDef();
    private int i;
    public AtomicInteger getAtomicI() {
        return atomicI;
    }
//
//    public int getI() {
//        return selfDef.getI();
//    }
    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        final Counter cas = new Counter();

        List<Thread> ts = new ArrayList<Thread>(600);
        List<Thread> ts2 = new ArrayList<Thread>(600);

        for (int j = 0; j < 100; j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++){
                        cas.count();

                    }
                }
            });

            ts.add(t);

        }
        for (int j = 0; j < 100; j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++){
                        cas.safeCount();

                    }
                }
            });

            ts2.add(t);

        }
        Profiler.begin();
        for (Thread t : ts){
            t.start();
        }
        for (Thread t : ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count cost : " + Profiler.end() + " mills ");

        Profiler.begin();
        for (Thread t : ts2){
            t.start();
        }
        for (Thread t : ts2){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("safeCount cost : " + Profiler.end() + " mills ");
        System.out.println("the int i : " + cas.getI());
        System.out.println("the atomic i : " + cas.getAtomicI().get());

    }

    private void safeCount() {

        for (;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);//这一步保证了原子性的执行, 在上一步得到了I的值后，进行
            if (suc){
                break;
            }
        }

    }

    private void count() {

        synchronized (this){
            i++;
        }
//        for (;;){
//            boolean suc = selfDef.compareAndSet(selfDef.getI(), selfDef.getI()+1);//这一步保证了原子性的执行, 在上一步得到了I的值后，进行
//            if (suc){
//                break;
//            }
//        }

    }
}
