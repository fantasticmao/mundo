package cn.fantasticmao.mundo.data.partition;

/**
 * 指定分库实体的种子字段，使用方式例如：
 *
 * <pre>
 * public class User implements PartitionSeedProvider&lt;Integer&gt; {
 *     private int id;
 *
 *     public Integer getSeed() {
 *         return id;
 *     }
 * }
 * </pre>
 *
 * @author maodh
 * @version 1.0
 * @since 2019/1/2
 */
public interface PartitionSeedProvider<SEED> {

    SEED getSeed();
}
