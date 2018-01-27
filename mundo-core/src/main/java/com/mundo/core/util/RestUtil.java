package com.mundo.core.util;

import com.mundo.core.constant.Beans;
import com.mundo.core.constant.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Function;

/**
 * RestUtil
 *
 * @author maomao
 * @since 2017/3/5
 */
public class RestUtil {
    protected static final RestTemplate defaultTemplate = SpringUtil.getBean(Beans.REST_DEFAULT_TEMPLATE);

    public static RestTemplate getDefaultRestTemplate() {
        return defaultTemplate;
    }

    public static <T> Optional<T> getForObject(String url, Class<T> responseType, Object... uriVariables) {
        if (defaultTemplate != null && RegexUtil.isUrl(url)) {
            T t = defaultTemplate.getForObject(url, responseType, uriVariables);
            return Optional.of(t);
        }
        return Optional.empty();
    }

    public static <T> Optional<ResponseEntity<T>> getForEntity(String url, Class<T> responseType, Object... uriVariables) {
        if (defaultTemplate != null && RegexUtil.isUrl(url)) {
            ResponseEntity<T> entity = defaultTemplate.getForEntity(url, responseType, uriVariables);
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    public static <T> Optional<T> getForBean(String url, Function<String, T> fun) {
        String str = getForObject(url, String.class).orElse(Strings.EMPTY);
        T t = fun.apply(str);
        return Optional.ofNullable(t);
    }
}
