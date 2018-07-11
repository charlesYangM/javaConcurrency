package util;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/9.
 */
public class Profiler {
    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("cost : " + Profiler.end()/1000.0+ " s ");
//        System.out.println(1 + 5 > 4);
    }
}
