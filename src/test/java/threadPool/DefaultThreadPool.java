package threadPool;

import com.sun.xml.internal.bind.v2.model.runtime.RuntimeNonElement;
import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by CharlesYang on 2018/4/10.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();


    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(workerNum);
    }

    public void execute(Job job) {
        if (job != null){
            synchronized (jobs){
                jobs.add(job);
                jobs.notifyAll();
            }
        }
    }

    public void shutdown() {
        for (Worker worker : workers){
            worker.shutdown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs){
            if ((num + this.workerNum) > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;

            }
            initializeWorkers(num);
            //TODO 为什么这个增加计数的代码没有放到initialize中？因为add这个行为更倾向于数字计算？
            this.workerNum  += num;
        }
    }

    public void removeWoker(int num) {
        synchronized (jobs){
            if (num >= this.workerNum){
                throw new IllegalArgumentException("beyond workNum");
            }

            int count = 0;

            while (count < num){
                Worker worker = workers.get(count);
                if (workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int num){
        for (int i = 0; i < num; i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable{
        private volatile boolean running = true;
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null){
                    try {
                        job.run();
                    }catch (Exception ex){

                    }
                }
            }

        }
        public void shutdown(){
            running = false;
        }
    }
}
