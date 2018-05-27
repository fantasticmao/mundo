package com.mundo.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * ApplicationTest
 *
 * @author maodh
 * @since 2017/11/17
 */
@EnableMundoData
@EnableAspectJAutoProxy
@EnableJpaRepositories
@Configuration
public class ApplicationTest {

    @Bean
    DataSource test() {
        return DataSourceBuilder.create()
                .type(MysqlDataSource.class)
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test")
                .username("root")
                .password("mao.122333")
                .build();
    }

    //@Bean
    DataSource lang() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang")
                .username("lang")
                .password("lang.122333")
                .build();
    }

    //@Bean
    DataSource lang01() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang01")
                .username("lang")
                .password("lang.122333")
                .build();
    }

    //@Bean
    DataSource lang02() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/lang02")
                .username("lang")
                .password("lang.122333")
                .build();
    }
}
