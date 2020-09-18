package tools.cache.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import tools.cache.abs.hash.RedisMapDao;

import java.util.List;


public  class BizAggRedisDaoExample<T> extends RedisMapDao<T> {

    @Autowired
    Mapper<T> mapper;

    /**
     * biz here
     */

    @Override
    public String redisPrefix() {
        return null;
    }

    @Override
    public String hashKey(T config) {
        return null;
    }

    @Override
    public String diffKey(T config) {
        return null;
    }

    @Override
    protected T buildQueryParam(String hashKey) {
        return null;
    }

    @Override
    public List<T> getFromDB(T config) {
        return null;
    }

    @Override
    public boolean validateParam(T c){
        return true;
    }


}
