package com.mundo.core.util;

/**
 * ObjectUtil
 *
 * @author maodh
 * @since 2017/8/2
 */
public class ObjectUtil {

    public static boolean nonNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }
}
