package com.mundo.core.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Pair
 *
 * @author maomao
 * @since 2017/3/5
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class Pair<T, R> implements Serializable {
    private static final long serialVersionUID = 3585194245329360293L;

    private T t;
    private R r;

    public Pair() {
    }

    public Pair(T t, R r) {
        this.t = t;
        this.r = r;
    }

}
