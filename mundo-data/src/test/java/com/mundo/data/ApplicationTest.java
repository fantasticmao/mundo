package com.mundo.data;

import com.mundo.core.support.MapBuilder;
import com.mundo.data.datasource.PartitionDataSource;
import com.mundo.data.datasource.PartitionLookupKeyStrategy;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;
import java.util.Map;

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

    DataSource test00() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test00")
                .username("test")
                .password("123456")
                .build();
    }

    DataSource test01() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test01")
                .username("test")
                .password("123456")
                .build();
    }

    DataSource test02() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test02")
                .username("test")
                .password("123456")
                .build();
    }

    DataSource test03() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test03")
                .username("test")
                .password("123456")
                .build();
    }

    DataSource test04() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test04")
                .username("test")
                .password("123456")
                .build();
    }

    @Bean
    PartitionDataSource partitionDataSource() {
        Map<String, DataSource> dataSources = MapBuilder.<String, DataSource>create(5)
                .put("test00", test00())
                .put("test01", test01())
                .put("test02", test02())
                .put("test03", test03())
                .put("test04", test04())
                .build();
        PartitionLookupKeyStrategy lookupKeyStrategy = key -> String.format("test%02d", Integer.valueOf(key.toString()));
        return new PartitionDataSource(dataSources, null, lookupKeyStrategy);
    }

    @Bean(name = "memcachedClient")
    XMemcachedClientFactoryBean memcachedClientFactoryBean() {
        XMemcachedClientFactoryBean factoryBean = new XMemcachedClientFactoryBean();
        factoryBean.setServers("localhost:11211");
        factoryBean.setCommandFactory(new BinaryCommandFactory());
        factoryBean.setConnectionPoolSize(2);
        factoryBean.setOpTimeout(3_000);
        return factoryBean;
    }
}
