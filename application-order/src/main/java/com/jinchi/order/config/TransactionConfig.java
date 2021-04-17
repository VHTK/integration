package com.jinchi.order.config;

/**
 * Created by ZHANGTAO269 on 2019-1-7.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class TransactionConfig implements TransactionManagementConfigurer{

    @Autowired
    @Qualifier("dataSourceTransactionManager")
    private PlatformTransactionManager dataSourceTransactionManager;

    // spring不会加载自身的PlatformTransactionManager
    @Bean(name = "dataSourceTransactionManager")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return dataSourceTransactionManager;
    }
}
