package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan(
    basePackages="com.example.demo.persistence",
    sqlSessionFactoryRef = "postgresqlSqlSessionFactory"
)
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String postgresDriverClassName;
    @Value("${spring.datasource.url}")
    private String postgresqlUrl;
    @Value("${spring.datasource.username}")
    private String postgresqlUsername;
    @Value("${spring.datasource.password}")
    private String postgresqlPassword;

    @Bean
    public DataSource postgresqlDataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(postgresDriverClassName);
        dataSource.setJdbcUrl(postgresqlUrl);
        dataSource.setUsername(postgresqlUsername);
        dataSource.setPassword(postgresqlPassword);

        return dataSource;
    }

    @Bean(name = "postgresTransactionManager")
    public DataSourceTransactionManager postgresTransactionManager() {
        log.info(" ### RootConfig postgresTransactionManager ### ");
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(postgresqlDataSource());
        return transactionManager;
    }

    @Bean(name = "postgresqlSqlSessionFactory")
    public SqlSessionFactory postgresqlSqlSessionFactory() throws Exception {
        log.info(" ### Postgresql sqlSessionFactory ### ");
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(postgresqlDataSource());
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
        factory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis-config.xml"));
        return factory.getObject();
    }

}
