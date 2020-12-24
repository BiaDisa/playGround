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

    CacheMethodEnum(String desc, String methodName, int code) {
        this.desc = desc;
        this.methodName = methodName;
        this.code = code;
        Class clazz = CacheKeyStrategy.class;
        try {
            method = clazz.getDeclaredMethod(methodName,Object[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String generateKey(Object[] args){
        Object res = new Object();
        try {
            res = method.invoke(args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (String) res;
    }



}
