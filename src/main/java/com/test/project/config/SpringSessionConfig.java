package com.test.project.config;

import com.wen.commons.spring.session.CompositionSessionStrategy;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * 启用session缓存，配置session跟踪策略
 *
 * @author denis.huang
 * @since 2017/4/17
 */
@Configuration
@EnableRedissonHttpSession(maxInactiveIntervalInSeconds = 2592000)
public class SpringSessionConfig {
    @Bean
    public HttpSessionStrategy sessionStrategyFactory() {
        return new CompositionSessionStrategy("quick");
    }
}
