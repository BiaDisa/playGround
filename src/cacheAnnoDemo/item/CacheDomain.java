package cacheAnnoDemo.item;

import cacheAnnoDemo.anotation.SimpleCache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.cache.decorators.WeakCache;

import java.lang.ref.WeakReference;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@Builder
public class CacheDomain {

    private WeakHashMap<String,Object> item;

    private int duration;

    private ChronoUnit timeUnit;

    private LocalTime time;

    public CacheDomain(WeakHashMap item, SimpleCache simpleCache) {
        this.item = item;
        this.duration = simpleCache.duration();
        this.timeUnit = simpleCache.timeUnit();
        time = LocalTime.now();
    }

    public CacheDomain() {
        this.item = new WeakHashMap<String,Object>();
    }

    public void remove(String key){
        item.remove(key);
    }

    public void put(String key,Object object){
        item.put(key,object);
    }

    public boolean isValid(){
        return LocalTime.now().isBefore(time.plus(duration,timeUnit));
    }
}
