package com.hope.application.project;

import com.hope.HopeGeneratorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeGeneratorApplication.class)
public class IProjectGeneratorTest {

    @Resource
    IProjectGenerator projectGenerator;

    @Test
    public void projectGeneratorTest() throws Exception {
        List<String> tableSqls = new ArrayList<>();
        tableSqls.add("create table user (`id` bigint(11) primary key,`name` varchar(50))");
        ProjectInfo projectInfo = new ProjectInfo(
                "cn.yrc.demo",
                "project-test",
                "1.0.0-SNAPSHOT",
                "project-test",
                "Demo project for Spring Boot",
                tableSqls);
        projectGenerator.generator(projectInfo);
    }
}
