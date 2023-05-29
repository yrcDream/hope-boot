package com.hope.application.project.impl;

import com.hope.application.ApplicationInfo;
import com.hope.application.BaseModule;
import com.hope.application.project.ProjectInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class GenerationTest extends BaseModule {

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {
        ApplicationInfo applicationInfo = new ApplicationInfo(
                projectInfo.getGroupId() + "." + lastPackageName,
                applicationJavaName.toString()
        );

        String packageName = projectInfo.getGroupId();
        String basePackagePath = packageName.replace(".", "/");

        File file = new File(projectsRoot + "/src/test/java/" + basePackagePath + "/",
                "ApiTest.java");

        // 写入文件
        super.writeFile(file, "test.ftl", applicationInfo);

        log.info("创建测试类 ApiTest.java {}", file.getPath());
    }
}
