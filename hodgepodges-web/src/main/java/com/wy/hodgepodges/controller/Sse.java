package com.wy.hodgepodges.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author wy
 * @version V1.0
 * @desc 服务端推送
 * @date 2020-03-22 19:12
 */
@Controller
public class Sse {

    @RequestMapping(value = "/push",produces = "text/event-stream")
    public @ResponseBody String push() throws InterruptedException {
        Random random = new Random();
         Thread.sleep(5000);


        return "data:Test 1,2,3"+random.nextInt()+"\n\n";
    }
}
