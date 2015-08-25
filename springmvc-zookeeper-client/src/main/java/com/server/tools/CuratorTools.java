package com.server.tools;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * zookeeper工具类
 */
public class CuratorTools {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(CuratorTools.class);

    static CuratorFramework zkClient=null;

    static String configZKHosts="192.168.1.253:2181,192.168.10.159:2181,192.168.1.254:2181";

     static String configZKNamespace="server_list";
    
     static String configZKParentPath="/server_list";

    static {
        logger.info("====================开始创建ZK客户端对象====================");
        String zkhosts=configZKHosts;//zk的host
        RetryPolicy rp=new ExponentialBackoffRetry(1000, 3);//重试机制
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkhosts)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(5000)
                .retryPolicy(rp);
        builder.namespace(configZKNamespace);
        CuratorFramework zclient = builder.build();
        zkClient=zclient;
        zkClient.start();// 放在这前面执行
        zkClient.newNamespaceAwareEnsurePath(configZKParentPath);
        logger.info("====================创建ZK客户端对象完成====================");
    }


    /**
     * 创建节点
     * @param nodePath
     * @param content
     * @throws Exception
     */
    public void createNodeWithEPHEMERAL(String nodePath,String content) throws Exception {
        zkClient.create().withMode(CreateMode.EPHEMERAL).forPath(nodePath, content.getBytes());
    }

    /**
     * 创建或更新一个节点
     *
     * @param nodePath 路径
     * @param content 内容
     * **/
    public void createOrUpdateNode(String nodePath,String content)throws Exception{
        zkClient.newNamespaceAwareEnsurePath(nodePath).ensure(zkClient.getZookeeperClient());
        zkClient.setData().forPath(nodePath, content.getBytes());
        logger.info("创建或者更新节点‘"+nodePath+"’成功，内容为‘"+content+"’");
    }


    /**
     * 删除节点
     * @param nodePath 删除节点的路径
     *
     * **/
    public void deleteNode(String nodePath)throws Exception{
        zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(nodePath);
        logger.info("删除节点‘" + nodePath + "’成功");
    }


    /**
     * 判断节点是否存在
     * @param nodePath
     * **/
    public boolean checkNodeExist(String nodePath)throws Exception{
        if(zkClient.checkExists().forPath(nodePath)==null){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 读取的路径
     * @param path
     * **/
    public String readNode(String path)throws Exception{
        return new String(zkClient.getData().forPath(path),"utf-8");
    }

    /**
     * 获取某个节点下的所有子文件
     * */
    public List<String> getRootChildren()throws Exception{
        return zkClient.getChildren().forPath("/");
    }

    /**
     * @param nodePath 路径
     * 获取某个节点下的所有子文件
     * */
    public List<String> getChildrenFromNode(String nodePath)throws Exception{
        return zkClient.getChildren().forPath(nodePath);
    }

}
