package com.test.project.config;

import com.wen.commons.spring.AbstractWebConfig;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.HibernateValidatorFactory;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorFactoryImpl;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.validation.Configuration;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * springmvc配置
 *
 * @author denis.huang
 * @since 2017/3/9
 */
@ComponentScan(basePackages = "com.test.project.web")
@EnableAsync
public class WebConfig extends AbstractWebConfig
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 不拦截静态资源
        registry.addResourceHandler("/").addResourceLocations("**.html");
        registry.addResourceHandler("/static/").addResourceLocations("/static/**");
    }

    /**
     * spring 参数验证处理器
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();

        LocalValidatorFactoryBean factoryBean = getApplicationContext().getBean(LocalValidatorFactoryBean.class);
        processor.setValidator(factoryBean.getValidator());
        return processor;
    }

    /**
     * 初始化spring validatorFactoryBean
     *
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // 使用hibernate的validator实现
        factoryBean.setProviderClass(HibernateValidator.class);
        return factoryBean;
    }
}
