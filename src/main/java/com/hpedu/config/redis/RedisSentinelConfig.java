package com.hpedu.config.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singletonMap;

//import redis.clients.jedis.JedisPoolConfig;
/**
 * 哨兵模式的配置.
 * @author Administrator
 *
 */
//@Configuration
//@PropertySource("classpath:/redis.properties")
public class RedisSentinelConfig {

/**
     * 配置redis的哨兵
    * @return RedisSentinelConfiguration
    * @autor lpl
    * @date 2017年12月21日
    * @throws
     */
    @Bean
    public RedisSentinelConfiguration sentinelConfiguration(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        RedisNode redisNode = new RedisNode("hostName", 1);//hostName, port
        redisNode.setName("mymaster");
        redisSentinelConfiguration.master(redisNode);
        //配置redis的哨兵sentinel
        RedisNode senRedisNode = new RedisNode("senHost1",1);//senHost1,senPort1
        Set<RedisNode> redisNodeSet = new HashSet<>();
        redisNodeSet.add(senRedisNode);
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        return redisSentinelConfiguration;
    }


    /**
     * 缓存管理配置
     * @param connectionFactory
     * @return
     */
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        /* 默认配置， 默认超时时间为30s */
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30L))
                .disableCachingNullValues();

        /* 配置test的超时时间为120s*/
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(singletonMap("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(120L))
                .disableCachingNullValues()))
                .transactionAware()
                .build();

        return cacheManager;
    }
    
}