package corePractice;

/**
 * Created by CharlesYang on 2018/7/3/003.
 */
public class WelcomeApp {
    public static void main(String[] args) throws InterruptedException {
        Thread welcomeThread = new Thread(new WelcomeApp.welcomeTask());

        welcomeThread.start();
        synchronized (welcomeThread){
            welcomeThread.wait(3000);
        }
//        welcomeThread.wait(1000);这个语句不好写因为start只是告知可以运行 还没开始，这个时候wait 也是没用啊，
//        welcomeThread.run();
        System.out.println("1.welcome! i am " + Thread.currentThread().getName());
        System.out.println(welcomeThread.getState());
        System.out.println("done");
        Thread.activeCount();
    }

    static class WelcomeThread extends Thread {
        @Override
        public void run(){
            System.out.println("2.welcome i am " + Thread.currentThread().getName());


        }
    }

    private static class welcomeTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2.welcome i am " + Thread.currentThread().getName());
            ThreadGroup group = Thread.currentThread().getThreadGroup();
            ThreadGroup topGroup = group;
// 遍历线程组树，获取根线程组
            while (group != null) {
                topGroup = group;
                group = group.getParent();
            }
// 激活的线程数加倍
            int estimatedSize = topGroup.activeCount() * 2;
            Thread[] slackList = new Thread[estimatedSize];
// 获取根线程组的所有线程
            int actualSize = topGroup.enumerate(slackList);
// copy into a list that is the exact size
            Thread[] list = new Thread[actualSize];
            System.arraycopy(slackList, 0, list, 0, actualSize);
            System.out.println("Thread list size == " + list.length);
            for (Thread thread : list) {
                System.out.println(thread.getName());
            }

        }
    }
}
