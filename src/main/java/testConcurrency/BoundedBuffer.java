package testConcurrency;

import java.util.concurrent.Semaphore;

/**
 * Created by CharlesYang on 2018/7/1/001.
 */
public class BoundedBuffer<E> {
    private final Semaphore availableItems,availableSpaces;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity){
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException{
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : i;
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E x = doExtract();
        availableSpaces.release();
        return x;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }

}
