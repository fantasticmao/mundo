package cn.fantasticmao.mundo.data.partition;

/**
 * 指定分库实体的种子字段
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019/1/2
 */
public interface PartitionSeedProvider {

    Object getSeed();
}
