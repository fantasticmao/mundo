package com.mundo.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * SpringUtil
 *
 * @author maodh
 * @since 2017/12/31
 */
public final class SpringUtil implements ApplicationContextAware, EnvironmentAware, ResourceLoaderAware {
    private static ApplicationContext applicationContext;
    private static Environment environment;
    private static ResourceLoader resourceLoader;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        SpringUtil.resourceLoader = resourceLoader;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(clazz) : null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return applicationContext != null ? (T) applicationContext.getBean(name) : null;
    }

    public static String getProperty(String key) {
        return environment != null ? environment.getProperty(key) : null;
    }
}
