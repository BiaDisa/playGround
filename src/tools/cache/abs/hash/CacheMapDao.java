package tools.cache.abs.hash;

import tools.cache.abs.RedisCacheDao;

public interface CacheMapDao<T> extends RedisCacheDao<T> {


    //HashMap中区分每个容器的key
    String hashKey(T config);

    //容器中区分每个元素的key
    String diffKey(T config);



}
