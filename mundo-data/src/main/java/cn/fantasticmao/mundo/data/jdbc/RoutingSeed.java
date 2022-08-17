package cn.fantasticmao.mundo.data.jdbc;

import java.lang.annotation.*;

/**
 * RoutingSeed
 * <p>
 * <ol>
 *     <li>class</li>
 *     <li>method</li>
 *     <li>arguments</li>
 *     <li>pojo filed</li>
 * </ol>
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingSeed {

    String value() default "";

}
