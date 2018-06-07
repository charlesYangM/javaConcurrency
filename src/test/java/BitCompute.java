/**
 * Created by CharlesYang on 2018/5/31.
 */
public class BitCompute {
    public static void main(String[] args) {
        int c = 2;
        int bit = c + (1 << 16);
        int ssize = 1;
        System.out.println(ssize<<=1);
        System.out.println(bit);
    }
}
