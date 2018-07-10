package corePractice.singleton;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/8/008.
 */
public class EnumBasedSingletonExample {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.class.getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Singleton.INSTANCE.service();
            }
        });
        t.start();
    }

    public static enum Singleton{
        INSTANCE;

        Singleton(){
            System.out.println("Singleton inited!");
        }

        public void service(){
            System.out.println("enum Singleton");
        }
    }
}
