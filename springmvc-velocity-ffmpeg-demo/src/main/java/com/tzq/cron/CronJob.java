package com.tzq.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-7-13
 * Time: 上午11:54
 */

@Repository
public class CronJob {


    Logger LOGGER = LoggerFactory.getLogger(CronJob.class);

    public void test(){
        System.out.println("--------------Test-----------");
    }

}
