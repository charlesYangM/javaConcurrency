package BoundedBuffer;

import junit.framework.TestCase;
import testConcurrency.BoundedBuffer;

/**
 * Created by CharlesYang on 2018/7/1/001.
 */
public class BoundedBufferTest extends TestCase {

    public void testIsEmptyWhenConstructed(){
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertFalse(bb.isEmpty());
        assertTrue(bb.isFull());
    }

    public void testTakeBlocksWhenEmpty(){
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){
            public void run(){
                try {
                    int unused = bb.take();
                    fail();
                }catch (InterruptedException e){

                }
            }
        };

        try {
            taker.start();
            Thread.sleep(2000);
            taker.interrupt();
            taker.join(2000);
            assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            fail();
        }
    }
}
