package com.example.demo.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by Administrator on 2018/5/20.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}