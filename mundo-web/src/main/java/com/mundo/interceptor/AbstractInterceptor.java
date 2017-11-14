package com.mundo.interceptor;

import com.mundo.support.JsonApi;
import com.mundo.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AbstractInterceptor
 *
 * @author maodh
 * @since 2017/8/4
 */
abstract class AbstractInterceptor extends HandlerInterceptorAdapter {

    void sendError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (AppUtil.isApp(request)) {
            sendJsonError(response);
        } else {
            sendWebError(response);
        }
    }

    private void sendJsonError(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonApi.error(HttpStatus.BAD_REQUEST).toJson());
        response.getWriter().flush();
        response.setStatus(HttpStatus.OK.value());
    }

    private void sendWebError(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
