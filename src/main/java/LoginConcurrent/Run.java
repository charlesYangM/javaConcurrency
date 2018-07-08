package LoginConcurrent;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class Run {

    static class ALogin extends Thread{
        @Override
        public void run(){
            LoginSerlet.doPost("a","aa");
        }
    }

    static class BLogin extends Thread{
        @Override
        public void run(){
            LoginSerlet.doPost("b","bb");
        }
    }

    static class MyThread extends Thread{
        private int i = 5;
        @Override
        public void run(){
            System.out.println(" i = " + (i--));
        }
    }
    public static void main(String[] args) {

        MyThread run = new MyThread();
        Thread a = new Thread(run);
        Thread b = new Thread(run);
        Thread c = new Thread(run);
        Thread d = new Thread(run);
        Thread e = new Thread(run);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
