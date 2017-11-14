package com.mundo.util;

import com.mundo.constant.Arrays;
import com.mundo.constant.Strings;

import java.lang.reflect.Array;

/**
 * ArrayUtil
 *
 * @author maomao
 * @since 2017/3/4
 */
public final class ArrayUtil {

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    public static boolean[] toBooleanArray(String str) {
        return toBooleanArray(str, Strings.COMMA);
    }

    public static boolean[] toBooleanArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            boolean[] dst = new boolean[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Boolean.parseBoolean(src[i]);
            }
            return dst;
        }
        return Arrays.BOOLEANS;
    }

    public static byte[] toByteArray(String str) {
        return toByteArray(str, Strings.COMMA);
    }

    public static byte[] toByteArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            byte[] dst = new byte[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Byte.parseByte(src[i]);
            }
            return dst;
        }
        return Arrays.BYTES;
    }

    public static short[] toShortArray(String str) {
        return toShortArray(str, Strings.COMMA);
    }

    public static short[] toShortArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            short[] dst = new short[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Short.parseShort(src[i]);
            }
            return dst;
        }
        return Arrays.SHORTS;
    }

    public static int[] toIntArray(String str) {
        return toIntArray(str, Strings.COMMA);
    }

    public static int[] toIntArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            int[] dst = new int[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Integer.parseInt(src[i]);
            }
            return dst;
        }
        return Arrays.INTS;
    }

    public static long[] toLongArray(String str) {
        return toLongArray(str, Strings.COMMA);
    }

    public static long[] toLongArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            long[] dst = new long[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Long.parseLong(src[i]);
            }
            return dst;
        }
        return Arrays.LONGS;
    }

    public static float[] toFloatArray(String str) {
        return toFloatArray(str, Strings.COMMA);
    }

    public static float[] toFloatArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            float[] dst = new float[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Float.parseFloat(src[i]);
            }
            return dst;
        }
        return Arrays.FLOATS;
    }

    public static double[] toDoubleArray(String str) {
        return toDoubleArray(str, Strings.COMMA);
    }

    public static double[] toDoubleArray(String str, String separator) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(separator)) {
            String[] src = str.split(separator);
            double[] dst = new double[src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = Double.parseDouble(src[i]);
            }
            return dst;
        }
        return Arrays.DOUBLES;
    }

    public static <T> String toString(T[] array) {
        return join(array);
    }

    public static String toString(boolean[] array) {
        return join(array);
    }

    public static String toString(char[] array) {
        return join(array);
    }

    public static String toString(byte[] array) {
        return join(array);
    }

    public static String toString(short[] array) {
        return join(array);
    }

    public static String toString(int[] array) {
        return join(array);
    }

    public static String toString(long[] array) {
        return join(array);
    }

    public static String toString(float[] array) {
        return join(array);
    }

    public static String toString(double[] array) {
        return join(array);
    }

    /**
     * 连接数组，默认以逗号为分隔符
     *
     * @param array 数组
     * @return 以逗号连接的字符串
     */
    public static <T> String join(T[] array) {
        return join(array, Strings.COMMA);
    }

    /**
     * 连接数组
     *
     * @param separator 分隔符
     * @param array     数组
     * @return 以分隔符连接的数组
     */
    public static <T> String join(T[] array, String separator) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : array) {
            sb.append(t).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(boolean[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, boolean[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (boolean b : array) {
            sb.append(b).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(char[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, char[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append(c).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(byte[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, byte[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(b).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(short[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, short[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (short s : array) {
            sb.append(s).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(int[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, int[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(long[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, long[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (long l : array) {
            sb.append(l).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(float[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, float[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (float f : array) {
            sb.append(f).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    public static String join(double[] array) {
        return join(Strings.COMMA, array);
    }

    public static String join(String separator, double[] array) {
        if (isEmpty(array)) {
            return Strings.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (double d : array) {
            sb.append(d).append(separator);
        }
        sb.delete(sb.lastIndexOf(separator), sb.length());
        return sb.toString();
    }

    /**
     * 倒序数组中的元素
     */
    public static <T> void reverse(T[] array) {
        if (array == null) {
            return;
        }
        T t;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
    }

    public static void reverse(boolean[] array) {
        if (array == null) {
            return;
        }
        boolean b;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            b = array[i];
            array[i] = array[j];
            array[j] = b;
        }
    }

    public static void reverse(char[] array) {
        if (array == null) {
            return;
        }
        char c;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            c = array[i];
            array[i] = array[j];
            array[j] = c;
        }
    }

    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        byte b;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            b = array[i];
            array[i] = array[j];
            array[j] = b;
        }
    }

    public static void reverse(short[] array) {
        if (array == null) {
            return;
        }
        short s;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            s = array[i];
            array[i] = array[j];
            array[j] = s;
        }
    }

    public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int e;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            e = array[i];
            array[i] = array[j];
            array[j] = e;
        }
    }

    public static void reverse(long[] array) {
        if (array == null) {
            return;
        }
        long l;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            l = array[i];
            array[i] = array[j];
            array[j] = l;
        }
    }

    public static void reverse(float[] array) {
        if (array == null) {
            return;
        }
        float f;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            f = array[i];
            array[i] = array[j];
            array[j] = f;
        }
    }

    public static void reverse(double[] array) {
        if (array == null) {
            return;
        }
        double d;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            d = array[i];
            array[i] = array[j];
            array[j] = d;
        }
    }

    /**
     * 连接数组和可变的参数
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(T[] array, T... ts) {
        Class<?> clazz = array.getClass().getComponentType();
        if (isEmpty(array)) {
            return (T[]) Array.newInstance(clazz, 0);
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        T[] newArray = (T[]) Array.newInstance(clazz, size);
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static boolean[] concat(boolean[] array, boolean... ts) {
        if (isEmpty(array)) {
            return Arrays.BOOLEANS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        boolean[] newArray = new boolean[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static char[] concat(char[] array, char... ts) {
        if (isEmpty(array)) {
            return Arrays.CHARS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        char[] newArray = new char[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static byte[] concat(byte[] array, byte... ts) {
        if (isEmpty(array)) {
            return Arrays.BYTES;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        byte[] newArray = new byte[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static short[] concat(short[] array, short... ts) {
        if (isEmpty(array)) {
            return Arrays.SHORTS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        short[] newArray = new short[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static int[] concat(int[] array, int... ts) {
        if (isEmpty(array)) {
            return Arrays.INTS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        int[] newArray = new int[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static long[] concat(long[] array, long... ts) {
        if (isEmpty(array)) {
            return Arrays.LONGS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        long[] newArray = new long[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static float[] concat(float[] array, float... ts) {
        if (isEmpty(array)) {
            return Arrays.FLOATS;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        float[] newArray = new float[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    public static double[] concat(double[] array, double... ts) {
        if (isEmpty(array)) {
            return Arrays.DOUBLES;
        }
        final int len1 = array.length;
        final int len2 = ts.length;
        final int size = array.length + ts.length;
        double[] newArray = new double[size];
        for (int i = 0, m = 0, n = 0; i < newArray.length; i++) {
            if (m < len1) {
                newArray[i] = array[m];
                m++;
            } else if (n < len2) {
                newArray[i] = ts[n];
                n++;
            }
        }
        return newArray;
    }

    /**
     * 从起始位置到数组末尾，截取数组，返回新的子数组。
     * 负数参数表示相对于数组最后一个元素的位置，最后一个元素是-1，最后第二个元素是-2......
     * <pre>
     * [1, 2, 3, 4, 5].slice(2) = [3, 4, 5];
     * [1, 2, 3, 4, 5].slice(-2) = [4, 5];
     * </pre>
     *
     * @param start 起始位置（包含），第一个值是0，第二值是1......
     */
    public static <T> T[] slice(T[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    /**
     * 从起始位置到结束位置，截取数组，返回新的子数组。
     * 负数参数表示相对于数组最后一个元素的位置，最后一个元素是-1，最后第二个元素是-2......
     * <pre>
     * [1, 2, 3, 4, 5].slice(2) = [3, 4, 5];
     * [1, 2, 3, 4, 5].slice(-2) = [4, 5];
     * [1, 2, 3, 4, 5].slice(1, 2) = [2];
     * [1, 2, 3, 4, 5].slice(1, -2) = [2, 3];
     * </pre>
     *
     * @param start 起始位置（包含），第一个值是0，第二值是1......
     * @param end   结束位置（不包含）
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] slice(T[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        Class<?> clazz = array.getClass().getComponentType();
        if (size <= 0) {
            return (T[]) Array.newInstance(clazz, 0);
        } else {
            T[] subArray = (T[]) Array.newInstance(clazz, size);
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static boolean[] slice(boolean[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static boolean[] slice(boolean[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.BOOLEANS;
        } else {
            boolean[] subArray = new boolean[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static char[] slice(char[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static char[] slice(char[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.CHARS;
        } else {
            char[] subArray = new char[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static byte[] slice(byte[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static byte[] slice(byte[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.BYTES;
        } else {
            byte[] subArray = new byte[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static short[] slice(short[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static short[] slice(short[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.SHORTS;
        } else {
            short[] subArray = new short[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static int[] slice(int[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static int[] slice(int[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.INTS;
        } else {
            int[] subArray = new int[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static long[] slice(long[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static long[] slice(long[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.LONGS;
        } else {
            long[] subArray = new long[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static float[] slice(float[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static float[] slice(float[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.FLOATS;
        } else {
            float[] subArray = new float[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }

    public static double[] slice(double[] array, int start) {
        return array == null ? null : slice(array, start, array.length);
    }

    public static double[] slice(double[] array, int start, int end) {
        if (array == null) {
            return null;
        }
        final int len = array.length;
        if (start > len) {
            start = len;
        } else if (start < -len) {
            start = -len;
        }
        if (end > len) {
            end = len;
        } else if (end < -len) {
            end = -len;
        }
        // 获取截取位置
        start = (start >= 0) ? start : len + start;
        end = (end >= 0) ? end : len + end;
        final int size = end - start;
        if (size <= 0) {
            return Arrays.DOUBLES;
        } else {
            double[] subArray = new double[size];
            // 截取数组
            for (int i = 0, m = 0, j = len - 1, n = size - 1; i <= j && m <= n; i++, j--) {
                if (i >= start) {
                    subArray[m] = array[i];
                    m++;
                }
                if (j < end) {
                    subArray[n] = array[j];
                    n--;
                }
            }
            return subArray;
        }
    }
}
