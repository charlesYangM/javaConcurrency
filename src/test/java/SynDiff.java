import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/9/009.
 */
public class SynDiff {
    public static synchronized void printlf(String id){
        System.out.println(id);
        for (;;);
    }

    public static synchronized void printlff(String id){
//        try {
////            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(id);
    }
    public synchronized void printlff3(){
//        try {
////            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("ha");
    }
    public synchronized void printlff4(){
//        try {
////            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("ha");
    }
    public static void main(String[] args) {

        final SynDiff synDiff = new SynDiff();
        final SynDiff synDiff2 = new SynDiff();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synDiff.printlf("1");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synDiff2.printlff("2");
            }
        });


        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
