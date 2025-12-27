package com.ravidpatel.mybookingapp.config;

import com.ravidpatel.mybookingapp.constant.DbType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DbType dbType = DbContextHolder.get();
        return dbType == null ? DbType.MASTER : dbType;
    }
}
