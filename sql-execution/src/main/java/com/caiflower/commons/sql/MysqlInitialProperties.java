package com.caiflower.commons.sql;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * mysql数据库初始化配置
 *
 * @author: lijinlong
 * @date: 2022/8/23 11:20
 */
@ConfigurationProperties("mysql.init")
public class MysqlInitialProperties {

    private Boolean active = false;

    private String location = "classpath:/init/init.sql";

    private String ddlLocations = "classpath:/init/ddl.sql";

    private String dmlLocations = "classpath:/init/dml.sql";

    public void setDdlLocations(String ddlLocations) {
        this.ddlLocations = ddlLocations;
    }

    public String getDdlLocations() {
        return ddlLocations;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    public void setDmlLocations(String dmlLocations) {
        this.dmlLocations = dmlLocations;
    }

    public String getDmlLocations() {
        return dmlLocations;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
