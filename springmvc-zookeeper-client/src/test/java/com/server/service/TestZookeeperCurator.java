package com.server.service;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2015/8/24.
 */
public class TestZookeeperCurator {

    public void init(){

    }
    @Test
    public void testConnectZookeeper(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.253:2181,192.168.10.159:2181,192.168.1.254:2181", retryPolicy);
        client.newNamespaceAwareEnsurePath("service");
        client.start();
        try {
            client.create().forPath("/testData", "my server1".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }


}
