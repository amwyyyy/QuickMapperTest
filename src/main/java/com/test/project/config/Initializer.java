package com.test.project.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * 无xml配置初始化入口类
 *
 * @author denis.huang
 * @since 2017/3/9
 */
public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        initializeSpringMVC(container);

        initializeShiro(container);
    }

    /**
     * 初始化springmvc servlet
     *
     * @param container
     */
    private void initializeSpringMVC(ServletContext container) {
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setAsyncSupported(true);
    }

    /**
     * 初始化shiro filter
     *
     * @param container
     */
    private void initializeShiro(ServletContext container) {
        FilterRegistration.Dynamic filter = container.addFilter("shiroFilter", DelegatingFilterProxy.class);
        filter.setInitParameter("targetFilterLifecycle", "true");
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        filter.setAsyncSupported(true);
    }
}
