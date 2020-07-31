package com.wy.hodgepodges.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wy
 * @version V1.0
 * @desc spring事件通知
 * @date 2020/6/29 3:43 下午
 */
public class BlackListEvent extends ApplicationEvent {
    private String address;
    private String name;

    public BlackListEvent(Object source,String address,String name) {
        super(source);
        this.address=address;
        this.name=name;
    }
}
