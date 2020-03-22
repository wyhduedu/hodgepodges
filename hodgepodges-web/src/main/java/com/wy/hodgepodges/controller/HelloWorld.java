package com.wy.hodgepodges.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-02 20:43
 */
@Controller
public class HelloWorld {

    @RequestMapping(value = "/hello")
    public @ResponseBody
    String login() {
        return "HDHHD";
    }
}
