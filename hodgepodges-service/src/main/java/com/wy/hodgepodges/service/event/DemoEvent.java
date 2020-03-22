package com.wy.hodgepodges.service.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-17 16:24
 */
public class DemoEvent extends ApplicationContextEvent {

    String msg;

    public DemoEvent(ApplicationContext source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getAaa() {
        return msg;
    }

    public void setAaa(String aa) {
          this.msg= aa;
    }

}
