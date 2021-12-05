package cn.fantasticmao.mundo.core.support;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;

/**
 * StackPointer
 *
 * @author fantasticmao
 * @version 1.0
 * @since 04/02/2018
 */
public final class StackPointer {

    public static void printStackTrace() {
        printStackTrace(null);
    }

    public static void printStackTrace(String descLine) {
        printStackTrace(descLine, System.out);
    }

    private static void printStackTrace(String descLine, PrintStream printStream) {
        if (StringUtils.isNotEmpty(descLine))
            printStream.println(descLine);
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (StackTraceElement traceElement : trace)
            printStream.println("\tat " + traceElement);
    }
}
