package com.mundo.core.util;

import java.util.Random;

/**
 * RandomUtil
 *
 * @author maomao
 * @since 2017/4/27
 */
public class RandomUtil {
    private static final String[] TABLE = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private static Random random = new Random();

    public static String newStringBySize(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int r = random.nextInt(62);
            sb.append(TABLE[r]);
        }
        return sb.toString();
    }
}
