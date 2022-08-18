package cn.fantasticmao.mundo.data.jdbc;

import java.lang.annotation.*;

/**
 * RoutingSeed
 * <p>
 * <ol>
 *     <li>Domain Fields</li>
 *     <li>Parameter Annotations</li>
 *     <li>Method Declaration</li>
 *     <li>Class Declaration</li>
 * </ol>
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingSeed {

    /**
     * Set the seed value directly, only valid for {@link ElementType#METHOD methods}
     * and {@link ElementType#TYPE types}.
     *
     * @return explicit seed value
     */
    String value() default "";

}
