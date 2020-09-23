package tools;

import tools.pool.GlobalThreadPool;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class CenterControlBoard {

    public static void main(String[] args) throws Exception {
       /* poolTest();
        System.out.println("10-249u710-51");
        Future f = GlobalThreadPool.submit(()-> {

        });*/
       String jsonTest = "{\n" +
               "    \"baseParams\":{\n" +
               "        \"sessionId\":\"1\",\n" +
               "        \"channel\":\"h5\",\n" +
               "        \"source\":\"1\"\n" +
               "    },\n" +
               "    \"signParams\":{\n" +
               "        \"tenantId\":\"00010002\",\n" +
               "        \"corpId\":\"ww94fcf67fe3851a91\",\n" +
               "        \"userId\":\"desperado\",\n" +
               "        \"extUserId\":\"wmHhXwBwAA1fJQOwc-1agiVahqh3rDUA\",\n" +
               "        \"message\":\"我是上海索牛的保险顾问段莉莎，为您提供保险咨询、家庭保单缺口分析。如有需要，我将竭力为您服务。\"\n" +
               "    },\n" +
               "        \"timeStamp\":1600847116635,\n" +
               "    \"sign\":\"e531775d19a866f75ad42f3f9c26fca0\"\n" +
               "}";
        HashMap<String,Object> map = JsonUtil.jsonToBean(jsonTest,HashMap.class);
        System.out.println(SignUtils.checkSignature(map,"e62f2d023cd428bea303c488880d85da"));
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
