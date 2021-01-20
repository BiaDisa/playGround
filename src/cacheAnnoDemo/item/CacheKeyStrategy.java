package cacheAnnoDemo.item;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class CacheKeyStrategy {

    private static final String delimiter = "-";

    //将String类型的进行hash
    public static String hash(Object[] args){
        return String.valueOf(concatArgs(args).hashCode());
    }

    private static String concatArgs(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if(args[i].getClass().isArray()){
                sb.append(concatArgs((Object[]) args[i]));
                continue;
            }
                sb.append(args[i]).append(i == args.length-1?"":delimiter);
        }
        return sb.toString();
    }

    public static String concat(Object[] args){
        return concatArgs(args);
    }
}
