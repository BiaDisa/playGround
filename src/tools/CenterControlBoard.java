package tools;

import tools.pool.GlobalThreadPool;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class CenterControlBoard {

    public static void main(String[] args){
        poolTest();
        System.out.println("10-249u710-51");
        Future f = GlobalThreadPool.submit(()-> {

        });
    }

    public static void poolTest(){
        AtomicLong readyCnt = new AtomicLong(1l);
        AtomicLong finishCnt = new AtomicLong(1l);
        while(readyCnt.get() < 10){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GlobalThreadPool.execute(() -> {
                try {

                    System.out.println("thread "+readyCnt.getAndIncrement()+"into work");
                    Thread.sleep(300);
                    System.out.println("thread "+finishCnt.getAndIncrement()+"shutdown");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                }
            });
        }
    }
}
