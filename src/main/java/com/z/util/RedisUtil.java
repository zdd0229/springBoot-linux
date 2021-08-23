package com.z.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RedisUtil {

    /**
     * redisTemplate
     */
    @Autowired
    private RedisTemplate<?, ?> redisTemplate;

    /**
     * 设置key和对象的value
     *
     * @param key   key
     * @param value value
     */
    public void set(final String key, Object value) {
        final byte[] vbytes = SerializeUtil.serialize(value);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
                return null;
            }
        });
    }

    /**
     * 设置key和value，并加上时间（秒）
     *
     * @param key   key
     * @param value value
     * @param l     时间
     */
    public void set(final String key, Object value, final long l) {
        final byte[] vbytes = SerializeUtil.serialize(value);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(redisTemplate.getStringSerializer().serialize(key), l, vbytes);
                return null;
            }
        });
    }

    /**
     * 设置key和value，并加上时间（秒）,随机时间
     * 缓存雪崩的解决方案，给过期世家加上一个随机值
     * @param key    key
     * @param value  value
     * @param l      时间
     * @param random 随机时间
     */
    public void set(final String key, Object value, final long l, final int random) {
        Random r = new Random();
        int i = r.nextInt(random * 2);
        if (l + (random - i) <= 0) {
            throw new IllegalArgumentException("随机时间过大");
        }

        final byte[] vbytes = SerializeUtil.serialize(value);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(redisTemplate.getStringSerializer().serialize(key), l + (random - i), vbytes);
                return null;
            }
        });
    }

    /**
     * 根据key来取值
     *
     * @param key         key
     * @param elementType 数据类型
     * @param <T>         泛型
     * @return 获取的对象
     */
    public <T> T get(final String key, Class<T> elementType) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(keybytes)) {
                    byte[] valuebytes = connection.get(keybytes);
                    T value = (T) SerializeUtil.deserialize(valuebytes);
                    return value;
                }
                return null;
            }
        });
    }

    /**
     * 根据key删除该值
     *
     * @param key key
     */
    public void del(final String key) {
        final byte[] keyBytes = redisTemplate.getStringSerializer().serialize(key);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(keyBytes);
                return null;
            }
        });
    }

}
