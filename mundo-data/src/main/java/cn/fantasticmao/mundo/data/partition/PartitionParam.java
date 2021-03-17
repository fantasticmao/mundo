package cn.fantasticmao.mundo.data.partition;

import java.lang.annotation.*;

/**
 * 获取 {@link org.springframework.data.repository.Repository} 方法中的第一个 {@link PartitionParam} 注解参数，
 * 用于计算 <code>PartitionSeed</code>。
 *
 * <code>PartitionSeed</code> 将会被托管于 {@link PartitionSeedContext}
 *
 * @author maodh
 * @see PartitionSeedContext
 * @since 2017/11/14
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PartitionParam {
}
