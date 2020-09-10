package tools.cache.abs.hash;

import java.util.HashMap;
import java.util.Map;

public interface HeapMapDao<T> extends CacheMapDao<T> {

    default T saveOrUpdateToCache(T record){
        return getFromSource().put(hashKey(record),record);
    }

    default T getFromCache(String hashKey){
        return getFromSource().get(hashKey);
    }

    //获得本地存储对象
    Map<String,T> getFromSource();
}
