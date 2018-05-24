import java.util.ArrayList;

/**
 * Created by CharlesYang on 2018/5/17.
 */
public class JvmTest {
    private int value;
    private static int y;
    public int inc(){
        return value + 1;
    }

    public int multipleParam(int i ,int j, int[][] array, String str){

        try {
            new Counter();
            new ArrayList<String>(6);
            System.out.println(array[1]);
            System.out.println(str);
            return i + j;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            int x =1;
            return x;
        }

    }
}
