package reorder;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class SpeculativeLoadExample {

    private static boolean ready = false;

    private static int[] data = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8};

    static class Writer implements Runnable{

        @Override
        public void run() {
            int[] newData = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8};

            for (int i = 0; i < newData.length; i++){
                newData[i] = newData[i] - i;
            }

            data = newData;
//            try {
//                TimeUnit.MILLISECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            ready = true;
        }
    }

    static class Reader implements Runnable{

        @Override
        public void run() {
            int sum = 0;
            int[] snapShot;
            if (ready){
                snapShot = data;
                for (int i = 0; i < snapShot.length; i++){
                    sum += snapShot[i];
                }
            }
            System.out.println("the sum = " + sum);
        }
    }

    public static void main(String[] args) {
        Thread writer = new Thread(new Writer());
        Thread reader = new Thread(new Reader());

        writer.start();

        reader.start();

    }
}
