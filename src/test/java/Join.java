import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/9.
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
//        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + " 123 terminate");
    }

    static class Domino implements Runnable {
        private Thread previous;

        public Domino(Thread previous) {
            this.previous = previous;
        }

        public void run() {

            try {
                previous.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate");
        }

    }
}

