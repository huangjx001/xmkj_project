package com.zz.xmkj.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import lombok.extern.slf4j.Slf4j;


@Configuration
@EnableCaching
@Slf4j
public class RedisConfig
{

    @Autowired
    private PropertiesConfig properties;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory)
    {
        // 默认1
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(
            Duration.ofMinutes(10)).serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    keySerializer())).serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                            valueSerializer())).disableCachingNullValues();
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(
            connectionFactory);
        Set<String> cacheNames = new HashSet<>();
        ConcurrentHashMap<String, RedisCacheConfiguration> cacheConfig = new ConcurrentHashMap<>();
        for (Map.Entry<String, Duration> entry : properties.getInitCaches().entrySet())
        {
            cacheNames.add(entry.getKey());
            cacheConfig.put(entry.getKey(), config.entryTtl(entry.getValue()));
        }
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(
            redisCacheWriter).cacheDefaults(config).initialCacheNames(
                cacheNames).withInitialCacheConfigurations(cacheConfig).transactionAware().build();
        log.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        log.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer()
    {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer()
    {
        return new GenericJackson2JsonRedisSerializer();
    }
}
