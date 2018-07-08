package threadVisible;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class ThreadStartVisibility {
    static int data = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(){

            @Override
            public void run(){
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(data);
            }
        };

        data = 1;
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = 2;

    }
}
