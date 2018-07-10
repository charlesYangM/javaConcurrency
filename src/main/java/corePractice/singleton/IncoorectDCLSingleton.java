package corePractice.singleton;

/**
 * Created by CharlesYang on 2018/7/8/008.
 */
public class IncoorectDCLSingleton {
    private static volatile IncoorectDCLSingleton singleton = null;//增加了volatile之后保证了不可重排序也保障了可见性

    private IncoorectDCLSingleton(){

    }

    public IncoorectDCLSingleton getSingleton(){
        if (singleton == null){
            synchronized (IncoorectDCLSingleton.class){
                if (singleton == null){
                    singleton = new IncoorectDCLSingleton();
                }
            }
        }

        return singleton;
    }
}
