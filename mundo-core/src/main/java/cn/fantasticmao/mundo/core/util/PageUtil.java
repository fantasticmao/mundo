package cn.fantasticmao.mundo.core.util;

/**
 * Calculate {@code offset} and {@code limit} parameters in sql.
 *
 * @author fantasticmao
 * @version 1.0.3
 * @since 2021-12-08
 */
public interface PageUtil {

    /**
     * Calculate the page offset.
     *
     * @param page greater or equals than 0
     * @param size greater or equals than 1, and less or equals than 500
     * @return valid page offset
     */
    static int offset(int page, int size) {
        page = Math.max(page, 0);
        return page * limit(size);
    }

    /**
     * Calculate the page size
     *
     * @param size greater or equals than 1, and less or equals than 500
     * @return valid page size
     */
    static int limit(int size) {
        size = Math.max(size, 1);
        size = Math.min(size, 500);
        return size;
    }
}
