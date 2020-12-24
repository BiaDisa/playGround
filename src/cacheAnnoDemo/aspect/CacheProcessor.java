package cacheAnnoDemo.aspect;

import cacheAnnoDemo.anotation.SimpleCache;
import cacheAnnoDemo.item.CacheMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public abstract class CacheProcessor {



    @Around("@annotation(cacheAnnoDemo.anotation.SimpleCache)")
    public void doAround(ProceedingJoinPoint pjp) throws NoSuchMethodException {

        Object[] args = pjp.getArgs();

        Class targetClass = pjp.getTarget().getClass();

        String methodName = pjp.getSignature().getName();

        Class[] parameterClazz = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();

        Method method = targetClass.getMethod(methodName, parameterClazz);

        final String cacheKey = getCacheKey(method,args);

        cacheProcess(cacheKey,args);
    }

    private String getCacheKey(Method method,Object[] args){
        Objects.requireNonNull(method);
        SimpleCache annotation = method.getAnnotation(SimpleCache.class);
        String[] argNames = annotation.keys();
        Class[] tars = annotation.cacheTargets();

        CacheMethodEnum cacheMethodEnum = annotation.cacheMethod();
        String key = cacheMethodEnum.generateKey(args);
        if(argNames.length == 0){
            return "cacheKey-nil";
        }
        return key;
    }

    abstract boolean cacheProcess(String key,Object[] args);


}
