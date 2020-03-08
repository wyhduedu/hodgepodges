//package com.wy.hodgepodges.service.pdf;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//
///**
// *
// * 通用 工具类
// *
// */
//public class TemplateUtils {
//
//	public static String getTranslateTemplate(String path, Map<String, Object> model) throws Exception {
//		String content = null;
//
//		Configuration config = FreemarkerConfiguration.getConfiguation();
//		config.setDefaultEncoding("UTF-8");
//		Template template = config.getTemplate(path);
//		content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//		// StringWriter stringWriter = new StringWriter();
//		// BufferedWriter writer = new BufferedWriter(stringWriter);
//		// template.setEncoding("UTF-8");
//		// template.process(model, writer);
//		// content = stringWriter.toString();
//		// writer.flush();
//		// writer.close();
//		return content;
//	}
//
//	public static void main(String[] args) throws Exception {
//		OutputStream out = new FileOutputStream("d:/topdf.pdf");
//		try {
//			String c = getTranslateTemplate("topdf2.html", new HashMap<String, Object>(0));
//			// System.out.println(c);
//			new com.zh.audit.confirmation.htmltopdf.PdfGenerator().generate(c, out);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
