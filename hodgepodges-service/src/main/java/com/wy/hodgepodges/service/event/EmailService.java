package com.wy.hodgepodges.service.event;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc 实现以aware结尾的接口之后在bean被实例化之后会Spring会注入相应的资源/
 * 事件触发对象
 * @date 2020/6/29 3:47 下午
 */
@Data
public class EmailService implements ApplicationEventPublisherAware {
    private List<String> blackList;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void sendEmail(String address, String test) {
        if (blackList.contains(address)) {
            /**
             * 如果是包含的地址将发布该消息
             */
            BlackListEvent event = new BlackListEvent(this, address, test);
            publisher.publishEvent(event);
            return;
        }
    }

}
