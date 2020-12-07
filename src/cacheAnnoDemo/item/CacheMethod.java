package cacheAnnoDemo.item;

public enum CacheMethod {

    SIMPLE_CONCAT("简单拼接",0),
    SIMPLE_HASH("简易hash",1);


    String desc;
    int code;

    CacheMethod(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }
}
