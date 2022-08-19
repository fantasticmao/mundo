package cn.fantasticmao.mundo.data.jdbc.user;

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
 * UserDataSourceConfiguration
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-19
 */
@SpringBootApplication
public class UserDataSourceConfiguration {
    @Resource
    private ResourceLoader resourceLoader;

    private DataSource user_00() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:user_00.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    private DataSource user_01() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:user_01.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    private DataSource user_02() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:user_02.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    private DataSource user_03() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:user_03.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    @Bean
    public RoutingDataSource<Integer> routingDataSource() throws IOException {
        Map<Object, Object> dataSources = Map.of(
            "user_00", user_00(),
            "user_01", user_01(),
            "user_02", user_02(),
            "user_03", user_03()
        );
        RoutingStrategy<Integer> routingStrategy = new RoutingStrategy.ShardingByMod<>("user_%02d", dataSources.size());
        return new RoutingDataSource<>(dataSources, user_00(), routingStrategy, Integer.class);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
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
    public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
