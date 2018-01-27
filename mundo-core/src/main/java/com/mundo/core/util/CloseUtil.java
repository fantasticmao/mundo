package com.mundo.core.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * CloseUtil
 *
 * @author maomao
 * @since 2017/3/5
 */
public final class CloseUtil {

    public static void close(Closeable... resources) {
        try {
            for (Closeable res : resources) {
                if (res != null) {
                    res.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
