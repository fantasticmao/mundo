package com.mundo.web.interceptor;

import com.mundo.constant.Words;
import com.mundo.support.JsonApi;
import com.mundo.util.CollectionUtil;
import com.mundo.util.StringUtil;
import com.mundo.web.annotation.EnableJsonp;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Queue;

/**
 * EnableJsonpInterceptor
 *
 * @author maodh
 * @since 2018/1/12
 */
public class EnableJsonpInterceptor extends AnnotationInterceptor<EnableJsonp> {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        Queue<EnableJsonp> annotationQueue = getMethodAnnotationQueue(handler, EnableJsonp.class);
        boolean isAvailableAnnotation = CollectionUtil.isNotEmpty(annotationQueue) && processAnnotationQueue(annotationQueue);
        if (isAvailableAnnotation) {
            String callback = request.getParameter(Words.CALLBACK);
            if (StringUtil.isEmpty()) {
                sendJsonError(response, JsonApi.error(HttpStatus.BAD_REQUEST)
                        .message("The 「callback」 argument for jsonp request must not be null or empty!"));
            } else {
                // TODO replace with jsonp result
                response.setContentType("application/javascript;charset=UTF-8");
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    boolean processAnnotationQueue(Queue<EnableJsonp> queue) {
        EnableJsonp enableJsonp;
        while ((enableJsonp = queue.poll()) != null) {
            if (!enableJsonp.value()) {
                return false;
            }
        }
        return true;
    }
}
