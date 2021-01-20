package cacheAnnoDemo.aspect;

import cacheAnnoDemo.item.CacheDomain;
import cacheAnnoDemo.item.CacheType;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HeapCacheProcessor extends CacheProcessor {

    private ConcurrentHashMap<String, CacheDomain> heapCache = new ConcurrentHashMap<>();

    @Override
    boolean cacheProcess(String key, Object[] args,Class tar) {
        for (int i = 0; i < args.length; i++) {
            if(args[i].getClass().isArray()){
                for (int i1 = ((Object[]) args[i]).length - 1; i1 >= 0; i1--) {
                    processRecord(key,args,tar);
                }
            }
        }
        return true;
    }

    //todo keyPrefix在哪里？
    private void processRecord(String key,Object arg,Class tar){
        if(arg.getClass().isInstance(tar)){
            CacheDomain cacheDomain = heapCache.get(key);
            if(null == cacheDomain){
                cacheDomain = new CacheDomain();
                cacheDomain.put(key,arg);
            }
        }
    }
}
