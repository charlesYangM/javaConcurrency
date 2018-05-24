import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/5/18.
 */
public class VolatileDemo {
    private static int i = 0;
    private static  boolean isStoped = false;

    public static void main(String[] args) throws InterruptedException {
        new SubThread().start();

        i = 1000;
        isStoped = true;
    }

    private static class SubThread extends Thread{
        public void run(){
            while(!isStoped){
                System.out.println(Thread.currentThread().getName());
                Thread.yield();
            }

            System.out.println(i);
        }
    }
}
