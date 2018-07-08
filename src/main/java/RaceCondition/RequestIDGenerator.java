package RaceCondition;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CharlesYang on 2018/7/4/004.
 */
public class RequestIDGenerator{

    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = -1;

    private RequestIDGenerator(){

    }

    public short newSequence(){
        if (sequence >= SEQ_UPPER_LIMIT){
            sequence = 0;

        }else {
            sequence ++;
        }
        return sequence;
    }

    public String nextID(){
        SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd:HH:mm:ss");
        String timestamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");

        short sequenceNo = newSequence();

        return "0049 " + timestamp + " " + df.format(sequenceNo);

    }

    public static RequestIDGenerator getInstance(){
        return INSTANCE;
    }
}
