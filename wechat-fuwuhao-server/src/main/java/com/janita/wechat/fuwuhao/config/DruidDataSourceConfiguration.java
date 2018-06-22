package com.janita.wechat.fuwuhao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 数据源bean
 * 连接池
 * @author Janita
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
@PropertySource(value={"classpath:config/db.properties"})
public class DruidDataSourceConfiguration {
    //spring-boot默认的日志是logback

    @Value("${datasource.url}")
    private String dbUrl;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setPassword(password);
        datasource.setUsername(username);
        datasource.setDriverClassName(driverClassName);
        return datasource;
    }
}