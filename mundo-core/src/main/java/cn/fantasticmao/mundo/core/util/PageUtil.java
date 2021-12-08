package cn.fantasticmao.mundo.core.util;

/**
 * PageUtil
 *
 * @author fantasticmao
 * @version 1.0.3
 * @since 2021-12-08
 */
public interface PageUtil {

    /**
     * 计算偏移量
     *
     * @param page 页数 {@code 0 <= page}
     * @param size 页长 {@code 1 <= size <= 500}
     * @return 偏移量
     * @see #size(int)
     */
    static int offset(int page, int size) {
        page = Math.max(page, 0);
        return page * size(size);
    }

    /**
     * 计算页长
     *
     * @param size 页长 {@code 1 <= size <= 500}
     * @return 页长
     */
    static int size(int size) {
        size = Math.max(size, 1);
        size = Math.min(size, 500);
        return size;
    }
}
