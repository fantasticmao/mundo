package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.*;

/**
 * Annotation to declare finder native queries directly on repository methods.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @see Query
 * @since 2022-08-13
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Query(nativeQuery = true)
public @interface NativeQuery {
    /**
     * Alias for {@link Query#value()}.
     *
     * @return JPA query
     */
    @AliasFor(annotation = Query.class)
    String value() default "";

    /**
     * Alias for {@link Query#countQuery()}.
     *
     * @return count query
     */
    @AliasFor(annotation = Query.class)
    String countQuery() default "";

    /**
     * Alias for {@link Query#countProjection()}.
     *
     * @return projection part of the count query
     */
    @AliasFor(annotation = Query.class)
    String countProjection() default "";

    /**
     * Alias for {@link Query#name()}.
     *
     * @return name of the query
     */
    @AliasFor(annotation = Query.class)
    String name() default "";

    /**
     * Alias for {@link Query#countName()}.
     *
     * @return name of the count query
     */
    @AliasFor(annotation = Query.class)
    String countName() default "";
}
