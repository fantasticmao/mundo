package cn.fantasticmao.mundo.data;

import cn.fantasticmao.mundo.data.jdbc.RoutingDataSource;
import cn.fantasticmao.mundo.data.jdbc.RoutingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * ApplicationTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/17
 */
@SpringBootApplication
public class ApplicationTest {
    @Resource
    private ResourceLoader resourceLoader;

    DataSource test00() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:test00.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    DataSource test01() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:test01.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    DataSource test02() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:test02.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    DataSource test03() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:test03.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    @Bean
    RoutingDataSource<Integer> routingDataSource() throws IOException {
        Map<Object, Object> dataSources = Map.of(
            "test00", test00(),
            "test01", test01(),
            "test02", test02(),
            "test03", test03()
        );
        RoutingStrategy<Integer> routingStrategy = new RoutingStrategy.ShardingByMod<>("test%02d", dataSources.size());
        return new RoutingDataSource<>(dataSources, test00(), routingStrategy, Integer.class);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("cn.fantasticmao.mundo.data");
        factory.setDataSource(dataSource);
        return factory;
    }

    @Bean
    PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
