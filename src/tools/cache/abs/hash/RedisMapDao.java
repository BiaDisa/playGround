package tools.cache.abs.hash;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import tools.JsonUtil;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * version:protoType
 * support collection in hash: map
 * 结构：redisKey-Map<hashKey,Map>-Map<diffKey,T>
 * @param <T>
 */
public abstract class RedisMapDao<T> implements CacheMapDao<T> {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BaseMapper<T> mapper;

    //redis-in hash key
    public void insertRecordsToCache(String hashKey, Map<String,T> map) {
        assert !CollectionUtils.isEmpty(map);
        redisTemplate.opsForHash().put(redisPrefix(), hashKey, JsonUtil.beanToJson(map));
    }

    public void insertOrUpdateConfigToCache(T config) {
        assert validateParam(config);
        Map<String,T> configs = getRecordMapFromCache(hashKey(config));
        if (CollectionUtils.isEmpty(configs)) {
            configs = new HashMap<>();
        }
        configs.put(diffKey(config),config);
        redisTemplate.opsForHash().put(redisPrefix(), hashKey(config), JsonUtil.beanToJson(configs));
    }

    public Map<String, T> getRecordMapFromCache(String hashKey) {
        assert StringUtils.isNotEmpty(hashKey);
        String raw = (String) redisTemplate.opsForHash().get(redisPrefix(), hashKey);

        if (null != raw) {
            Map<String, T> stringTMap = (Map<String,T>)JsonUtil.jsonToBean(raw, Map.class);
            return stringTMap;
        } else {
            Map<String,T> mapRes = new HashMap<>();
            List<T> dbRes = getFromDB(buildQueryParam(hashKey));
            if (!CollectionUtils.isEmpty(dbRes)) {
                mapRes = convertListToMap(dbRes);
                insertRecordsToCache(hashKey,mapRes);
            }
            return mapRes;
        }
    }

    protected abstract T buildQueryParam(String hashKey);

    public T getSingleRecordFromCache(T param){
        Map<String,T> res = getRecordMapFromCache(hashKey(param));
        assert !CollectionUtils.isEmpty(res);
        return res.get(diffKey(param));
    }

    @SuppressWarnings("should be locked in redis")
    public void deleteConfigInCache(T config) {
        assert validateParam(config);
        Map<String,T> oriRes = getRecordMapFromCache(hashKey(config));
        if (CollectionUtils.isEmpty(oriRes)) {
            return;
        }
        oriRes.remove(diffKey(config));
        insertRecordsToCache(hashKey(config),oriRes);
    }

    //参数校验
    public boolean validateParam(T c) {
        return null != c;
    }



    public Map<String, T> convertListToMap(List<T> tar) {
        assert !CollectionUtils.isEmpty(tar);
        return tar.stream().collect(Collectors.toMap(this::diffKey, Function.identity()));
    }

    public List<T> convertMapToList(Map<String,T> tar){
        return (List<T>)tar.values();
    }

    public abstract List<T> getFromDB(T queryParams);

    //redis的key
    public abstract String redisPrefix();





}
