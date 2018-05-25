package com.mundo.core.support;

import com.mundo.core.util.AppUtil;
import com.mundo.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * DataEventLog
 * 参照「打点日志规范」
 *
 * @author maodh
 * @since 2017/7/9
 */
public class DataEventLog {
    private static final Logger WEB_SERVER_LOG = LoggerFactory.getLogger("webServerLog");
    private static final Logger APP_SERVER_LOG = LoggerFactory.getLogger("appServerLog");

    private Map<String, Object> map;

    private DataEventLog() {
        throw new AssertionError("No com.maomao.support.DataEventLog instances for you!");
    }

    private DataEventLog(HttpServletRequest request) {
        this.map = new HashMap<>();
    }

    public void log(HttpServletRequest request) {
        String json = JsonUtil.toJson(map).orElse("{}");
        if (AppUtil.isApp(request)) {
            APP_SERVER_LOG.info(json);
        } else {
            WEB_SERVER_LOG.info(json);
        }
    }

    private static class Builder {
        /**
         * 设备属性
         */
        private String ac; // app code
        private String av; // app version
        private String mc; // machine code
        private String os; // operating system
        /**
         * 行为属性
         */
        private String api;
        private String ip;
        private String uid;
        private String time;
        private Map<String, Object> params;

        public static Builder create() {
            return new Builder();
        }

        public DataEventLog build() {
            return new DataEventLog();
        }
    }

}
