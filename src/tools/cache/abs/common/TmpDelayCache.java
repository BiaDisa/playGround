package tools.cache.abs.common;


import tools.cache.abs.RedisCacheDao;
import tools.cache.constant.CacheConstant;

public interface TmpDelayCache<T> extends RedisCacheDao<T> {

    default void saveAsTmpCache(String key,T data,int expire){
        getRedisTemplate().opsForValue().set(key,data,expire);
    }

    @SuppressWarnings("throw null")
    default T getFromCache(String key){
        T res = (T)getRedisTemplate().opsForValue().get(key);
        if(null == res){
            res = backUp(key);
            if(null != res){
                saveAsTmpCache(key,res,getExpireTime());
            }
        }
        return res;
    }

    default int getExpireTime(){
        return CacheConstant.defaultExpireTime;
    }


}
