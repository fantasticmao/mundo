package com.mundo.core.support;

import com.mundo.core.util.StringUtil;

import java.io.PrintStream;

/**
 * StackPointer
 *
 * @author maodh
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
        if (StringUtil.isNotEmpty(descLine))
            printStream.println(descLine);
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (StackTraceElement traceElement : trace)
            printStream.println("\tat " + traceElement);
    }
}
