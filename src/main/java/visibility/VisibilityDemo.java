package visibility;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class VisibilityDemo {
    public static void main(String[] args) {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();

        Thread tc = new Thread(timeConsumingTask);

        tc.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeConsumingTask.cancel();

    }

    static class  TimeConsumingTask implements Runnable{

        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel){
                if (doExecute()){
                    break;
                }
            }
            if (toCancel){
                System.out.printf("Task was canceled");
            }else {
                System.out.println("Task done.");
            }
        }

        private boolean doExecute() {
            boolean isDone = false;
            System.out.printf("executing ....");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return isDone;
        }

        public void cancel(){
            toCancel = true;
            System.out.println(this + "     canceled ");
        }
    }
}
