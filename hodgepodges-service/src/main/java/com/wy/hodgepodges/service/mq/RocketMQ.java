package com.wy.hodgepodges.service.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/9 8:16 下午
 */
@Slf4j
public class RocketMQ {
    public static void main(String[] args) {
        final Long[] totalTime = {0L};
        final Date[] date1 = {null};
        final Date[] date2 = {new Date()};
        MessageListenerOrderly messageListenerOrderly = new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                log.info("==========CONSUME_START===========");
                log.info(Thread.currentThread().getName()
                        + " Receive New Messages: " + msgs.size());
                try {
                    if (date1[0] == null) {
                        date1[0] = new Date();//在第一次消费时初始化
                    }
                    Thread.sleep(100);
//                    log.info("total:" + (++total));
                    date2[0] = new Date();
                    totalTime[0] = (date2[0].getTime() - date1[0].getTime());
                    log.info("totalTime:" + totalTime[0]);
                    log.info("==========CONSUME_SUCCESS===========");
                    return ConsumeOrderlyStatus.SUCCESS;
                } catch (Exception e) {
                    log.info("==========RECONSUME_LATER===========");
                    log.error(e.getMessage(), e);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }

        };


    }
}
