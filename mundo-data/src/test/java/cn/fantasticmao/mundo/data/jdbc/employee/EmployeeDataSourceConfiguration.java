package cn.fantasticmao.mundo.data.jdbc.employee;

import cn.fantasticmao.mundo.data.jdbc.RoutingDataSource;
import cn.fantasticmao.mundo.data.jdbc.RoutingStrategy;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.sqlite.jdbc4.JDBC4DatabaseMetaData;

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
        Map<Object, DataSource> dataSources = Map.of(
            "employee_sale", employee_sale(),
            "employee_tech", employee_tech()
        );
        RoutingStrategy<String> routingStrategy = new RoutingStrategy.MultiTenant("employee_%s");
        return new RoutingDataSource<>(dataSources, new JDBC4DatabaseMetaData(null),
            routingStrategy, String.class);
    }

}
