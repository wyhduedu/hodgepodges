package com.wy.hodgepodges;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class WebApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        //@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        System.out.println("djdjjd");
        //如果加载spring-context.xml文件：
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    }

}
