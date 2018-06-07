package concurrentHashMAP;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by CharlesYang on 2018/6/1.
 */
public class Main {
    public static void main(String[] args) {
//        ConcurrentHashMap
//        ConcurrentLinkedQueue
        while (Thread.activeCount() > 1)
            System.out.println("hello world");
            Thread.yield();

    }
}
