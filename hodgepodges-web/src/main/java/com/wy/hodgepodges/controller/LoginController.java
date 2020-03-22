package com.wy.hodgepodges.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wy
 * @version V1.0
 * @desc 默认加载页面
 * @date 2020-03-21 23:18
 */
@Controller
public class LoginController {
    @RequestMapping({"", "login"})
    public @ResponseBody String login() {
        return "login";
    }

}
