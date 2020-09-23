package tools;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangqichang on 2018/11/28.
 */
@Slf4j
public class SignUtils {

    private static String TIME_STAMP_KEY = "timeStamp";
    private static String SIGN_KEY = "sign";
    //超时时效，超过此时间认为签名过期
    private static Long EXPIRE_TIME = 2 * 60 * 1000L;

    /**
     * 生成签名
     *
     * @param param     向VIP发送的参数
     * @param secretKey 由VIP提供的私钥，该私钥应存储于properties方便更换
     * @return
     */
    public static Map getSignature(Object param, String secretKey,Long timeStamp) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

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
        //根据参数生成签名
        log.info("signStr:"+temp.toString() + secretKey+timeStamp);
        System.out.println(temp.toString() + secretKey);
//        String sign = getMD5(temp.toString() + secretKey+timeStamp).toUpperCase();
        String sign = Md5Util.md5(temp.toString() + secretKey+timeStamp).toUpperCase();
        params.put(SIGN_KEY, sign);
        return params;
    }

    /**
     * 校验签名有效性
     *
     * @param secretKey VIP提供的私钥
     * @return
     */
    public static boolean checkSignature(HashMap<String,Object> param, String secretKey) throws Exception {
        //获取request中的json参数转成map

        Long timeStamp;


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
        if(null != param.get(TIME_STAMP_KEY)){
            timeStamp = Long.parseLong(param.get(TIME_STAMP_KEY).toString());
        }else{
            timeStamp = System.currentTimeMillis();
        }

        String sign = param.get(SIGN_KEY).toString().toUpperCase();
        Long start = Long.parseLong(param.get(TIME_STAMP_KEY).toString());
        long now = System.currentTimeMillis();
        //校验时间有效性
        if (start == null || now - start > EXPIRE_TIME ) {
            /*throw new RuntimeException("请求参数过时");*/
        }
        //是否携带签名
        if (StringUtils.isBlank(sign)) {
            /*log.info("sign签名参数为空");
            throw new RuntimeException("sign签名参数为空");*/
        }
        //获取除签名外的参数
        param.remove(SIGN_KEY);
        //校验签名
        Map paramMap = SignUtils.getSignature(paramTmp, secretKey,timeStamp);
        String signature = (String) paramMap.get("sign");
        System.out.println(signature);
        log.info("VerifySignParam:{},VerifySign : {}", JSONObject.toJSONString(paramTmp),signature);
        log.info("ParamSignParam:{},ParamSign : {}", JSONObject.toJSONString(paramTmp),sign);
        if (!sign.equals(signature)) {
            log.info("sign签名错误");
            throw new RuntimeException("sign签名错误");
        }
        return true;
    }


    public static String getMD5(String str) throws  Exception{
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }
}