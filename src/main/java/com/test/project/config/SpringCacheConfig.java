package com.test.project.config;

import com.wen.commons.utils.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * spring cache配置
 *
 * @author denis.huang
 * @since 2017/3/11
 */
@Configuration
@EnableCaching
public class SpringCacheConfig {
    @Value("${redis.hostName}")
    private String hostName;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private Integer timeout;
    @Value("${redis.database}")
    private Integer database;
    private Integer connectionPoolSize = 5;
    private Integer minIdle = 2;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {

        Config config = new Config();
        SingleServerConfig ssc = config.useSingleServer();

        ssc.setAddress(hostName + ":" + port).setTimeout(timeout).setDatabase(database)
                .setConnectionPoolSize(connectionPoolSize).setConnectionMinimumIdleSize(minIdle);
        if (StringUtils.isNotEmpty(password)) {
            ssc.setPassword(password);
        }

        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(5 * 60 * 1000);
        cacheConfig.setMaxIdleTime(60 * 1000);

        Map<String, CacheConfig> config = new HashMap<>(1);
        config.put("queryCache", cacheConfig);

        return new RedissonSpringCacheManager(redissonClient, config, new JsonJacksonCodec());
    }
}
