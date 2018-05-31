import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/5/18.
 */
public class VolatileDemo {
    private static int i = 0;
    private static boolean isStoped = false;

    public static void main(String[] args) throws InterruptedException {
        new SubThread().start();


        ArrayList<Thread> jobs = new ArrayList<>(100);
        for (int j = 0; j< 100; j++){
            Runnable script = new Runnable() {
                @Override
                public void run() {
                    i = 1000;
                    isStoped = true;
                    System.out.println("finish : " + Thread.currentThread());
                }
            };
            Thread temp_t = new Thread(script,"thread " + j);
            jobs.add(temp_t);
        }
        for (Thread t : jobs){
            t.start();
        }

    }



    private static class SubThread extends Thread{
        public void run(){
            while(!isStoped){
//                System.out.println(Thread.currentThread().getName());
                Thread.yield();
            }
            if (i == 0){
                System.out.println("------" + i);
            }


        }
    }
}
