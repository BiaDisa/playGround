package cacheAnnoDemo.item;

import cacheAnnoDemo.anotation.SimpleCache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheDomain {

    private HashMap<String,Object> item;

    private int duration;

    private ChronoUnit timeUnit;

    private LocalTime time;

    public CacheDomain(HashMap item, SimpleCache simpleCache) {
        this.item = item;
        this.duration = simpleCache.duration();
        this.timeUnit = simpleCache.timeUnit();
        time = LocalTime.now();
    }

    public void remove(String key){
        item.remove(key);
    }

    public boolean isValid(){
        return LocalTime.now().isBefore(time.plus(duration,timeUnit));
    }
}
