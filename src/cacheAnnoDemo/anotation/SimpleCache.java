package cacheAnnoDemo.anotation;
import cacheAnnoDemo.item.CacheMethod;

import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Inherited
public @interface SimpleCache {
    /**
     * keys source
     */
        String[] keys() default {""};

    /**
     * key generator method
     */
    CacheMethod cacheMethod() default CacheMethod.SIMPLE_CONCAT;

    /**
     * cache time
     */
    int duration() default 5;

    /**
     * timeUnit
     */
    ChronoUnit timeUnit() default ChronoUnit.MINUTES;

}
