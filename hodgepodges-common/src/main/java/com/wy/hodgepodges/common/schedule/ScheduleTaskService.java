package com.wy.hodgepodges.common.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wy
 * @version V1.0
 * @desc 计划任务执行
 * @date 2020-03-18 20:30
 */
@Service
@EnableScheduling
@EnableAsync
@Slf4j
public class ScheduleTaskService  {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 500000)
    public void reportCurrentTime(){
        log.info("每隔5s执行一次"+dateFormat.format(new Date()));
    }
    @Scheduled(cron = "0 28 11 ? * *")
    public void fixTimeExecution(){log.info("在指定时间"+dateFormat.format(new Date())+"执行");
    }
}