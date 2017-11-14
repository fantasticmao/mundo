package com.mundo.interceptor;

import com.mundo.annotation.CheckLogin;
import com.mundo.constant.Words;
import com.mundo.util.CollectionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Queue;

/**
 * CheckLoginInterceptor
 *
 * @author maodh
 * @since 2017/8/2
 */
public class CheckLoginInterceptor extends AnnotationInterceptor<CheckLogin> {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Queue<CheckLogin> annotationQueue = getMethodAnnotationQueue(handler, CheckLogin.class);
        // TODO 接入 SSO
        boolean isCheck = CollectionUtil.isNotEmpty(annotationQueue) && processAnnotationQueue(annotationQueue);
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
        Object user = request.getSession().getAttribute(Words.USER);
        if (user == null) {
            sendError(request, response);
            return false;
        }
        return true;
    }

}
