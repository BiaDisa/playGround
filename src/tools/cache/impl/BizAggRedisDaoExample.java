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
    public QueryWrapper<T> buildDBQueryWrapper(T queryParams) {
        return null;
    }

    @Override
    public QueryWrapper<T> buildDBQueryWrapper(String... queryParams) {
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
    public List<T> getFromDB(T config) {
        return null;
    }


}
