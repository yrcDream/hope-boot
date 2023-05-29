package com.hope.application;

/**
 * 程序基础信息
 */
public class ApplicationInfo {
    /**
     * 包名
     */
    private String packageName;
    /**
     * 类名
     */
    private String className;



    public ApplicationInfo(String packageName) {
        this.packageName = packageName;
    }

    public ApplicationInfo(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
