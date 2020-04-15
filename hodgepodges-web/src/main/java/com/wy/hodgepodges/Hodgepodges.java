package com.wy.hodgepodges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wy
 */
@SpringBootApplication
public class Hodgepodges /*implements   WebApplicationInitializer*/ {

    public static void main(String[] args) {
        SpringApplication.run(Hodgepodges.class, args);
    }
//
////    @Override
//    public void onStartup(ServletContext servletContext)  {
//        log.info("加载配置文件");
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setServletContext(servletContext);
//        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",new DispatcherServlet(context));
//        dynamic.addMapping("/");
//        dynamic.setLoadOnStartup(1);
//        dynamic.setAsyncSupported(true);
//    }
}
