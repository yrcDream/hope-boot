package com.hope.application.project.impl;

import com.hope.application.BaseModule;
import com.hope.application.project.ProjectInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class GenerationPom extends BaseModule {

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot + "/",
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "pom.ftl", projectInfo);

        log.info("创建配置文件 pom.xml {}", file.getPath());
    }
}
