package com.wy.hodgepodges;

import com.wy.hodgepodges.service.impl.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-22 20:41
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootConfiguration
@WebAppConfiguration("src/main/resources")
@SpringBootTest
@Transactional
public class WebApplicationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;
    @Autowired
    MockHttpServletRequest request;
    @Autowired
    DemoService demo;
    @Autowired
    MockHttpSession session;


    @Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    @Test
    public void testNormalController(){
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("page"))
                    .andExpect(forwardedUrl("/WEB-INFO/classes/views/page.jsp"))
                    .andExpect(model().attribute("msg",demo.outputResult()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }  @Test
    public void testRestController(){
        try {
            mockMvc.perform(get("/testRest"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("text/plain;charset=UTF-8"))
                    .andExpect(content().string(demo.outputResult()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
