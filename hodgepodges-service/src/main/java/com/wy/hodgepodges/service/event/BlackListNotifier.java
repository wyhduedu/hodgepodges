package com.wy.hodgepodges.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author wy
 * @version V1.0
 * @desc 事件监听, 实现ApplicationListener的接口将监听相应的事件，当有事件时，将触发onApplicationEvent方法
 * @date 2020/6/29 3:50 下午
 */
@Slf4j
public class BlackListNotifier {

    @EventListener
    public void onApplicationEvent(BlackListEvent blackListEvent) {
        log.info("执行监听事件");
    }

    /**
     * 如果方法需要监听好几个事件，或者如果定义的方法没有任何参数,也可以在注解自身上指定事件类型
     */
    @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class, BlackListEvent.class})
    public void handleContextStart() {
        System.out.println("监听到事件");
    }

    /**
     * 如果需要发布一个事件作为另一个事件的结果，只需要更改方法签名返回应该发布的事件即可
     * 注意点是：异步事件不支持此功能
     */
    @EventListener
    public BlackListEvent handlerBlackListEvent(BlackListEvent event) {

        return event;
    }


}
