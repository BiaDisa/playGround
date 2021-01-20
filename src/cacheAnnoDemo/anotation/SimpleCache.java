package cacheAnnoDemo.anotation;
import cacheAnnoDemo.item.CacheMethodEnum;

import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Inherited
public @interface SimpleCache {
    /**
     * keys source
     */
    String prefixKey() default "";

    /**
     * key generator method
     */
    CacheMethodEnum cacheMethod() default CacheMethodEnum.SIMPLE_CONCAT;

    /**
     * cache time
     */
    int duration() default 5;

    /**
     * timeUnit
     */
    ChronoUnit timeUnit() default ChronoUnit.MINUTES;

    Class cacheTargets() default Object.class;

}
