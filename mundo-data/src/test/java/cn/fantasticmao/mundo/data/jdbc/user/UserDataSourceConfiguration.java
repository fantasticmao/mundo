package cn.fantasticmao.mundo.data.jdbc.user;

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
        Map<Object, DataSource> dataSources = Map.of(
            "user_00", user_00(),
            "user_01", user_01(),
            "user_02", user_02(),
            "user_03", user_03()
        );
        RoutingStrategy<Integer> routingStrategy = new RoutingStrategy.ShardingByMod<>("user_%02d", dataSources.size());
        return new RoutingDataSource<>(dataSources, new JDBC4DatabaseMetaData(null),
            routingStrategy, Integer.class);
    }

}
