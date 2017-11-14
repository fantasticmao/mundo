package com.mundo.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * AppUtil
 *
 * @author maodh
 * @since 2017/7/10
 */
public class AppUtil {
    private static final Set<String> APP_SET = new HashSet<>();

    static {
        APP_SET.add("maomao");
    }

    public static boolean isApp(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return StringUtils.isNotEmpty(userAgent) && APP_SET.contains(userAgent);
    }
}
