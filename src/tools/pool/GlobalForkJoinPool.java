package tools.pool;

import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;


@Component
public class GlobalForkJoinPool {

    private static final  ForkJoinPool forkJoinPool;

    static{
        int coreNum = Runtime.getRuntime().availableProcessors() + 1;
        forkJoinPool = new ForkJoinPool(coreNum);
    }


    public  static Future<?> submit(Runnable task){
        return forkJoinPool.submit(task);
    }

    public static boolean isTerminated(){
        return forkJoinPool.isTerminated();
    }

    public static void shutDown(){
        forkJoinPool.shutdown();
    }

}
