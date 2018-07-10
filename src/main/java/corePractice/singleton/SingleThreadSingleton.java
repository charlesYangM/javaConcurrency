package corePractice.singleton;

/**
 * Created by CharlesYang on 2018/7/8/008.
 */
public class SingleThreadSingleton {

    private static SingleThreadSingleton singleThreadSingleton = null;

    private SingleThreadSingleton() {

    }

    public static SingleThreadSingleton getSingleThreadSingleton() {


        if (singleThreadSingleton == null) {
            singleThreadSingleton = new SingleThreadSingleton();
        }

        return singleThreadSingleton;
    }

    public static SingleThreadSingleton getSingleThreadSingleton2() {

        synchronized (SingleThreadSingleton.class) {
            if (singleThreadSingleton == null) {
                singleThreadSingleton = new SingleThreadSingleton();
            }
        }
        return singleThreadSingleton;
    }

}
