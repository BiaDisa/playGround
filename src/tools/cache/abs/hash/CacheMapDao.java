package tools.cache.abs.hash;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public interface CacheMapDao<T> {


    //HashMap中区分每个容器的key
    String hashKey(T config);

    //容器中区分每个元素的key
    String diffKey(T config);

    //双写备份
    List<T> getFromDB(T config);


}
