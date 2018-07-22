package com.mundo.data.support;

import com.google.common.cache.AbstractLoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * MemcacheLoadingCache
 *
 * @author maodh
 * @since 2018/7/22
 */
public class MemcacheLoadingCache<K, V> extends AbstractLoadingCache<K, V> {
    private final MemcachedClient memcachedClient;
    private final CacheLoader<K, V> cacheLoader;
    private final Function<Object, String> keyGenerator;
    private final int expireAfterWriteSeconds;

    public MemcacheLoadingCache(MemcachedClient memcachedClient, CacheLoader<K, V> cacheLoader, Function<Object, String> keyGenerator) {
        this(memcachedClient, cacheLoader, keyGenerator, 30, TimeUnit.DAYS);
    }

    public MemcacheLoadingCache(MemcachedClient memcachedClient, CacheLoader<K, V> cacheLoader, Function<Object, String> keyGenerator, long duration, TimeUnit unit) {
        this.memcachedClient = memcachedClient;
        this.cacheLoader = cacheLoader;
        this.keyGenerator = keyGenerator;
        this.expireAfterWriteSeconds = Math.toIntExact(unit.toSeconds(duration));
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
        try {
            final String k = keyGenerator.apply(key);
            return memcachedClient.get(k);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void put(K key, V value) {
        try {
            final String k = keyGenerator.apply(key);
            memcachedClient.set(k, expireAfterWriteSeconds, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invalidate(Object key) {
        try {
            final String k = keyGenerator.apply(key);
            memcachedClient.delete(k);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
        }
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
