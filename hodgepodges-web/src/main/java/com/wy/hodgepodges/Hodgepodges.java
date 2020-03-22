package com.wy.hodgepodges;

import com.wy.hodgepodges.config.ConfigAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author wy
 */
@Slf4j
@SpringBootApplication
public class Hodgepodges implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Hodgepodges.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext)  {
        log.info("加载配置文件");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ConfigAdapter.class);
        context.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",new DispatcherServlet(context));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
        dynamic.setAsyncSupported(true);
    }
}
