package com.server.service;

import com.server.tools.CrudExample;
import com.server.tools.ZKManager;
import com.server.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2015/8/25.
 */
public class TestZKManager  {



    @Test
    public void testRegister(){
        ZKManager.register("192.168.100.100");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetServerList(){
        CuratorFramework zkClient = CrudExample.createWithOptions(Constants.ZK_CONNECT_STR, ZKManager.ZK_NAMESPACE, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
        try {
            List<String> list= CrudExample.getChildrenFromNode(zkClient, ZKManager.SLASH+ZKManager.ZK_NAMESPACE);
            for(String s:list){
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
