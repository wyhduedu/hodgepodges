package com.wy.hodgepodges;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-02 20:43
 */
@RestController
public class HelloWorld {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String login(/*@RequestBody String aa*/) {

        System.out.println("fjkfdjk");
        return "HDHHD";
    }
}
