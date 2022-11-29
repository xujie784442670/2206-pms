package cn.jasonone.service.impl;

import cn.jasonone.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author xujie
 * @version 1.0.0
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void set(String key, Object value, long ex) {
        redisTemplate.opsForValue().set(key,value,ex, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String key, long ex) {
        redisTemplate.expire(key,ex, TimeUnit.SECONDS);
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long inc(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long delete(String... keys) {
        return redisTemplate.delete(Arrays.asList(keys));
    }
}
