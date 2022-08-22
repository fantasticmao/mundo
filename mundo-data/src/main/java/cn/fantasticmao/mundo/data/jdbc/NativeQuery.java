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
     */
    @AliasFor(annotation = Query.class)
    String value() default "";

    /**
     * Alias for {@link Query#countQuery()}.
     */
    @AliasFor(annotation = Query.class)
    String countQuery() default "";

    /**
     * Alias for {@link Query#countProjection()}.
     */
    @AliasFor(annotation = Query.class)
    String countProjection() default "";

    /**
     * Alias for {@link Query#name()}.
     */
    @AliasFor(annotation = Query.class)
    String name() default "";

    /**
     * Alias for {@link Query#countName()}.
     */
    @AliasFor(annotation = Query.class)
    String countName() default "";
}
