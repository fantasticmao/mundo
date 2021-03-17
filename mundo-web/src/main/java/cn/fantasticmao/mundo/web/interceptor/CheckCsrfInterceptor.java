package cn.fantasticmao.mundo.web.interceptor;

import cn.fantasticmao.mundo.web.annotation.CheckCsrf;
import org.apache.commons.collections4.CollectionUtils;
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
            Queue<CheckCsrf> annotationQueue = super.getMethodAnnotationQueue(handler, CheckCsrf.class);
            boolean isCheck = CollectionUtils.isNotEmpty(annotationQueue) && this.processAnnotationQueue(annotationQueue);
            return !isCheck || check(request, response);
        }
    }

    @Override
    boolean processAnnotationQueue(Queue<CheckCsrf> queue) {
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
