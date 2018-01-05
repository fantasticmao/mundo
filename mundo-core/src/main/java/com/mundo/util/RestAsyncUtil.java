package com.mundo.util;

import com.mundo.constant.Beans;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Optional;

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

    public static <T> Optional<ListenableFuture<ResponseEntity<T>>> ansycGetForEntity(String url, Class<T> responseType,
                                                                                      Object... uriVariables) {
        if (ansycTemplate != null && RegexUtil.isUrl(url)) {
            ListenableFuture<ResponseEntity<T>> future = ansycTemplate.getForEntity(url, responseType, uriVariables);
            return Optional.of(future);
        }
        return Optional.empty();
    }

    public static <T> void ansycGetForEntity(String url, Class<T> responseType,
                                             ListenableFutureCallback<ResponseEntity<T>> callback, Object... uriVariables) {
        ansycGetForEntity(url, responseType, uriVariables)
                .orElseThrow(NullPointerException::new).addCallback(callback);
    }

    public static <T> void ansycGetForEntity(String url, Class<T> responseType,
                                             SuccessCallback<ResponseEntity<T>> successCallback,
                                             Object... uriVariables) {
        ansycGetForEntity(url, responseType, uriVariables)
                .orElseThrow(NullPointerException::new).addCallback(successCallback, Throwable::printStackTrace);
    }

    public static <T> void ansycGetForEntity(String url, Class<T> responseType,
                                             SuccessCallback<ResponseEntity<T>> successCallback,
                                             FailureCallback failureCallback, Object... uriVariables) {
        ansycGetForEntity(url, responseType, uriVariables)
                .orElseThrow(NullPointerException::new).addCallback(successCallback, failureCallback);
    }
}
