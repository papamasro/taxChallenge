package com.example.demo.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.cache.CacheManager;
import javax.cache.Caching;
import java.time.Duration;




    @Configuration
    public class CacheConfiguration {

        @Bean
        public Config config() {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://localhost:6379");
            return config;
        }

        @Bean(name="SpringCM")
        public CacheManager cacheManager(Config config) {
            CacheManager manager = Caching.getCachingProvider().getCacheManager();
            manager.createCache("cache", RedissonConfiguration.fromConfig(config));
            manager.createCache("userList", RedissonConfiguration.fromConfig(config));
            return manager;
        }


        @Bean
        ProxyManager<String> proxyManager(CacheManager cacheManager) {
            return new JCacheProxyManager<>(cacheManager.getCache("cache"));
        }
    }

    /*

    @Configuration
@EnableCaching
public class CacheConfiguration {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.ttl.default}")
    private int redisTTLDefault;

    @Value("${redis.ttl.taxes}")
    private int redisTTLTaxes;


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(redisTTLDefault)).disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("taxes", myDefaultCacheConfig(Duration.ofMinutes(redisTTLTaxes)))
                .build();
    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }




}
  */
