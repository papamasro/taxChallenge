package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;


    @Configuration
    public class CacheConfiguration {

        @Bean
        public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
            RedisTemplate<?, ?> template = new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);
            return template;
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
