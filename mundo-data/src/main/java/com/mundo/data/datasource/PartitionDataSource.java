package com.mundo.data.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.function.Function;

/**
 * PartitionDataSource
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionDataSource extends AbstractRoutingDataSource {
    private Function<Long, Integer> hashFunction;

    public PartitionDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources, Function<Long, Integer> hashFunction) {
        setTargetDataSources(targetDataSources);
        setDefaultTargetDataSource(defaultTargetDataSource);
        this.hashFunction = hashFunction;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        long seed = PartitionContext.pop();
        int key = hashFunction.apply(seed);
        return key < 10 ? "lang0" + key : "lang" + key;
    }
}
