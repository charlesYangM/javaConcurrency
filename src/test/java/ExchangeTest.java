import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CharlesYang on 2018/4/21.
 */
public class ExchangeTest {
    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
                               @Override
                               public void run() {
                                   try{
                                       String A = "银行流水线A";
                                       exgr.exchange(A);
                                   } catch (InterruptedException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }
        );

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";

                try {
                    String A = exgr.exchange(B);
                    System.out.println("A和B 数据是否一致： " + A.equals(B) + ", A 录入的是："
                    + A + ", B 录入的是：" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
