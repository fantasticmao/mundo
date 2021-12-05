package cn.fantasticmao.mundo.web.interceptor;

import cn.fantasticmao.mundo.web.annotation.CheckLogin;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Queue;

/**
 * CheckLoginInterceptor
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/8/2
 */
public class CheckLoginInterceptor extends AnnotationInterceptor<CheckLogin> {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Queue<CheckLogin> annotationQueue = super.getMethodAnnotationQueue(handler, CheckLogin.class);
        // TODO 接入 SSO
        boolean isCheck = CollectionUtils.isNotEmpty(annotationQueue) && this.processAnnotationQueue(annotationQueue);
        return !isCheck || check(request, response);
    }

    @Override
    boolean processAnnotationQueue(Queue<CheckLogin> queue) {
        CheckLogin checkLogin;
        while ((checkLogin = queue.poll()) != null) {
            if (!checkLogin.value()) {
                return false;
            }
        }
        return true;
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            sendError(request, response);
            return false;
        }
        return true;
    }

}
