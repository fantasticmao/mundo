package cn.fantasticmao.mundo.data.jdbc;

import java.lang.annotation.*;

/**
 * Annotation to declare the {@link RoutingDataSource DataSource} route seed. Used
 * on domain type fields, method parameters, method and interface declarations of
 * {@link org.springframework.data.repository.Repository Spring-Data Repositories}.
 * <p>
 * With {@link RoutingStrategy.ShardingByMod ShardingByMod Strategy}, this annotation
 * can be used to implement database sharding. For example:
 * <pre class="code">
 * &#64;Entity
 * &#64;Table(name = "t_user")
 * public class User {
 *     &#64;Id
 *     &#64;RoutingSeed
 *     private Integer id;
 *     private String name;
 *
 *     // ......
 * }
 *
 * public interface UserRepository extends CrudRepository&lt;User, Integer&gt; {
 *     &#64;Override
 *     User save(User user);
 *
 *     &#64;Override
 *     Optional&lt;User&gt; findById(&#64;RoutingSeed Integer id);
 * }
 * </pre>
 * <p>
 * With {@link RoutingStrategy.MultiTenant MultiTenant Strategy}, this annotation
 * can be used to implement the multi-tenant database pattern. For example:
 * <pre class="code">
 * &#64;Entity
 * &#64;Table(name = "t_employee")
 * public class Employee {
 *     &#64;Id
 *     private Integer id;
 *     private String name;
 *
 *     // ......
 * }
 *
 * public interface EmployeeRepository extends CrudRepository&lt;Employee, Integer&gt; {
 *     String DEPARTMENT_SALE = "sale";
 *     String DEPARTMENT_TECH = "tech";
 *
 *     &#64;RoutingSeed(DEPARTMENT_SALE)
 *     &#64;Query(value = "SELECT * FROM t_employee WHERE id = ?1", nativeQuery = true)
 *     Employee findByIdInSale(Integer id);
 *
 *     &#64;RoutingSeed(DEPARTMENT_TECH)
 *     &#64;Query(value = "SELECT * FROM t_employee WHERE id = ?1", nativeQuery = true)
 *     Employee findByIdInTech(Integer id);
 * }
 * </pre>
 *
 * @author fantasticmao
 * @version 1.0.6
 * @see RoutingDataSource
 * @see RoutingSeedContext
 * @see RoutingSeedExtractor
 * @see RoutingStrategy
 * @since 2022-08-17
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingSeed {

    /**
     * Set the route seed directly, only works on method and interface declarations.
     *
     * @return seed value that has been set
     */
    String value() default "";

}
