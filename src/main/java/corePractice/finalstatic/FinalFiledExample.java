package corePractice.finalstatic;

/**
 * Created by CharlesYang on 2018/7/8/008.
 */
public class FinalFiledExample {
    final int x;
    int y;

    static FinalFiledExample finalFiledExample;

    public FinalFiledExample(){
        x = 1;
        y = 2;
    }

    public static void writer(){
        finalFiledExample = new FinalFiledExample();
    }

    public static int reader(){

        final FinalFiledExample finalFiledExample1 = finalFiledExample;
        if (finalFiledExample1 != null){
            int diff = finalFiledExample1.y - finalFiledExample1.x;
            return diff;
        }
        return 0;
    }
    public static FinalFiledExample getInstance(){
        finalFiledExample = new FinalFiledExample();
        return finalFiledExample;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000000000; i ++){
            writer();
            if (reader() == -1){
                System.out.println("..");
            }

        }

    }
}
