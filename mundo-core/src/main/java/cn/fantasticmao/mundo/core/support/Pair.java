package cn.fantasticmao.mundo.core.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pair
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class Pair<T, R> {
    private T t;
    private R r;

    public Pair() {
    }

    public Pair(T t, R r) {
        this.t = t;
        this.r = r;
    }

}
