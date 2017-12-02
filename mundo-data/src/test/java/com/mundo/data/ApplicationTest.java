package com.mundo.data;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

/**
 * ApplicationTest
 *
 * @author maodh
 * @since 2017/11/17
 */
@EnableMundoData
@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ApplicationTest {

    @Bean
    DataSource lang() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang")
                .username("lang")
                .password("lang.122333")
                .build();
    }

    @Bean
    DataSource lang01() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang01")
                .username("lang")
                .password("lang.122333")
                .build();
    }

    @Bean
    DataSource lang02() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang02")
                .username("lang")
                .password("lang.122333")
                .build();
    }
}
