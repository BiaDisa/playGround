package cacheAnnoDemo.anotation.redis;

import cacheAnnoDemo.anotation.SimpleCache;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleCache
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisValCache {

}
