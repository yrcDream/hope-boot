package com.hope.application.project.impl;

import com.hope.application.BaseModule;
import com.hope.application.project.ProjectInfo;
import com.hope.constans.Constants;
import com.hope.core.CodeGeneratorTool;
import com.hope.core.model.ClassInfo;
import com.hope.util.DirCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GenerationPackageInfo extends BaseModule {


    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {

        // 写入文件
        List<String> tableSqlList = projectInfo.getTableSql();
        if (CollectionUtils.isEmpty(tableSqlList)) {
            log.error("创表信息为空");
            return;
        }
        for (String tableSql: tableSqlList) {
            try {
                ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("classInfo", classInfo);

                File controllerFile = new File(DirCacheHolder.get(Constants.CONTROLLER_DIR_KEY),classInfo.getClassName() + "Controller.java");
                super.writeFile(controllerFile,"controller.ftl",params);
                log.info("创建controller类结束");

                File serviceFile = new File(DirCacheHolder.get(Constants.SERVICE_DIR_KEY),classInfo.getClassName() + "Service.java");
                super.writeFile(serviceFile,"service.ftl",params);
                log.info("创建service接口结束");

                File serviceImplFile = new File(DirCacheHolder.get(Constants.SERVICE_IMPL_DIR_KEY),classInfo.getClassName() + "ServiceImpl.java");
                super.writeFile(serviceImplFile,"service_impl.ftl",params);
                log.info("创建service实现类结束");

                File daoFile = new File(DirCacheHolder.get(Constants.DAO_DIR_KEY),classInfo.getClassName() + "Dao.java");
                super.writeFile(daoFile,"dao.ftl",params);
                log.info("创建dao层接口结束");

                File pojoFile = new File(DirCacheHolder.get(Constants.POJO_DIR_KEY),classInfo.getClassName() + "Pojo.java");
                super.writeFile(pojoFile,"model.ftl",params);
                log.info("创建pojo类结束");

                File mapperFile = new File(DirCacheHolder.get(Constants.MAPPER_XML_DIR_KEY),classInfo.getClassName() + "Mapper.xml");
                super.writeFile(mapperFile,"mybatis.ftl",params);
                log.info("创建mapper-xml结束");
            } catch (Exception e) {
                log.error("解析建表语句出现异常:{}", e);
            }
        }
        log.info("创建分层和描述文件 {}", "package-info.java");
    }
}
