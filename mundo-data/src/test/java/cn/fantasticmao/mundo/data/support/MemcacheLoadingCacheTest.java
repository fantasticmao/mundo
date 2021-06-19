package cn.fantasticmao.mundo.data.support;

import cn.fantasticmao.mundo.data.SpringTest;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * MemcacheLoadingCacheTest
 *
 * @author maodh
 * @version 1.0
 * @since 2018/7/24
 */
@Disabled
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
        Assertions.assertEquals(TEST1, cache.get(key));
    }

    @Test
    public void getCallable() throws ExecutionException {
        cache.invalidate(key);
        Assertions.assertNull(cache.getIfPresent(key));

        Assertions.assertEquals(TEST2, cache.get(key, () -> TEST2));
    }

    @Test
    public void getIfPresent() {
        cache.invalidate(key);
        Assertions.assertNull(cache.getIfPresent(key));

        cache.put(key, TEST2);
        Assertions.assertEquals(TEST2, cache.getIfPresent(key));
    }

    @Test
    public void put() {
        cache.invalidate(key);
        Assertions.assertNull(cache.getIfPresent(key));

        cache.put(key, TEST2);
        Assertions.assertEquals(TEST2, cache.getIfPresent(key));
    }

    @Test
    public void invalidate() {
        cache.invalidate(key);
        Assertions.assertNull(cache.getIfPresent(key));
    }

    @Test
    public void refresh() {
        cache.invalidate(key);
        cache.put(key, TEST2);
        Assertions.assertEquals(TEST2, cache.getIfPresent(key));
        cache.refresh(key);
        Assertions.assertEquals(TEST1, cache.getIfPresent(key));
    }
}