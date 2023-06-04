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

                File pojoDir  = DirCacheHolder.get(Constants.POJO_DIR_KEY);
                String pojoName = classInfo.getClassName() + "POJO";
                this.generatePackageAndPath(pojoDir,pojoName,"POJO",params);
                File pojoFile = new File(pojoDir,pojoName + ".java");
                super.writeFile(pojoFile,"model.ftl",params);
                log.info("创建pojo类结束");

                File daoDir = DirCacheHolder.get(Constants.DAO_DIR_KEY);
                String daoName = classInfo.getClassName() + "Dao";
                this.generatePackageAndPath(daoDir,daoName,"Dao",params);
                File daoFile = new File(daoDir,daoName + ".java");
                super.writeFile(daoFile,"dao.ftl",params);
                log.info("创建dao层接口结束");

                File serviceDir = DirCacheHolder.get(Constants.SERVICE_DIR_KEY);
                String serviceName = classInfo.getClassName() + "Service";
                this.generatePackageAndPath(serviceDir,serviceName,"Service",params);
                File serviceFile = new File(serviceDir,serviceName + ".java");
                super.writeFile(serviceFile,"service.ftl",params);
                log.info("创建service接口结束");

                File serviceImplDir = DirCacheHolder.get(Constants.SERVICE_IMPL_DIR_KEY);
                String serviceImplName = classInfo.getClassName() + "ServiceImpl";
                this.generatePackageAndPath(serviceImplDir,serviceImplName,"ServiceImpl",params);
                File serviceImplFile = new File(serviceImplDir, serviceImplName + ".java");
                super.writeFile(serviceImplFile,"service_impl.ftl",params);
                log.info("创建service实现类结束");

                File controllerDir = DirCacheHolder.get(Constants.CONTROLLER_DIR_KEY);
                String controllerName = classInfo.getClassName() + "Controller";
                this.generatePackageAndPath(controllerDir,controllerName,"Controller",params);
                File controllerFile = new File(controllerDir ,controllerName + ".java");
                super.writeFile(controllerFile,"controller.ftl",params);
                log.info("创建controller类结束");

                File mapperFile = new File(DirCacheHolder.get(Constants.MAPPER_XML_DIR_KEY),classInfo.getClassName() + "Mapper.xml");
                super.writeFile(mapperFile,"mybatis.ftl",params);
                log.info("创建mapper-xml结束");
            } catch (Exception e) {
                log.error("解析建表语句出现异常:", e);
            }
        }
        log.info("创建分层和描述文件 {}", "package-info.java");
    }

    /**
     * 构建Java文件的package路径和全限定名
     * @param dir 所属目录
     * @param className 类名称
     * @param namePre 类型前缀
     * @param params 参数存储
     */
    public void generatePackageAndPath(File dir, String className,String namePre, Map<String, Object> params) {
        String dirPath = dir.getPath();
        int index = dirPath.indexOf(Constants.JAVA_PRE);
        StringBuilder filePath = new StringBuilder();
        filePath.append(dirPath.substring(index + Constants.JAVA_PRE.length() + 1));
        String packagePath = filePath.toString().replace("/",".");
        params.put(namePre+"Package",packagePath);
        params.put(namePre+"Class",packagePath + "." + className);
    }
}
