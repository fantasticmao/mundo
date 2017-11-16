package com.mundo.data.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * PartitionDataSource
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionDataSource extends AbstractRoutingDataSource {

    public PartitionDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        setTargetDataSources(targetDataSources);
        setDefaultTargetDataSource(defaultTargetDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
