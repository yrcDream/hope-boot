package com.hope.application.project.impl;

import com.hope.application.ApplicationInfo;
import com.hope.application.BaseModule;
import com.hope.application.project.ProjectInfo;
import com.hope.constans.Constants;
import com.hope.util.DirCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class GenerationApplication extends BaseModule {
    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {

        ApplicationInfo applicationInfo = new ApplicationInfo(
                projectInfo.getGroupId() + "." + lastPackageName,
                applicationJavaName.toString()
        );

        File file = new File(DirCacheHolder.get(Constants.BASE_DIR_KEY),
                applicationInfo.getClassName() + ".java");

        // 写入文件
        super.writeFile(file, "application.ftl", applicationInfo);

        log.info("创建主入口类 Application.java {}", file.getPath());
    }
}
