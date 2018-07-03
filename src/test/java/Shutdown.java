import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/8.
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);

        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        two.cancel();//�˴�Ӧ��ע�⵽ two �������߳����涼��ʹ�õ�
    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i = " + i);
        }
        public void cancel(){
            on = false;
        }
    }
}
