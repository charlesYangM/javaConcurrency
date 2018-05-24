package priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/4/5.
 */
public class Priority {
    private static volatile boolean notstart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++){
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread: " + i);
            thread.setPriority(priority);
            thread.start();
        }
        notstart = false;
        TimeUnit.SECONDS.sleep(10);
    }
    static class Job implements Runnable{
        private int priority;
        private long jobCount;
        public Job(int priority){
            this.priority = priority;
        }
        public void run() {
            while (notstart){
                Thread.yield();
            }
            while (notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }

}
