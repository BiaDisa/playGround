package cacheAnnoDemo.item.demo.vo;

import cacheAnnoDemo.item.CacheArgs;
import cacheAnnoDemo.item.CachePrefix;

import java.util.List;

public class Prototype {

    @CachePrefix
    private Integer id;

    @CacheArgs
    private String like;

    @CacheArgs
    private List<Prototype> types;
}
