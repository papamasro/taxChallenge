package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;


@Configuration
public class CacheConfiguration {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(redisHost, redisPort)
        );
    }


        /* CUSTOM IMPLEMENTATION FOR REDIS SCALABLE (NOT WORKING)

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

        @Bean
        public RedisTemplate<String, Object> redisTemplate() {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory());
            return template;
        }



     */

}


