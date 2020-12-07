package cacheAnnoDemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@Aspect
@Slf4j
public class CacheProcessor {

    @Before("@annotation(cacheAnnoDemo.anotation.heap.HeapCache)")
    public void afterInsert(){}


}
