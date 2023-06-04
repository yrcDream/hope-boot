package com.hope.application;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BaseModule {
    private static String ENCODING = "UTF-8";

    private static Configuration cfg;


    static {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_23);
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/templates/xxl-code-generator");
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Template getTemplate(String ftl) throws IOException {
        return cfg.getTemplate(ftl, ENCODING);
    }

    protected void writeFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        try {
            getTemplate(ftl).process(dataModel, outputStreamWriter);
        } finally {
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
    }

    protected void createFir(File file) {
        if (file.exists()) {
            file.delete();
        }
        file.mkdirs();
    }
}
