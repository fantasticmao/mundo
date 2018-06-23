package com.mundo.core.util;

import java.util.regex.Pattern;

/**
 * RegexUtil
 *
 * @author maomao
 * @since 2017/3/7
 */
public interface RegexUtil {
    Pattern HOST = Pattern.compile("^[\\w|\\d|\\\\.]+(/[\\w|\\d|\\\\.]+)*$");
    Pattern URL = Pattern.compile("^http(s)?://[\\w|\\d|\\\\.]+(/[\\w|\\d|\\\\.]+)*$", Pattern.CASE_INSENSITIVE);
    Pattern TELEPHONE = Pattern.compile("^\\d{11}$");
    Pattern EMAIL = Pattern.compile("^[\\w|\\d]+@[\\w|\\d]+\\.[\\w|\\d]+$");

    static boolean isHost(String str) {
        return StringUtil.isNotEmpty(str) && HOST.matcher(str).matches();
    }

    static boolean isUrl(String str) {
        return StringUtil.isNotEmpty(str) && URL.matcher(str).matches();
    }

    static boolean isTelephone(String str) {
        return StringUtil.isNotEmpty(str) && TELEPHONE.matcher(str).matches();
    }

    static boolean isEmail(String str) {
        return StringUtil.isNotEmpty(str) && EMAIL.matcher(str).matches();
    }

    static boolean isUsername(String str) {
        String regex = "^[\\w|\\d|\\x{4e00}-\\x{9fa5}]{4,16}$";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

    static boolean isPassword(String str) {
        String regex = "^(.*){8,16}$";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

    static boolean isHtml(String str) {
        String regex = "<[.^>]*>";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

}
