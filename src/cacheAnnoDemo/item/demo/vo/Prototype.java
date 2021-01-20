package cacheAnnoDemo.item.demo.vo;

import cacheAnnoDemo.item.CacheArgs;
import cacheAnnoDemo.item.CachePrefix;
import cacheAnnoDemo.item.CacheType;

import java.util.List;

@CacheType
public class Prototype {

    @CachePrefix
    private Integer id;

    @CacheArgs
    private String like;

    @CacheArgs
    private List<Prototype> types;
}
