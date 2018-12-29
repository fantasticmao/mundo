package com.mundo.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.Nonnull;

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
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(@Nonnull Environment environment) {
        SpringUtil.environment = environment;
    }

    @Override
    public void setResourceLoader(@Nonnull ResourceLoader resourceLoader) {
        SpringUtil.resourceLoader = resourceLoader;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }

    public static Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }
}
