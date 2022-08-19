package cn.fantasticmao.mundo.data.jdbc.employee;

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
 * EmployeeDataSourceConfiguration
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-19
 */
@SpringBootApplication
public class EmployeeDataSourceConfiguration {
    @Resource
    private ResourceLoader resourceLoader;

    private DataSource employee_sale() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:employee_sale.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    private DataSource employee_tech() throws IOException {
        File dbFile = resourceLoader.getResource("classpath:employee_tech.db").getFile();
        return DataSourceBuilder.create()
            .url("jdbc:sqlite:" + dbFile.getAbsolutePath())
            .build();
    }

    @Bean
    public RoutingDataSource<String> routingDataSource() throws IOException {
        Map<Object, Object> dataSources = Map.of(
            "employee_sale", employee_sale(),
            "employee_tech", employee_tech()
        );
        RoutingStrategy<String> routingStrategy = new RoutingStrategy.MultiTenant("employee_%s");
        return new RoutingDataSource<>(dataSources, employee_sale(), routingStrategy, String.class);
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
