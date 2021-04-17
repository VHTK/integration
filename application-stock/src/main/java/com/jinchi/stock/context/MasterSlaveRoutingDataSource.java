package com.jinchi.stock.context;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 我们自己定义一个DataSource类，来继承 AbstractRoutingDataSource
 */
public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
