package com.server.service;

import com.server.tools.CrudExample;
import com.server.tools.ZKManager;
import com.server.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25.
 */
public class TestZKManager  {



    @Test
    public void testRegister(){
        ZKManager.getInstance().registerNode("192.168.100.100");
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIPList(){
        List<String> ipList=ZKManager.getInstance().getNodeValueList();
        Assert.isTrue(ipList.size()>0);
        for(String ip:ipList){
            System.out.println(ip);
        }
    }


    @Test
    public void testGetServerList(){
        CuratorFramework zkClient = CrudExample.createWithOptions(Constants.ZK_CONNECT_STR, ZKManager.ZK_NAMESPACE, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
        try {
            zkClient.start();
            List<String> list= CrudExample.getChildrenFromNode(zkClient, "/");
            List<String> ipList=new ArrayList<String>();
            for(String s:list){
                System.out.println(s);
              String nodeValue= CrudExample.readNode(zkClient,ZKManager.SLASH+ZKManager.ZK_NAMESPACE+ZKManager.SLASH+s);
              ipList.add(nodeValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null!=zkClient){
                CloseableUtils.closeQuietly(zkClient);
            }
        }
    }

}
