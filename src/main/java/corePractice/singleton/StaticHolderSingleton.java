package corePractice.singleton;

/**
 * Created by CharlesYang on 2018/7/8/008.
 */
public class StaticHolderSingleton {
    private StaticHolderSingleton(){

    }

    private static class InstanceHolder{
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public void someService(){
        System.out.println(this.hashCode());
        System.out.println(Thread.currentThread().getName() + " : i am the right method to get singleTon");
    }

    public static void main(String[] args) {
        StaticHolderSingleton.getInstance().someService();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                StaticHolderSingleton.getInstance().someService();
            }
        });

        t.start();
    }
}
