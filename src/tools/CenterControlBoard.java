package tools;



import tools.sign.JsonUtil;
import tools.sign.SignUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CenterControlBoard {

    static HashMap<String,Object> vars = new HashMap<>();

    /*public static void main(String[] args) throws Exception {
       *//* poolTest();
        System.out.println("10-249u710-51");
        Future f = GlobalThreadPool.submit(()-> {
        });*//*


        *//*Map<String,Map> total = new HashMap();
        Map<String,Object> baseParams = new HashMap();
        baseParams.put("sessionId","1");
        baseParams.put("channel","h5");
        baseParams.put("source","1");
        total.put("baseParams",baseParams);
        Map<String,Object> signParams = new HashMap();
        signParams.put("tenantId","00010002");
        signParams.put("corpId","ww94fcf67fe3851a91");
        signParams.put("userId","desperado");
        signParams.put("extUserId","wmHhXwBwAA1fJQOwc-1agiVahqh3rDUA");
        signParams.put("message","我是上海索牛的保险顾问段莉莎，为您提供保险咨询、家庭保单缺口分析。如有需要，我将竭力为您服务。");
        total.put("signParams",signParams);

        long timestamp = System.currentTimeMillis();

//对map参数进行排序生成参数
        Set<String> keysSet = total.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = total.get(key);
            String valueString = "";
            if (null != value && ((null != value) && value !=" "))) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        //根据参数生成签名
        String TEMP_STR = temp.toString()+"e62f2d023cd428bea303c488880d85da"+timestamp;



// 拿到一个MD5转换器
        MessageDigest md = MessageDigest.getInstance("MD5");
// 创建一个字节数组
        byte[] md5hash = new byte[32];

        md.update(TEMP_STR.getBytes("utf-8"), 0, TEMP_STR.length());
//转换并返回结果
        md5hash = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<md5hash.length;i++) {
            String sval = Integer.toHexString((int) md5hash[i] & 0xFF);
            if(sval.length()== 1)
            {
                sval = "0"+sval;
            }
            sb.append(sval);
        }

        vars.putObject("MD5Signature", sb.toString());
        vars.putObject("timestamp",timestamp);*//*

         *//*能够正常工作的版本*//*
        Long time = System.currentTimeMillis();
        String j = "{\n" +
                "    \"baseParams\":{\n" +
                "        \"sessionId\":\"ff766c8ace34f3aa0256166ca58fd7e6\",\n" +
                "        \"channel\":\"h5\",\n" +
                "        \"source\":\"1\",\n" +
                "        \"isDebug\":false\n" +
                "    },\n" +
                "    \"signParams\":{\n" +
                "       \"tenantId\":\"00010002\",\n" +
                "        \"corpId\":\"ww94fcf67fe3851a91\",\n" +
                "        \"userId\":\"desperado\",\n" +
                "        \"agentId\":\"1000012\"\n" +
                "    },\n" +
                "    \"timeStamp\":1600936030287,\n" +
                "    \"sign\":\"f417718d1eef3dcaae6e0e06e294c760\"\n" +
                "}";

        Map<String,Object> parse = JsonUtil.jsonToBean(j,Map.class);
        String sign = SignUtils.getSignature((HashMap<String, Object>) parse,"f3b4f3301be4260aaafa32352a03dcbd");

        *//*signTest();*//*

    }*/

    public static void consoleTest(){
        Map<String,Object> params = new HashMap();
        params.put("sessionId","ff766c8ace34f3aa0256166ca58fd7e6");
        params.put("channel","h5");
        params.put("source","1");
        params.put("isDebug",false);
        params.put("tenantId","00010002");
        params.put("corpId","ww94fcf67fe3851a91");
        params.put("userId","desperado");
        params.put("agentId","1000012");

        StringBuilder temp = new StringBuilder();
        boolean first = true;
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value && null != value.toString()) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        vars.put("signStr",temp.toString());
    }


    /*public static void poolTest(){
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
    }*/

    /*public static void signTest(){
        Long timeStamp = System.currentTimeMillis();
        String j = "{\n" +
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
                "       \"timeStamp\":"+timeStamp+",\n" +
                "\"sign\":\"\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> param = JsonUtil.jsonToBean(j,Map.class);

        String secretKey = "e62f2d023cd428bea303c488880d85da";





        Map<String, Object> paramTmp = new HashMap<>();
        if(null != param){
            if(null != param.get("baseParams")){
                Map<String,Object> baseParams = (Map<String, Object>) param.get("baseParams");
                if(null == baseParams || baseParams.isEmpty()){
                    throw new RuntimeException("baseParams为空");
                }
                baseParams.forEach((k,v)->
                        {
                            if((v instanceof String
                                    || v instanceof Long
                                    || v instanceof Integer
                                    || v instanceof Boolean
                                    || v instanceof Double) && null != v && org.apache.commons.lang3.StringUtils.isNotBlank(v.toString())){
                                paramTmp.put(k,v);
                            }

                        }
                );
            }
            if(null != param.get("signParams")){
                Map<String,Object> signParams = (Map<String, Object>) param.get("signParams");
                signParams.forEach((k,v)->
                        {
                            if((v instanceof String
                                    || v instanceof Long
                                    || v instanceof Integer
                                    || v instanceof Boolean
                                    || v instanceof Double) && null != v && org.apache.commons.lang3.StringUtils.isNotBlank(v.toString())){
                                paramTmp.put(k,v);
                            }
                        }
                );
            }

        }
        if(null != param.get("timeStamp")){
            timeStamp = Long.parseLong(param.get("timeStamp").toString());
        }else{
            timeStamp = System.currentTimeMillis();
        }

        String sign = param.get("sign").toString().toUpperCase();
        Long start = Long.parseLong(param.get("timeStamp").toString());
        long now = System.currentTimeMillis();
        //校验时间有效性
        if (start == null || now - start > 2 * 60 * 1000L ) {
            throw new RuntimeException("请求参数过时");
        }
        //是否携带签名
        if (StringUtils.isBlank(sign)) {
            log.info("sign签名参数为空");
            throw new RuntimeException("sign签名参数为空");
        }
        //获取除签名外的参数
        param.remove("sign");
        //校验签名

        Map params;
        try {
            String jsonStr = objectMapper.writeValueAsString(param);
            params = objectMapper.readValue(jsonStr, Map.class);

        } catch (Exception e) {
            throw new RuntimeException("生成签名：转换json失败");
        }


        //对map参数进行排序生成参数
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value && org.apache.commons.lang3.StringUtils.isNotBlank(value.toString())) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        System.out.println(timeStamp);
        String buffer = temp.toString()+secretKey+timeStamp;
        String string = null;
        char hexDigist[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(buffer.getBytes());
            byte[] datas = md.digest(); //16个字节的长整数

            char[] str = new char[2*16];
            int k = 0;

            for(int i=0;i<16;i++)
            {
                byte b   = datas[i];
                str[k++] = hexDigist[b>>>4 & 0xf];//高4位
                str[k++] = hexDigist[b & 0xf];//低4位
            }
            string = new String(str);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println("sign:     "+string);

    }*/


}
