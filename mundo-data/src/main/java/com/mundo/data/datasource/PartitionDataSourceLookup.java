package com.mundo.data.datasource;

import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import javax.sql.DataSource;

/**
 * PartitionDataSourceLookup
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionDataSourceLookup implements DataSourceLookup {

    @Override
    public DataSource getDataSource(String dataSourceName) throws DataSourceLookupFailureException {
        return null;
    }


}
