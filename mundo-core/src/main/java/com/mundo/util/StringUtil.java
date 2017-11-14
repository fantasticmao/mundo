package com.mundo.util;

import com.mundo.constant.Strings;

/**
 * StringUtil
 *
 * @author maomao
 * @since 2017/3/4
 */
public final class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    private static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        return isEmpty(trim(str)) ? null : str;
    }

    public static String trimToEmpty(String str) {
        return isEmpty(trim(str)) ? Strings.EMPTY : str;
    }

    /**
     * 连接字符串数组，默认以逗号为分隔符
     *
     * @param strings 字符串数组
     * @return 以逗号连接的字符串
     */
    public static String join(String[] strings) {
        return join(strings, Strings.COMMA);
    }

    /**
     * 连接字符串数组
     *
     * @param separator 分隔符
     * @param strings   字符串数组
     * @return 以分隔符连接的字符串
     */
    public static String join(String[] strings, String separator) {
        return ArrayUtil.join(strings, separator);
    }

    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    /**
     * 从起始位置到字符串末尾，截取字符串。
     * 负数参数表示相对于数组最后一个元素的位置，最后一个元素是-1，最后第二个元素是-2......
     *
     * @param str   字符串
     * @param start 起始位置（包含），第一个值是0，第二值是1......
     * @return 字符串
     */
    public static String slice(String str, int start) {
        return slice(str, start, str.length());
    }

    /**
     * 从起始位置到结束位置，截取字符串。
     * 负数参数表示相对于数组最后一个元素的位置，最后一个元素是-1，最后第二个元素是-2......
     *
     * @param str   字符串
     * @param start 起始位置（包含），第一个值是0，第二值是1......
     * @param end   结束位置（不包含）
     * @return 字符串
     */
    public static String slice(String str, int start, int end) {
        if (isEmpty(str)) {
            return Strings.EMPTY;
        }
        char[] chars = str.toCharArray();
        chars = ArrayUtil.slice(chars, start, end);
        return new String(chars);
    }

}
