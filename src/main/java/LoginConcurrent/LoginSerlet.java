package LoginConcurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class LoginSerlet {
    private static String usernameRef;
    private static String passwordRef;

    public synchronized static void doPost(String username, String password){
        try{

            usernameRef = username;
            if (username.equals("a")){
                TimeUnit.SECONDS.sleep(5);
            }
            passwordRef = password;

            System.out.println("username = " + usernameRef + " password = "
            + passwordRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
