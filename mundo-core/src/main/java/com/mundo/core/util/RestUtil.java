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
    public static final RestTemplate DEFAULT_REST_TEMPLATE = SpringUtil.getBean(Beans.DEFAULT_REST_TEMPLATE);

    public static <T> Optional<T> getForObject(String url, Class<T> responseType, Object... uriVariables) {
        if (DEFAULT_REST_TEMPLATE != null && RegexUtil.isUrl(url)) {
            T t = DEFAULT_REST_TEMPLATE.getForObject(url, responseType, uriVariables);
            return Optional.of(t);
        }
        return Optional.empty();
    }

    public static <T> Optional<ResponseEntity<T>> getForEntity(String url, Class<T> responseType, Object... uriVariables) {
        if (DEFAULT_REST_TEMPLATE != null && RegexUtil.isUrl(url)) {
            ResponseEntity<T> entity = DEFAULT_REST_TEMPLATE.getForEntity(url, responseType, uriVariables);
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
