package cn.fantasticmao.mundo.web.interceptor;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.mundo.web.support.JsonApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AbstractInterceptor
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/8/4
 */
abstract class AbstractInterceptor extends HandlerInterceptorAdapter {

    void sendError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String accept = request.getHeader("Accept");
        if (accept.contains(MediaType.APPLICATION_JSON.getType())) {
            sendJsonError(response, JsonApi.error(HttpStatus.BAD_REQUEST));
        } else {
            sendWebError(response, HttpStatus.BAD_REQUEST);
        }
    }

    void sendJsonError(HttpServletResponse response, JsonApi jsonApi) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonUtil.toJson(jsonApi));
        response.getWriter().flush();
        response.setStatus(HttpStatus.OK.value());
    }

    void sendWebError(HttpServletResponse response, HttpStatus httpStatus) throws IOException {
        response.sendError(httpStatus.value());
    }
}
