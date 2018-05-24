/**
 * Created by CharlesYang on 2018/3/30.
 */
public class JMMwriteCache {
    static int x, a, y, b;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                b = 2;
                y = a;
            }
        });

        t1.start();
        t2.start();
        System.out.println("x : " + x);
        System.out.println("y : " + y);

    }

}