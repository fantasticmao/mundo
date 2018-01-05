package com.mundo.util;

import java.util.regex.Pattern;

/**
 * RegexUtil
 *
 * @author maomao
 * @since 2017/3/7
 */
public final class RegexUtil {
    private static final Pattern HOST = Pattern.compile("^[\\w|\\d|\\\\.]+(/[\\w|\\d|\\\\.]+)*$");
    private static final Pattern URL = Pattern.compile("^http(s)?://[\\w|\\d|\\\\.]+(/[\\w|\\d|\\\\.]+)*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEPHONE = Pattern.compile("^\\d{11}$");
    private static final Pattern EMAIL = Pattern.compile("^[\\w|\\d]+@[\\w|\\d]+\\.[\\w|\\d]+$");

    public static boolean isHost(String str) {
        return StringUtil.isNotEmpty(str) && HOST.matcher(str).matches();
    }

    public static boolean isUrl(String str) {
        return StringUtil.isNotEmpty(str) && URL.matcher(str).matches();
    }

    public static boolean isTelephone(String str) {
        return StringUtil.isNotEmpty(str) && TELEPHONE.matcher(str).matches();
    }

    public static boolean isEmail(String str) {
        return StringUtil.isNotEmpty(str) && EMAIL.matcher(str).matches();
    }

    public static boolean isUsername(String str) {
        String regex = "^[\\w|\\d|\\x{4e00}-\\x{9fa5}]{4,16}$";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

    public static boolean isPassword(String str) {
        String regex = "^(.*){8,16}$";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

    public static boolean isHtml(String str) {
        String regex = "<[.^>]*>";
        return StringUtil.isNotEmpty(str) && str.matches(regex);
    }

}
