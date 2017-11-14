package com.mundo.support;

import java.util.function.Function;

/**
 * Page
 *
 * @author maodh
 * @since 2017/7/25
 */
public class Page {
    private int current; // the current page index number
    private int pageSize; // the number of items of this page
    private int totalSize; // the number of items of total pages

    public int getOffset() {
        return pageSize * (current - 1);
    }

    public int getOffset(Function<Page, Integer> function) {
        return function.apply(this);
    }

}
