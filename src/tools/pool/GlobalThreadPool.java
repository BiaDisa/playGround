package tools.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class GlobalThreadPool {

    private static final ThreadPoolExecutor ioPool;

    //todo - modify params (especially on taskCnt,queueCapacity,maxSize)
    static{
        //get poolSize
        int expectTaskCnt = 20;
        float expectTaskCost = 0.2f;
        int poolSize = (int) (expectTaskCnt * expectTaskCost);

        //set keepAliveTime;
        long maxAliveTime = 60;

        //set timeUnit
        TimeUnit timeUnit = TimeUnit.SECONDS;

        //set workQueue
        float responseTime = 0.3f;
        int queueCapacity = 100/*(int)(((float)poolSize/((float)expectTaskCost)) * (responseTime))*/;
        BlockingQueue<Runnable> bq = new LinkedBlockingQueue<>(queueCapacity);

        //get max pool size
        int maxSize =  100/*(int)((3*expectTaskCnt- queueCapacity)*expectTaskCost)*/;

        //set ThreadFactory
        ThreadFactory tf  = new ThreadFactory() {
            private final AtomicLong readyCnt = new AtomicLong(1l);
            @Override
            public Thread newThread(Runnable r) {
                Thread onLoad = new Thread(r,"thread-generated-"+readyCnt.toString());
                readyCnt.incrementAndGet();
                return onLoad;
            }
        };

        //set handler
        RejectedExecutionHandler handler = (r, executor) -> log.error("fireCaught!-on:"+r.toString()+",reason:full waitingQueue");

        ioPool = new ThreadPoolExecutor(poolSize,maxSize,maxAliveTime,timeUnit,bq,tf,handler);
    }

    public static void execute(Runnable r){
        ioPool.execute(r);
    }

    public static Future submit(Runnable r){
        return ioPool.submit(r);
    }

    public static void shutdown(){
        ioPool.shutdown();
    }
}
