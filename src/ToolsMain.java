import sun.nio.ch.DirectBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.*;

public class ToolsMain {

    private static ByteBuffer bb = ByteBuffer.allocateDirect(102400);


    public static void main(String[] args) throws InterruptedException {
        List<ByteBuffer> arr = new ArrayList<>();
        for(int i = 0;i<100;i++){
            arr.add(ByteBuffer.allocateDirect(102400));
        }
        System.gc();
        Thread.sleep(1000L*60*2);
        if(bb instanceof DirectBuffer){
            System.out.println("yes");
        }
        for (ByteBuffer buffer : arr) {
            if(null != buffer)
                System.out.println("yes");
        }

    }
        }


