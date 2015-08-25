package com.server.tools;

import com.server.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;

import java.util.List;

/**
 * zookeeper工具类
 */
public class ZKManager {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(ZKManager.class);
    public static final String SLASH="/";
    public static final String ZK_NAMESPACE="appserver";
    public static final String ZK_NODE_PREFIX="server";

    public static void register(String nodeValue) {
        String nodeName=ZK_NODE_PREFIX+nodeValue;
        String fullPath=SLASH+ZK_NAMESPACE+SLASH+nodeName;//格式:/appserver/server192.168.1.1
        CuratorFramework zkClient = CrudExample.createWithOptions(Constants.ZK_CONNECT_STR, ZK_NAMESPACE, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
        try {
            zkClient.start();
            zkClient.newNamespaceAwareEnsurePath(SLASH + ZK_NAMESPACE);
            //创建的时候一定要设置节点为临时节点，否则当客户端断开时zookeeper不会将节点删除，临时节点通过CreateMode设置
            String nodePath = SLASH + nodeName;
            CrudExample.createNodeWithModeAndTransaction(zkClient, CreateMode.EPHEMERAL,nodePath,nodeValue);
            //CrudExample.createOrUpdateNode(zkClient,slash+nodeName,nodeValue);
            //String result= CrudExample.readNode(zkClient,nodePath);
            //System.out.println(result);
            if (CrudExample.checkNodeExist(zkClient, nodePath)) {
                logger.info("注册节点成功:"+fullPath+",值："+nodeValue);
            } else {
                logger.error("糟了，注册节点失败:"+fullPath+",值："+nodeValue);
            }
        } catch (Exception e) {
            logger.error("OOPS,register node failed!", e);
        } finally {
            //关闭zkClint应该放到application声明周期里边
            /*if (null != zkClient) {
                CloseableUtils.closeQuietly(zkClient);
            }*/
        }
    }


}
