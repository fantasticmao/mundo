package com.mundo.data.support;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mundo.data.SpringTest;
import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * MemcacheLoadingCacheTest
 *
 * @author maodh
 * @since 2018/7/24
 */
public class MemcacheLoadingCacheTest extends SpringTest {
    @Resource(name = "memcachedClient")
    private MemcachedClient memcachedClient;
    private LoadingCache<Integer, String> cache;

    private static final int key = 1;
    private static final String TEST1 = "test1";
    private static final String TEST2 = "test2";


    @PostConstruct
    public void init() {
        this.cache = MemcacheCacheBuilder.<Integer, String>newBuilder(memcachedClient)
                .expireTime(1, TimeUnit.MINUTES)
                .keyConvert(String::valueOf)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(@Nonnull Integer key) {
                        return TEST1;
                    }
                });
    }

    @Test
    public void get() throws ExecutionException {
        cache.invalidate(key);
        Assert.assertEquals(TEST1, cache.get(key));
    }

    @Test
    public void getCallable() throws ExecutionException {
        cache.invalidate(key);
        Assert.assertNull(cache.getIfPresent(key));

        Assert.assertEquals(TEST2, cache.get(key, () -> TEST2));
    }

    @Test
    public void getIfPresent() {
        cache.invalidate(key);
        Assert.assertNull(cache.getIfPresent(key));

        cache.put(key, TEST2);
        Assert.assertEquals(TEST2, cache.getIfPresent(key));
    }

    @Test
    public void put() {
        cache.invalidate(key);
        Assert.assertNull(cache.getIfPresent(key));

        cache.put(key, TEST2);
        Assert.assertEquals(TEST2, cache.getIfPresent(key));
    }

    @Test
    public void invalidate() {
        cache.invalidate(key);
        Assert.assertNull(cache.getIfPresent(key));
    }

    @Test
    public void refresh() {
        cache.invalidate(key);
        cache.put(key, TEST2);
        Assert.assertEquals(TEST2, cache.getIfPresent(key));
        cache.refresh(key);
        Assert.assertEquals(TEST1, cache.getIfPresent(key));
    }
}