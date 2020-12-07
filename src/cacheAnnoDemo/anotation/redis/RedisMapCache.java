package cacheAnnoDemo.anotation.redis;

import cacheAnnoDemo.anotation.SimpleCache;


import java.lang.annotation.Inherited;

@Inherited
@SimpleCache
public @interface RedisMapCache {

    SimpleCache simpleCache();
}
