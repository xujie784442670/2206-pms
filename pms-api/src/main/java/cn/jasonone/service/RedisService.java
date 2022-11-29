package cn.jasonone.service;

/**
 * @author xujie
 * @version 1.0.0
 */
public interface RedisService {

    void set(String key,Object value);

    void set(String key,Object value,long ex);

    void expire(String key,long ex);

    long ttl(String key);

    <T> T get(String key);

    Long inc(String key);

    boolean exists(String key);

    Long delete(String ... key);
}
