package com.mundo.data;

import com.mundo.data.datasource.PartitionDataSource;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
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
//@EnableJpaRepositories
@Configuration
public class ApplicationTest {

    @Bean
    DataSource sns00() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.200.77:3306/sns00")
                .username("mydxy")
                .password("e144(e2beae1@830d1")
                .build();
    }

    @Bean
    DataSource sns01() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.200.77:3306/sns01")
                .username("mydxy")
                .password("e144(e2beae1@830d1")
                .build();
    }

    @Bean
    DataSource sns02() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.200.77:3306/sns02")
                .username("mydxy")
                .password("e144(e2beae1@830d1")
                .build();
    }

    @Bean
    DataSource sns03() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.200.77:3306/sns03")
                .username("mydxy")
                .password("e144(e2beae1@830d1")
                .build();
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    PartitionDataSource partitionDataSource() {
        return new PartitionDataSource(key -> "");
    }

    @Bean(name = "memcachedClient")
    XMemcachedClientFactoryBean memcachedClientFactoryBean() {
        XMemcachedClientFactoryBean factoryBean = new XMemcachedClientFactoryBean();
        factoryBean.setServers("192.168.200.95:11211");
        factoryBean.setCommandFactory(new BinaryCommandFactory());
        factoryBean.setConnectionPoolSize(2);
        factoryBean.setOpTimeout(3_000);
        return factoryBean;
    }
}
