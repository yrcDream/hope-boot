package com.hope.application.project;

/**
 * 项目生成器
 */
public interface IProjectGenerator {
    /**
     * 根据项目基础信息生成项目原型
     * @param projectInfo 项目基础信息
     * @throws Exception 异常信息
     */
    void generator(ProjectInfo projectInfo) throws Exception;
}
