package com.hope.application.project;

import java.util.List;

public class ProjectInfo {
    /**
     * pom纵坐标
     */
    private String groupId;
    /**
     * pom横坐标
     */
    private String artifactId;
    /**
     * 版本
     */
    private String version;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述
     */
    private String description;

    /**
     * 建表语句
     * 支持多个表
     */
    private List<String> tableSql;


    ProjectInfo() {}

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description, List<String> tableSql) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
        this.tableSql = tableSql;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTableSql() {
        return tableSql;
    }

    public void setTableSql(List<String> tableSql) {
        this.tableSql = tableSql;
    }
}
