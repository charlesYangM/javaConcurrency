import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/5/21.
 */
public class HappensBefore {

    private int i = 5;
    private volatile boolean flag ;
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }



    public void write(int j) {
        i = j;
    }

    public int read() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        final HappensBefore happensBefore = new HappensBefore();


        /***
         * JMM的最小安全性保障：线程执行时，读取到的值，要么是之前某个线程写入的值，要么是默认值(0 , null , false)
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                happensBefore.write(90);
                happensBefore.setFlag(true);
//                System.out.println("thread 1 : " + happensBefore.read());
                try {
                    System.out.println(Thread.currentThread() + " thread 1 "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 这段代码中，true 与 90 总是同时出现，看起来像是t1,t2两个线程各自的内部正常运行，但若是将49Line 与 48line对换
         * 则 可能会出现 false与90出现的情况
         */
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(happensBefore.isFlag());
                System.out.println(happensBefore.read());
                try {
                    System.out.println(Thread.currentThread() + " thread 2 "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        System.out.println("one done" + Thread.currentThread() + "  "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        t2.join();
        System.out.println("second done" +Thread.currentThread() + "  "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
