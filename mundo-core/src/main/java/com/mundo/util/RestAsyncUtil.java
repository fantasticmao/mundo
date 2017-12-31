package com.mundo.util;

import com.mundo.constant.Beans;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * RestAsyncUtil
 *
 * @author maodh
 * @since 2017/12/31
 */
public class RestAsyncUtil extends RestUtil {
    protected static final AsyncRestTemplate ansycTemplate = SpringUtil.getBean(Beans.REST_ANSYC_TEMPLATE);

    public static AsyncRestTemplate getAnsycRestTemplate() {
        return ansycTemplate;
    }

    public static <T> ListenableFuture<ResponseEntity<T>> ansycGetForEntity(String url, Class<T> responseType, Object... uriVariables) {
        return (ansycTemplate != null && StringUtil.isNotEmpty(url))
                ? ansycTemplate.getForEntity(url, responseType, uriVariables) : null;
    }
}
