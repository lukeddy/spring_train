package com.server.service;

import com.server.tools.CuratorTools;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public class TestCuratorTools extends TestBase {
    @Autowired
    private CuratorTools curatorTools;

    @Test
    public void testAddNodes(){
        try {
            for(int i=1;i<=6;i++){
                curatorTools.createOrUpdateNode("/myserver"+i,"192.168.1.10"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetRootNode(){
        try {
            List<String> nodeList=curatorTools.getRootChildren();
            Assert.notNull(nodeList);
            for(String s:nodeList){
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetNodes(){
        try {
            List<String> nodeList=curatorTools.getChildrenFromNode("/myserver1");
            Assert.notNull(nodeList);
            for(String s:nodeList){
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNodeExist(){
        try {
            boolean isNodeExist=curatorTools.checkNodeExist("/service/serverList/myserver1");
            Assert.isTrue(isNodeExist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteNode(){
        try {
            curatorTools.deleteNode("/service/serverList/myserver1");
            boolean isNodeExist=curatorTools.checkNodeExist("/service/serverList/myserver1");
            Assert.isTrue(!isNodeExist);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
