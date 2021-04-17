package com.jinchi.order.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
@ConfigurationProperties("spring")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private Map<String,Properties> datasources = new HashMap<>();

    public Map<String, Properties> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, Properties> datasources) {
        this.datasources = datasources;
    }

    @Bean(name = "dataSource")
    public DataSource shardingDataSource() throws SQLException {

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        //用户表配置，可以添加多个配置
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration1());
        shardingRuleConfig.getBindingTableGroups().add("gps");

        //设置数据库策略，传入的是sys_time
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("sys_time", DatabaseShardingAlgorithm.class.getName()));
        //设置数据表策略，传入的是sys_time
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("sys_time", TableShardingAlgorithm.class.getName()));

        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));
    }

    //创建用户表规则
    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();

        orderTableRuleConfig.setLogicTable("gps");
        //设置数据节点，格式为dbxx.tablexx。这里的名称要和map的别名一致。下面两种方式都可以
        //orderTableRuleConfig.setActualDataNodes("db_${0..1}.gps_${0..1}");
        orderTableRuleConfig.setActualDataNodes("db_201810.gps_20181014,db_201810.gps_20181015,db_201811.gps_20181114,db_201811.gps_20181115");
        //设置纵列名称
        orderTableRuleConfig.setKeyGeneratorColumnName("sys_time");
        return orderTableRuleConfig;
    }

    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration1() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        //设置用户表逻辑
        orderTableRuleConfig.setLogicTable("tb_user");
        //设置数据节点，格式为dbxx.tablexx。这里的名称要和map的别名一致
        orderTableRuleConfig.setActualDataNodes("gps_com.tb_user");

        return orderTableRuleConfig;
    }

    //下面函数是获取数据源，即包含有多少个数据库，读入到系统中存放于map中
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        datasources.forEach((k,v)->{
            try {
                result.put(k, DruidDataSourceFactory.createDataSource(v));
            } catch (Exception e) {
                logger.error("Create data source error, database name is {}",k);
            }
        });
        return result;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(shardingDataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
