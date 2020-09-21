package tools.cache.abs;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface RedisCacheDao<T> {

    RedisTemplate getRedisTemplate();

    T backUp(String key);

    List<T> backUp(T param);

}
