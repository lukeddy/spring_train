package com.server.tools;

import com.server.util.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * zookeeper工具类
 */
public class ZKManager {
    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(ZKManager.class);
    public static final String SLASH = "/";
    public static final String ZK_NAMESPACE = "myappserver";
    public static final String ZK_NODE_PREFIX = "server";

    private static ZKManager zkManager = new ZKManager();
    private CuratorFramework zkClient = null;
    static String currNodeValue=null;

    private ZKManager() {
        if (null == zkClient) {
            zkClient = CuratorFrameworkFactory.builder().connectString(Constants.ZK_CONNECT_STR)
                    .namespace(ZK_NAMESPACE)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .connectionTimeoutMs(1000)
                    .sessionTimeoutMs(1000)
                    .build();
            //注册zookeeper连接状态监听
            zkClient.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                public void stateChanged(CuratorFramework client, ConnectionState newState) {
                    //TODO zookeeper连接状态更改处理逻辑
                    logger.info("** STATE CHANGED TO : " + newState);
                    if(ConnectionState.RECONNECTED==newState){
                        //TODO重新注册
                        if(null!=currNodeValue){
                            logger.info("重连后重新注册节点 : " + currNodeValue);
                            ZKManager.getInstance().registerNode(currNodeValue);
                        }
                    }
                }
            });
            zkClient.start();
            zkClient.newNamespaceAwareEnsurePath(SLASH + ZK_NAMESPACE);
        }
    }

    public static ZKManager getInstance() {
        return zkManager;
    }

    /**
     * 注册节点
     *
     * @param nodeValue
     */
    public void registerNode(String nodeValue) {
        String nodeName = ZK_NODE_PREFIX + nodeValue;
        String fullPath = SLASH + ZK_NAMESPACE + SLASH + nodeName;//格式:/appserver/server192.168.1.1
        try {
            String nodePath = SLASH + nodeName;
            //取得zookeeper事务对象
            CuratorTransaction transaction = zkClient.inTransaction();
            //使用事务创建节点并提交事务
            if (zkClient.checkExists().forPath(nodePath) != null) {//如果节点存在则更新
                transaction.setData().forPath(nodePath, nodeValue.getBytes()).and().commit();
            } else { //如果不存在则创建
                //创建的时候一定要设置节点为临时节点，否则当客户端断开时zookeeper不会将节点删除，临时节点通过CreateMode设置
                transaction.create().withMode(CreateMode.EPHEMERAL).forPath(nodePath, nodeValue.getBytes()).and().commit();
            }
            currNodeValue=nodeValue;
            logger.info("注册节点(" + fullPath + "," + nodeValue + ") 成功");
        } catch (Exception e) {
            logger.error("注册节点(" + fullPath + "," + nodeValue + ") 失败", e);
        } finally {
            //关闭zkClint应该放到application声明周期里边
            /*if (null != zkClient) {
                CloseableUtils.closeQuietly(zkClient);
            }*/
        }
    }

    /**
     * 从zookeeper中取得所有节点值，即IP列表
     *
     * @return
     */
    public List<String> getNodeValueList() {
        List<String> ipList = new ArrayList<String>();
        try {
            List<String> list = zkClient.getChildren().forPath("/");
            for (String s : list) {
                System.out.println(s);
                String nodeValue = new String(zkClient.getData().forPath(SLASH + s), "utf-8");
                ipList.add(nodeValue);
            }
        } catch (Exception e) {
            logger.error("OOPS,get ip list failed from zookeeper!", e);
        } finally {
            //关闭zkClint应该放到application声明周期里边
            /*if (null != zkClient) {
                CloseableUtils.closeQuietly(zkClient);
            }*/
        }
        return ipList;
    }


}
