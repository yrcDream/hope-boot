package com.hope.application.project.impl;

import com.hope.application.project.IProjectGenerator;
import com.hope.application.project.ProjectInfo;
import com.hope.constans.Constants;
import com.hope.util.DirCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Slf4j
@Service
public class ProjectGeneratorImpl implements IProjectGenerator {

    @Resource
    private GenerationProjectDir generationProjectDir;
    @Resource
    private GenerationApplication generationApplication;
    @Resource
    private GenerationYml generationYml;
    @Resource
    private GenerationPom generationPom;
    @Resource
    private GenerationTest generationTest;
    @Resource
    private GenerationIgnore generationIgnore;
    @Resource
    private GenerationPackageInfo generationPackageInfo;

    @Override
    public void generator(ProjectInfo projectInfo) throws Exception {
        //URL resource = this.getClass().getResource("/");
        String projectsRoot = Constants.ROOT_DIR + "/" + projectInfo.getArtifactId();
        String lastPackageName = projectInfo.getArtifactId().replaceAll("-", "").toLowerCase();
        //启动类名称
        String[] split = projectInfo.getArtifactId().split("-");
        StringBuffer applicationJavaName = new StringBuffer();
        Arrays.asList(split).forEach(s -> {
            applicationJavaName.append(s.substring(0, 1).toUpperCase() + s.substring(1));
        });
        applicationJavaName.append("Application");

        DirCacheHolder.init();
        // 0. 创建项目目录结构
        generationProjectDir.doGeneration(projectInfo,projectsRoot,lastPackageName);

        // 1. 创建  Application.java
        generationApplication.doGeneration(projectInfo, projectsRoot, lastPackageName, applicationJavaName);

        // 2. 生成 application.yml
        generationYml.doGeneration(projectInfo, projectsRoot);

        // 3. 生成 pom.xml
        generationPom.doGeneration(projectInfo, projectsRoot);

        // 4. 生成 .gitignore
        generationIgnore.doGeneration(projectInfo, projectsRoot);

        // 5. DDD 四层描述文件
        generationPackageInfo.doGeneration(projectInfo, projectsRoot, lastPackageName, applicationJavaName);


        // 6. 创建测试类 ApiTest.java
        generationTest.doGeneration(projectInfo, projectsRoot, lastPackageName, applicationJavaName);

        DirCacheHolder.clear();

    }
}
