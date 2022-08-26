package com.caiflower.commons.sql;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mysql数据库初始化
 *
 * @author: lijinlong
 * @date: 2022/8/22 11:33
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(MysqlInitialProperties.class)
@AutoConfigureBefore({MybatisPlusAutoConfiguration.class})
public class MysqlInitialization {

    @Resource
    private DataSource dataSource;

    private MysqlInitialProperties mysqlInitialProperties;

    @Autowired
    public void setMysqlInitialProperties(MysqlInitialProperties mysqlInitialProperties) {
        this.mysqlInitialProperties = mysqlInitialProperties;
    }

    private void executeSQL(Statement statement, PathMatchingResourcePatternResolver resolver) {
        executeSQLFile(statement, resolver, mysqlInitialProperties.getLocation());
    }

    private void executeDDL(Statement statement, PathMatchingResourcePatternResolver resolver) {
        String ddlLocations = mysqlInitialProperties.getDdlLocations();
        for (String ddlLocation : ddlLocations.split(";")) {
            executeSQLFile(statement, resolver, ddlLocation);
        }
    }

    private void executeDML(Statement statement, PathMatchingResourcePatternResolver resolver) {
        String dmlLocations = mysqlInitialProperties.getDmlLocations();
        for (String dmlLocation : dmlLocations.split(";")) {
            executeSQLFile(statement, resolver, dmlLocation);
        }
    }

    /**
     * 执行sql文件
     *
     * @param statement Statement
     * @param resolver  资源解析器
     * @param location  文件位置
     */
    private void executeSQLFile(Statement statement, PathMatchingResourcePatternResolver resolver, String location) {
        if (location != null) {
            org.springframework.core.io.Resource resource = resolver.getResource(location);
            if (resource.exists()) {
                try (InputStream inputStream = resource.getInputStream()) {
                    for (String sql : parseSql(IOUtils.readLines(inputStream, StandardCharsets.UTF_8))) {
                        try {
                            statement.execute(sql);
                        } catch (SQLException e) {
                            log.info("sql execute exception: {}", e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    log.info("location:{} file read fail", location);
                }
            } else {
                log.info("location:{} file is not exists", location);
            }
        } else {
            log.info("MysqlInitialization: ddlLocation is null");
        }
    }

    private String[] parseSql(List<String> source) {
        return source.stream().filter((line) -> !StringUtils.isBlank(line)).collect(Collectors.joining("\n")).split(";");
    }

    @PostConstruct
    public void initialize() {
        if (!mysqlInitialProperties.getActive()) {
            log.info("MysqlInitialization is not active");
            return;
        }
        log.info("MysqlInitialization: start to initial mysql");
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            executeSQL(statement, resolver);
            executeDDL(statement, resolver);
            executeDML(statement, resolver);
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
        } catch (Exception e) {
            log.info("mysql init fail");
        }
    }
}
