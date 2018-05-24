import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CharlesYang on 2018/5/18.
 */
public class SelfDef {
    private  int i = 0;
    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = getUnsafe();
    private static final long valueOffset;

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (SelfDef.class.getDeclaredField("i"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
