package cn.fantasticmao.mundo.web.filter;

import cn.fantasticmao.mundo.core.support.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * HttpFormatRequestLoggingFilter
 * <p>
 * 开启 HttpFormatRequestLoggingFilter 功能，需要在 logback.xml 中配置对应的 logger，示例配置如下：
 * <pre>
 *     <appender name="http_request_info" class="ch.qos.logback.core.rolling.RollingFileAppender">
 *         <file>${log_home}/http-request.log</file>
 *         <encoder>
 *             <pattern>%date{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{5}:%L%n%message%n###%n%n</pattern>
 *             <charset>UTF-8</charset>
 *         </encoder>
 *         <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
 *             <maxHistory>30</maxHistory>
 *             <fileNamePattern>${log_home}/http-request.%d{yyyy-MM-dd}.log</fileNamePattern>
 *         </rollingPolicy>
 *     </appender>
 *
 *     <logger name="HttpFormatRequestLoggingFilter" level="TRACE" additivity="false">
 *         <appender-ref ref="http_request_info"/>
 *     </logger>
 * </pre>
 * </p>
 *
 * @author maomao
 * @since 2019-06-14
 */
public class HttpFormatRequestLoggingFilter extends CommonsRequestLoggingFilter {
    private final Logger logger;

    private static final String SPACE = Constant.Strings.SPACE;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 256;
    private static final String DEFAULT_LOGGER_NAME = "HttpFormatRequestLoggingFilter";

    public HttpFormatRequestLoggingFilter() {
        this(DEFAULT_MAX_PAYLOAD_LENGTH);
    }

    public HttpFormatRequestLoggingFilter(int maxPayloadLength) {
        this(maxPayloadLength, DEFAULT_LOGGER_NAME);
    }

    public HttpFormatRequestLoggingFilter(int maxPayloadLength, String loggerName) {
        this.logger = LoggerFactory.getLogger(loggerName);
        maxPayloadLength = Math.max(maxPayloadLength, 1024);
        super.setMaxPayloadLength(maxPayloadLength);
    }


    @Override
    @Nonnull
    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        final String method = request.getMethod();
        final String uri = request.getRequestURI();
        final String queryString = request.getQueryString();
        final String version = request.getProtocol();
        final Enumeration<String> headerNames = request.getHeaderNames();

        StringBuilder msg = new StringBuilder();
        msg.append(method).append(SPACE).append(uri);
        if (queryString != null) {
            msg.append("?").append(queryString);
        }
        msg.append(SPACE).append(version).append(LINE_SEPARATOR);
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                msg.append(name).append(": ").append(value).append(LINE_SEPARATOR);
            }
        }

        final String payload = super.getMessagePayload(request);
        if (payload != null) {
            msg.append(LINE_SEPARATOR).append(payload).append(LINE_SEPARATOR);
        }
        return msg.toString();
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isTraceEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        // 忽略请求处理之前的日志
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.trace(message);
    }

    @Override
    protected boolean isIncludePayload() {
        return true;
    }
}
