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
public class GenerationYml extends BaseModule {
    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        File file = new File(DirCacheHolder.get(Constants.RESOURCE_KRY),
                "application.yml"
        );

        // 写入文件
        super.writeFile(file, "yml.ftl", null);

        log.info("创建配置文件 application.yml {}", file.getPath());
    }
}
