package com.mundo.web.interceptor;

import com.mundo.web.annotation.CheckCsrf;
import com.mundo.data.util.CollectionUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Queue;

/**
 * CheckCsrfInterceptor
 *
 * @author maodh
 * @since 2017/8/2
 */
public class CheckCsrfInterceptor extends AnnotationInterceptor<CheckCsrf> {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isGetMethod = Objects.equals(HttpMethod.GET.name(), request.getMethod());
        if (isGetMethod) {
            return true;
        } else {
            // TODO 校验自定义 HTTP Header
            Queue<CheckCsrf> annotationQueue = getMethodAnnotationQueue(handler, CheckCsrf.class);
            boolean isCheck = CollectionUtil.isNotEmpty(annotationQueue) && handleAnnotationQueue(annotationQueue);
            return !isCheck || check(request, response);
        }
    }

    @Override
    boolean handleAnnotationQueue(Queue<CheckCsrf> queue) {
        CheckCsrf checkCsrf;
        while ((checkCsrf = queue.poll()) != null) {
            if (!checkCsrf.value()) {
                return false;
            }
        }
        return true;
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader(HttpHeaders.REFERER);
        String host = request.getHeader(HttpHeaders.HOST);
        return Objects.equals(referer, host);
    }

}
