package cacheAnnoDemo.aspect;

import cacheAnnoDemo.anotation.SimpleCache;
import cacheAnnoDemo.item.CacheMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

        //todo 注解信息写入cache中
        SimpleCache annotation = method.getAnnotation(SimpleCache.class);
        Class tar = annotation.cacheTargets();
        CacheMethodEnum cacheMethodEnum = annotation.cacheMethod();
        final String cacheKey = getCacheKey(cacheMethodEnum,args,annotation.prefixKey());
        cacheProcess(cacheKey,args,tar);
    }

    private String getCacheKey(CacheMethodEnum cacheMethodEnum,Object[] args,String prefixKey){
        String key = cacheMethodEnum.generateKey(args,prefixKey);
        if(StringUtils.isEmpty(key)){
            return prefixKey + "cacheKey-nil";
        }
        return prefixKey + key;
    }

    abstract boolean cacheProcess(String key,Object[] args,Class tars);


}
