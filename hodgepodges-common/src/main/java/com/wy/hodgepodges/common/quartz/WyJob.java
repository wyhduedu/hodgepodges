package com.wy.hodgepodges.common.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 15:43
 */
public class WyJob implements Job {

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(System.currentTimeMillis() + " - helloJob 任务执行");
    }
}