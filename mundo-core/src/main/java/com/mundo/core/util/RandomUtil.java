package com.mundo.core.util;

import org.apache.commons.lang3.RandomUtils;

/**
 * RandomUtil
 *
 * @author maomao
 * @since 2017/4/27
 */
public final class RandomUtil extends RandomUtils {
    private static final String[] TABLE_NUMBER = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    private static final String[] TABLE_STRING = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    public static String nextNumber() {
        int index = nextInt(0, TABLE_NUMBER.length);
        return TABLE_NUMBER[index];
    }

    public static String nextString() {
        int index = nextInt(0, TABLE_STRING.length);
        return TABLE_STRING[index];
    }

}
