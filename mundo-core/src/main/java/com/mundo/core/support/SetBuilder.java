package com.mundo.core.support;

import com.mundo.core.util.JsonUtil;
import org.apache.commons.lang3.builder.Builder;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * SetBuilder
 *
 * @author maodh
 * @since 2018/10/11
 */
@NotThreadSafe
public class SetBuilder<E> implements Builder<Set<E>> {
    private Set<E> set;

    private SetBuilder() {
        throw new AssertionError("No com.mundo.core.support.SetBuilder instances for you!");
    }

    private SetBuilder(Set<E> set) {
        this.set = set;
    }

    public static <E> SetBuilder<E> create() {
        return create(HashSet::new);
    }

    public static <E> SetBuilder<E> create(int capacity) {
        return create(() -> new HashSet<>(capacity));
    }

    public static <E> SetBuilder<E> create(Supplier<Set<E>> setSupplier) {
        return new SetBuilder<>(setSupplier.get());
    }

    public SetBuilder<E> addAll(Collection<E> c) {
        this.set.addAll(c);
        return this;
    }

    public SetBuilder<E> add(E e) {
        this.set.add(e);
        return this;
    }

    public SetBuilder<E> addIfAbsent(E e) {
        return addIf(e, element -> !set.contains(element));
    }

    public SetBuilder<E> addIfNonNull(E e) {
        return addIf(e, Objects::nonNull);
    }

    public SetBuilder<E> addIf(E e, Predicate<E> predicate) {
        if (predicate.test(e)) {
            this.set.add(e);
        }
        return this;
    }

    @Override
    public Set<E> build() {
        return set;
    }

    public String toJson() {
        return JsonUtil.toJson(set);
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
