package com.wy.hodgepodges.common.pdf;

import freemarker.template.Configuration;

public class FreemarkerConfiguration {

	private static Configuration config;

	static {
		config = new Configuration();
		config.setClassForTemplateLoading(FreemarkerConfiguration.class, "/template");
	}

	public static Configuration getConfiguation() {
		return config;
	}

}
