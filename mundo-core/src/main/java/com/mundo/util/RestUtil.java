package com.mundo.util;

import com.mundo.constant.Beans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/**
 * RestUtil
 *
 * @author maomao
 * @since 2017/3/5
 */
public class RestUtil {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected static final RestTemplate defaultTemplate = SpringUtil.getBean(Beans.REST_DEFAULT_TEMPLATE);

    public static RestTemplate getDefaultRestTemplate() {
        return defaultTemplate;
    }

    public static <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
        return (defaultTemplate != null && StringUtil.isNotEmpty(url))
                ? defaultTemplate.getForObject(url, responseType, uriVariables) : null;
    }

    public static <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) {
        return (defaultTemplate != null && StringUtil.isNotEmpty(url))
                ? defaultTemplate.getForEntity(url, responseType, uriVariables) : null;
    }

    public static <T> T getForBean(String url, Function<String, T> fun) {
        String str = getForObject(url, String.class);
        return StringUtil.isNotEmpty(str) ? fun.apply(str) : null;
    }
}
