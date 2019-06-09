package com.mundo.web.interceptor;

import com.mundo.core.support.Constant;
import com.mundo.core.util.CollectionUtil;
import com.mundo.web.annotation.LogRequestInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Queue;

/**
 * LogRequestInfoInterceptor
 *
 * @author maomao
 * @since 2019-06-09
 */
public class LogRequestInfoInterceptor extends AnnotationInterceptor<LogRequestInfo> {
    private static final Logger LOGGER = LoggerFactory.getLogger("logRequestInfo");
    @Resource
    private ConfigurableEnvironment environment;
    @Resource
    private LogRequestInfoInterceptor.Properties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Queue<LogRequestInfo> annotationQueue = super.getMethodAnnotationQueue(handler, LogRequestInfo.class);
        boolean needLogRequestInfo = this.processAnnotationQueue(annotationQueue);
        needLogRequestInfo = needLogRequestInfo || this.needLogAllRequestInfo();
        if (needLogRequestInfo) {
            this.logRequestInfo(request);
        }
        return true;
    }

    public LogRequestInfoInterceptor.Properties getProperties() {
        return this.properties;
    }

    @Override
    boolean processAnnotationQueue(Queue<LogRequestInfo> queue) {
        return CollectionUtil.isNotEmpty(queue);
    }

    private boolean needLogAllRequestInfo() {
        if (!properties.getLogAllRequestInfo()) {
            return false;
        } else {
            String[] profiles = properties.getLogAllRequestInfoWhenProfile();
            if (profiles == null) {
                return true;
            } else {
                return environment.acceptsProfiles(Profiles.of(profiles));
            }
        }
    }

    private void logRequestInfo(HttpServletRequest request) {
        final String method = request.getMethod();
        final String uri = request.getRequestURI();
        final String queryString = request.getQueryString();
        final String version = request.getProtocol();
        final Enumeration<String> headerNames = request.getHeaderNames();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method).append(Constant.Strings.SPACE).append(uri);
        if (queryString != null) {
            stringBuilder.append("?").append(queryString);
        }
        stringBuilder.append(Constant.Strings.SPACE).append(version).append(System.lineSeparator());
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                stringBuilder.append(name).append(": ").append(value).append(System.lineSeparator());
            }
        }
        LOGGER.trace(stringBuilder.toString());
    }

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties(prefix = "mundo.web", ignoreInvalidFields = true)
    public static class Properties {
        /**
         * 是否开启打印所有请求的日志
         */
        private Boolean logAllRequestInfo;
        /**
         * 匹配当前环境的 {@link org.springframework.context.annotation.Profile} 数值，再判断是否开启打印所有请求的日志
         */
        private String[] logAllRequestInfoWhenProfile;

        public Properties() {
            this.logAllRequestInfo = false;
            this.logAllRequestInfoWhenProfile = null;
        }
    }
}
