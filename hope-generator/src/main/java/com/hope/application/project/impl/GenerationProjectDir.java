package com.hope.application.project.impl;

import com.hope.application.BaseModule;
import com.hope.application.project.ProjectInfo;
import com.hope.constans.Constants;
import com.hope.util.DirCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class GenerationProjectDir extends BaseModule {

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot,String lastPackageName) throws Exception {

        String packageName = projectInfo.getGroupId();
        String basePackagePath = packageName.replace(".", "/");

        log.info("开始创建项目目录");

        File baseDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath);
        super.createFir(baseDir);
        log.info("创建base目录：{}", baseDir.getPath());
        DirCacheHolder.add(Constants.BASE_DIR_KEY,baseDir);

        File controllerDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.CONTROLLER_DIR);
        super.createFir(controllerDir);
        log.info("创建controller目录：{}", controllerDir.getPath());
        DirCacheHolder.add(Constants.CONTROLLER_DIR_KEY,controllerDir);

        File serviceDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.SERVICE_DIR);
        super.createFir(serviceDir);
        log.info("创建service目录：{}", serviceDir.getPath());
        DirCacheHolder.add(Constants.SERVICE_DIR_KEY,serviceDir);

        File serviceImplDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.SERVICE_IMPL_DIR);
        super.createFir(serviceImplDir);
        log.info("创建service-impl目录：{}", serviceImplDir.getPath());
        DirCacheHolder.add(Constants.SERVICE_IMPL_DIR_KEY,serviceImplDir);

        File daoDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.DAO_DIR);
        super.createFir(daoDir);
        log.info("创建dao目录：{}", daoDir.getPath());
        DirCacheHolder.add(Constants.DAO_DIR_KEY,daoDir);

        File pojoDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.POJO_DIR);
        super.createFir(pojoDir);
        log.info("创建pojo目录：{}", pojoDir.getPath());
        DirCacheHolder.add(Constants.POJO_DIR_KEY,pojoDir);

        File configDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.CONFIG_DIR);
        super.createFir(configDir);
        log.info("创建config目录：{}", configDir.getPath());
        DirCacheHolder.add(Constants.CONFIG_DIR_KEY,configDir);

        File commonDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.COMMON_DIR);
        super.createFir(commonDir);
        log.info("创建config目录：{}", commonDir.getPath());
        DirCacheHolder.add(Constants.COMMON_DIR_KEY,commonDir);

        File utilsDir = new File(projectsRoot + Constants.JAVA_PRE + "/" + basePackagePath + Constants.UTILS_DIR);
        super.createFir(utilsDir);
        log.info("创建utils目录：{}", utilsDir.getPath());
        DirCacheHolder.add(Constants.UTILS_DIR_KEY,utilsDir);

        File resourceDir = new File(projectsRoot + Constants.RESOURCE_PRE);
        super.createFir(resourceDir);
        log.info("创建resource目录：{}", resourceDir.getPath());
        DirCacheHolder.add(Constants.RESOURCE_KRY,resourceDir);


        File mapperXmlDir = new File(projectsRoot + Constants.MAPPER_XML_DIR);
        super.createFir(resourceDir);
        log.info("创建mapper-xml目录：{}", mapperXmlDir.getPath());
        DirCacheHolder.add(Constants.MAPPER_XML_DIR_KEY,mapperXmlDir);
        log.info("结束创建项目目录");


    }
}