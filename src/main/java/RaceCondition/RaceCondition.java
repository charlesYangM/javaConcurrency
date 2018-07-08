package RaceCondition;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class RaceCondition {
    public static void main(String[] args) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new WorkerThread(10, i );
        }

        for (Thread thread : threads){
            thread.start();
        }
    }

    private static class WorkerThread extends Thread {
        private final int requestCount;

        private WorkerThread(int requestCount, int id) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }
        @Override
        public void run(){
            int i = requestCount;
            String requestID;

            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while (i-- > 0){
                requestID = requestIDGenerator.nextID();
                processRequest(requestID);
            }
        }

        private void processRequest(String requestID) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s got RequstId %s %n",Thread.currentThread().getName(),
                    requestID);
        }


    }
}
