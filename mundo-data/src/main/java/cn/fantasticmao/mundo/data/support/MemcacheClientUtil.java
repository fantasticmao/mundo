package cn.fantasticmao.mundo.data.support;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

/**
 * MemcacheClientUtil
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/7/22
 */
public class MemcacheClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemcacheClientUtil.class);

    private MemcachedClient memcachedClient;

    public MemcacheClientUtil(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public <T> T get(final String key) {
        try {
            return memcachedClient.get(key);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            LOGGER.error("MemcacheClient get key(" + key + ") error!", e);
            return null;
        }
    }

    public boolean add(final String key, final Object value, final int exp) {
        try {
            return memcachedClient.add(key, exp, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            LOGGER.error("MemcacheClient add key(" + key + ") error!", e);
            return false;
        }
    }

    public boolean replace(final String key, final Object value, final int exp) {
        try {
            return memcachedClient.replace(key, exp, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            LOGGER.error("MemcacheClient replace key(" + key + ") error!", e);
            return false;
        }
    }

    public boolean set(final String key, final Object value, final int exp) {
        try {
            return memcachedClient.set(key, exp, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            LOGGER.error("MemcacheClient set key(" + key + ") error!", e);
            return false;
        }
    }

    public boolean delete(final String key) {
        try {
            return memcachedClient.delete(key);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            LOGGER.error("MemcacheClient delete key(" + key + ") error!", e);
            return false;
        }
    }
}
