package cacheAnnoDemo.item;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum CacheMethodEnum {


    SIMPLE_CONCAT("简单拼接","concat",0),
    SIMPLE_HASH("简易hash","hash",1);

    private String desc;
    private String methodName;
    private int code;
    private Method method;
    private Class clazz = CacheKeyStrategy.class;

    CacheMethodEnum(String desc, String methodName, int code) {
        this.desc = desc;
        this.methodName = methodName;
        this.code = code;
        try {
            method = clazz.getDeclaredMethod(methodName,Object[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String generateKey(Object[] args,String prefix){
        Object res = new Object();
        try {
            res = method.invoke(clazz.newInstance(),new Object[]{args});
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return prefix + (String) res;
    }



}
