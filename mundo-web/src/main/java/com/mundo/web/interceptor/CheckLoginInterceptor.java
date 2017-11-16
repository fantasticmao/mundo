package com.mundo.web.interceptor;

import com.mundo.web.annotation.CheckLogin;
import com.mundo.data.constant.Words;
import com.mundo.data.util.CollectionUtil;

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
        boolean isCheck = CollectionUtil.isNotEmpty(annotationQueue) && handleAnnotationQueue(annotationQueue);
        return !isCheck || check(request, response);
    }

    @Override
    boolean handleAnnotationQueue(Queue<CheckLogin> queue) {
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
