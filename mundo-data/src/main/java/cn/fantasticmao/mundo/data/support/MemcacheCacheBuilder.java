package cn.fantasticmao.mundo.data.support;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.rubyeye.xmemcached.MemcachedClient;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * MemcacheCacheBuilder
 *
 * <pre>
 * LoadingCache&lt;Integer, String&gt; cache = MemcacheCacheBuilder.&lt;Integer, String&gt;newBuilder(memcachedClient)
 *     .expireTime(1, TimeUnit.HOURS)
 *     .keyConvert(String::valueOf)
 *     .build(new CacheLoader&lt;Integer, String&gt;() {
 *         public String load(@Nonnull Integer key) throws Exception {
 *             // do something ...
 *             return null;
 *         }
 *      });
 * </pre>
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/7/22
 */
public class MemcacheCacheBuilder<K, V extends Serializable> {
    private MemcachedClient memcachedClient;
    private Function<Object, String> keyConvert;
    private int expireAfterWriteSeconds;

    private MemcacheCacheBuilder(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
        this.keyConvert = String::valueOf;
        this.expireAfterWriteSeconds = Math.toIntExact(TimeUnit.DAYS.toSeconds(30));
    }

    public static <K, V extends Serializable> MemcacheCacheBuilder<K, V> newBuilder(MemcachedClient memcachedClient) {
        return new MemcacheCacheBuilder<>(memcachedClient);
    }

    public MemcacheCacheBuilder<K, V> keyConvert(Function<Object, String> keyConvert) {
        this.keyConvert = keyConvert;
        return this;
    }

    public MemcacheCacheBuilder<K, V> expireTime(long duration, TimeUnit unit) {
        long time = unit.toSeconds(duration);
        this.expireAfterWriteSeconds = Math.toIntExact(time);
        return this;
    }

    public LoadingCache<K, V> build(CacheLoader<K, V> cacheLoader) {
        return new MemcacheLoadingCache<>(memcachedClient, cacheLoader, keyConvert, expireAfterWriteSeconds);
    }
}
