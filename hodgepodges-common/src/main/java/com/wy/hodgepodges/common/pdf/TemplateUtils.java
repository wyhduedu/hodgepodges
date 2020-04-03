package com.wy.hodgepodges.common.pdf;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用 工具类
 */
public class TemplateUtils {

    public static String getTranslateTemplate(String path, Map<String, Object> model) throws Exception {
        Configuration config = FreemarkerConfiguration.getConfiguation();
        config.setDefaultEncoding("UTF-8");
        Template template = config.getTemplate(path);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        template.setEncoding("UTF-8");
        template.process(model, writer);
        content = stringWriter.toString();
        writer.flush();
        writer.close();
        return content;
    }

    public static void main(String[] args) throws Exception {


          final String DEST = "/Users/wy/Downloads/notice31.pdf";
//          final String HTML = "/Users/wy/Downloads/notice31.html";
          final String HTML = "/Users/wy/aqqqqaa.html";

///Users/wy/project/workspace/hodgepodges/hodgepodges-common/src/main/java/com/wy/hodgepodges/common/pdf/confirmation_wanglai.html


        OutputStream out = new FileOutputStream("/Users/wy/Downloads/notice3111111.pdf");
        try {
            String c = getTranslateTemplate("test.html", new HashMap<>(0));
//            String c = getTranslateTemplate("confirmation_wanglai.html", new HashMap<>(0));
            new PdfGenerator().generate(c, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
