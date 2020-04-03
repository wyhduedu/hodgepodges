package com.wy.hodgepodges;

import com.wy.hodgepodges.config.ConfigAdapter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ConfigAdapter.class)
@WebAppConfiguration("src/main/resources")
@SpringBootTest
@PropertySource( "classpath:application1.properties" )
public class WebApplicationTests  {

    @Value("${aaaa.aaaa}")
    private String  aa;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        System.out.println("djdjjd");
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(equalTo("Hello World!")));

    }


    @Test
    public void ssss() {
        System.out.println("dkdk");
        Assert.assertEquals("djdhd", "djdj");
    }

    public static void main(String[] args) {
        //@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        System.out.println("djdjjd");
        //如果加载spring-context.xml文件：
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    }

}
