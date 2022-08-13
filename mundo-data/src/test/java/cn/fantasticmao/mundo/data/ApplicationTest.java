package cn.fantasticmao.mundo.data;

import cn.fantasticmao.mundo.data.partition.PartitionDataSource;
import cn.fantasticmao.mundo.data.partition.PartitionJpaRepositoryFactoryBean;
import cn.fantasticmao.mundo.data.partition.PartitionSeedToDataSourceKeyStrategy;
import cn.fantasticmao.mundo.data.partition.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

/**
 * ApplicationTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/17
 */
@EnableJpaRepositories(repositoryFactoryBeanClass = PartitionJpaRepositoryFactoryBean.class)
@SpringBootApplication
public class ApplicationTest {

    DataSource test00() {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/test00")
            .username("test")
            .password("123456")
            .build();
    }

    DataSource test01() {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/test01")
            .username("test")
            .password("123456")
            .build();
    }

    DataSource test02() {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/test02")
            .username("test")
            .password("123456")
            .build();
    }

    DataSource test03() {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/test03")
            .username("test")
            .password("123456")
            .build();
    }

    DataSource test04() {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/test04")
            .username("test")
            .password("123456")
            .build();
    }

    @Bean
    PartitionDataSource partitionDataSource() {
        Map<String, DataSource> dataSources = Map.of("test01", test01(),
            "test02", test02(),
            "test03", test03(),
            "test04", test04());
        PartitionSeedToDataSourceKeyStrategy partitionSeedToDataSourceKeyStrategy =
            new PartitionSeedToDataSourceKeyStrategy.NumberModulusStrategy("test%02d", dataSources.size());
        return new PartitionDataSource(dataSources, test00(), partitionSeedToDataSourceKeyStrategy);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, Collections.emptyMap(), null)
            .dataSource(dataSource)
            .packages(User.class)
            .build();
    }

    @Bean
    PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
