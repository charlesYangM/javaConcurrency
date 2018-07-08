package RaceCondition;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class NonAtomicAssignmentDemo implements Runnable {

    static long value = 0;

    private final long valueToset;

    public NonAtomicAssignmentDemo(long valueToset) {
        this.valueToset = valueToset;
    }

    public static void main(String[] args) {
        Thread updateThread1 = new Thread(new NonAtomicAssignmentDemo(0));

        Thread updateThread2 = new Thread(new NonAtomicAssignmentDemo(-1));

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });

        updateThread1.start();
        updateThread2.start();

        long snapshot;

        while(0 == (snapshot = value) || -1 == snapshot){
//            ps.print(snapshot);
        }

        System.err.printf("Unexpected data : %d(Ox%16x", snapshot, snapshot);
        ps.close();
        System.exit(0);
    }


    @Override
    public void run() {
        for (;;){
            value = valueToset;
        }
    }
}
