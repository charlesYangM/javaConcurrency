import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/8.
 */
public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "printThread");
        printThread.setDaemon(true);

        printThread.start();
        TimeUnit.SECONDS.sleep(3);

        printThread.suspend();
        System.out.println("main suspend printThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

        printThread.resume();
        System.out.println("main resume printThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

        printThread.stop();
        System.out.println("main stop printThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable{
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName() + " Run at "
                + format.format(new Date()));

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
