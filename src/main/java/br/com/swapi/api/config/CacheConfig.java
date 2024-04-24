package br.com.swapi.api.config;

import br.com.swapi.api.config.properties.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class CacheConfig {

    private final CacheProperties cacheProperties;


    public CacheConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandalone = new RedisStandaloneConfiguration(cacheProperties.getHost(), cacheProperties.getPort());
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandalone);
        factory.getPoolConfig().setMinIdle(10);
        factory.getPoolConfig().setMaxIdle(30);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    @Primary
   public CacheManager cacheManager(){
        return RedisCacheManager.builder()
                .fromConnectionFactory(redisConnectionFactory())
                .cacheDefaults(
                        RedisCacheConfiguration
                                .defaultCacheConfig(
                                        Thread
                                                .currentThread()
                                                .getContextClassLoader())
                                .entryTtl(
                                Duration.ofHours(cacheProperties.getTtlCache())
                        )
                ).build();
    }


}
