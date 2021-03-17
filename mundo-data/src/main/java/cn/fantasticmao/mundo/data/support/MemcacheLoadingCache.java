package cn.fantasticmao.mundo.data.support;

import com.google.common.cache.AbstractLoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import net.rubyeye.xmemcached.MemcachedClient;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * MemcacheLoadingCache
 *
 * @author maodh
 * @since 2018/7/22
 */
public class MemcacheLoadingCache<K, V> extends AbstractLoadingCache<K, V> {
    private final MemcacheClientUtil memcacheClientUtil;
    private final CacheLoader<K, V> cacheLoader;
    private final Function<Object, String> keyGenerator;
    private final int expireAfterWriteSeconds;

    public MemcacheLoadingCache(MemcachedClient memcachedClient, CacheLoader<K, V> cacheLoader, Function<Object, String> keyGenerator) {
        this(memcachedClient, cacheLoader, keyGenerator, Math.toIntExact(TimeUnit.DAYS.toSeconds(30)));
    }

    public MemcacheLoadingCache(MemcachedClient memcachedClient, CacheLoader<K, V> cacheLoader, Function<Object, String> keyGenerator, int expireAfterWriteSeconds) {
        this.memcacheClientUtil = new MemcacheClientUtil(memcachedClient);
        this.cacheLoader = cacheLoader;
        this.keyGenerator = keyGenerator;
        this.expireAfterWriteSeconds = expireAfterWriteSeconds;
    }

    @Override
    public synchronized V get(K key) {
        V val = getIfPresent(key);
        if (val == null) {
            try {
                val = cacheLoader.load(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (val != null) {
                this.put(key, val);
            }
        }
        return val;
    }

    @Override
    public synchronized V get(K key, Callable<? extends V> valueLoader) {
        V val = getIfPresent(key);
        if (val == null) {
            try {
                val = valueLoader.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (val != null) {
                this.put(key, val);
            }
        }
        return val;
    }

    @Override
    public V getIfPresent(Object key) {
        final String k = keyGenerator.apply(key);
        return memcacheClientUtil.get(k);
    }

    @Override
    public void put(K key, V value) {
        final String k = keyGenerator.apply(key);
        memcacheClientUtil.set(k, value, expireAfterWriteSeconds);
    }

    @Override
    public void invalidate(Object key) {
        final String k = keyGenerator.apply(key);
        memcacheClientUtil.delete(k);
    }

    @Override
    public synchronized void refresh(K key) {
        V val = null;
        try {
            val = cacheLoader.load(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val != null) {
            this.put(key, val);
        }
    }

    @Override
    public CacheStats stats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
        throw new UnsupportedOperationException();
    }

}
