package com.wy.hodgepodges.controller;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-23 13:11
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {


    @GetMapping(value = "/home")
    public String homePage() {
        return "test";
    }
}