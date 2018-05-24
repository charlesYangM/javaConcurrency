import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by CharlesYang on 2018/3/28.
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        System.out.println(getProcessID());
        new DeadLockDemo().deadlock();
    }

    private void deadlock(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A) {
                    try{
                        Thread.currentThread().sleep(2000);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }

                }
            }
        }

        );

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        }

        );

        t1.start();
        t2.start();
    }

    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
    }
}
