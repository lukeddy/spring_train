package com.server.tools;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.List;

public class CrudExample {

    /**
     * 关键点：创建节点时设置节点为临时节点，保证在客户端断开时节点被zookeeper清楚
     * @param args
     */
    public static void main(String[] args) {
        String conString="192.168.1.253:2181,192.168.10.159:2181,192.168.1.254:2181";
        String namespace="templist";
        String myNode="/tempnode";
        CuratorFramework zkClient=CrudExample.createWithOptions(conString,namespace, new ExponentialBackoffRetry(1000, 3), 1000, 1000);;
        try{
            zkClient.start();// 放在这前面执行
            zkClient.newNamespaceAwareEnsurePath("/" + namespace);
            //创建的时候一定要设置节点为临时节点，否则当客户端断开时zookeeper不会将节点删除，临时节点通过CreateMode设置
            //创建10个临时节点
            for(int i=1;i<=10;i++){
                CrudExample.createNodeWithModeAndTransaction(zkClient, CreateMode.EPHEMERAL, "/tempnode"+i, "temp node value"+i);
            }
            Thread.sleep(30000);
            if(CrudExample.checkNodeExist(zkClient,"/templist/tempnode3")){
                System.out.println("节点存在");
            }else{
                System.out.println("节点不存在");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(null!=zkClient){
                CloseableUtils.closeQuietly(zkClient);
            }
        }

    }

    /**
     * create a simple curator client
     * @param connectionString
     * @return
     */
    public static CuratorFramework createSimple(String connectionString) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    /**
     * create a curator client with options
     * @param connectionString
     * @param retryPolicy
     * @param connectionTimeoutMs
     * @param sessionTimeoutMs
     * @return
     */
    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder().connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }

    /**
     *
     * @param connectionString
     * @param namespace
     * @param retryPolicy
     * @param connectionTimeoutMs
     * @param sessionTimeoutMs
     * @return
     */
    public static CuratorFramework createWithOptions(String connectionString,String namespace, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder().connectString(connectionString)
                .namespace(namespace)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }

    /**
     * this will create the given ZNode with the given data
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().forPath(path, payload);
    }

    /**
     * 创建带有创建模式的节点
     * @param nodePath
     * @param content
     * @throws Exception
     */
    public static void createNodeWithMode(CuratorFramework client,CreateMode createMode, String nodePath,String content) throws Exception {
        client.create().withMode(createMode).forPath(nodePath, content.getBytes());
    }

    /**
     * 使用事务创建带有创建模式的节点
     * @param client
     * @param createMode
     * @param nodePath
     * @param content
     * @throws Exception
     */
    public static void createNodeWithModeAndTransaction(CuratorFramework client,CreateMode createMode, String nodePath,String content) throws Exception {
        CuratorTransaction transaction=client.inTransaction();
        transaction.create().withMode(createMode).forPath(nodePath, content.getBytes()).and().commit();
    }
    /**
     * this will create the given ZNode with the given data and with parent path
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void createWithParents(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().creatingParentsIfNeeded().forPath(path, payload);
    }


    /**
     * 创建带有父目录并设置节点创建模式
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void createWithParentsAndCreateMode(CuratorFramework client,CreateMode createMode, String path, byte[] payload) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(createMode).forPath(path, payload);
    }

    /**
     * this will create the given EPHEMERAL ZNode with the given data
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
    }


    /**
     * 创建或更新一个节点
     *
     * @param nodePath 路径
     * @param content 内容
     * **/
    public static void createOrUpdateNode(CuratorFramework client,String nodePath,String content)throws Exception{
        client.newNamespaceAwareEnsurePath(nodePath).ensure(client.getZookeeperClient());
        client.setData().forPath(nodePath, content.getBytes());
        System.out.println("创建或者更新节点‘" + nodePath + "’成功，内容为‘" + content + "’");
    }

    /**
     * 字节
     * @param client
     * @param nodePath
     * @param content
     * @throws Exception
     */
    public static void createOrUpdateNode(CuratorFramework client,String nodePath,String content,String charsetName)throws Exception{
        client.newNamespaceAwareEnsurePath(nodePath).ensure(client.getZookeeperClient());
        client.setData().forPath(nodePath,content.getBytes(charsetName));
        System.out.println("创建或者更新节点‘" + nodePath + "’成功，内容为‘" + content + "’");
    }
    /**
     * this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given data using Curator protection.
     * @param client
     * @param path
     * @param payload
     * @return
     * @throws Exception
     */
    public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload) throws Exception {
        return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
    }

    /**
     * set data for the given node
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.setData().forPath(path, payload);
    }

    /**
     * this is one method of getting event/async notifications
     * @param client
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void setDataAsync(CuratorFramework client, String path, byte[] payload) throws Exception {
        CuratorListener listener = new CuratorListener() {
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

            }
        };
        client.getCuratorListenable().addListener(listener);
        //set data for the given node asynchronously. The completion notification is done via the CuratorListener.
        client.setData().inBackground().forPath(path, payload);
    }

    /**
     * this is another method of getting notification of an async completion
     * @param client
     * @param callback
     * @param path
     * @param payload
     * @throws Exception
     */
    public static void setDataAsyncWithCallback(CuratorFramework client, BackgroundCallback callback, String path, byte[] payload) throws Exception {
        client.setData().inBackground(callback).forPath(path, payload);
    }

    /**
     * 判断节点是否存在
     * @param nodePath
     * **/
    public static boolean checkNodeExist(CuratorFramework client,String nodePath)throws Exception{
        if(client.checkExists().forPath(nodePath)==null){
            return false;
        }else{
            return true;
        }
    }
    /**
     * delete the given node
     * @param client
     * @param path
     * @throws Exception
     */
    public static void delete(CuratorFramework client, String path) throws Exception {
        client.delete().forPath(path);
    }

    /**
     * 删除节点
     * @param nodePath 删除节点的路径
     *
     * **/
    public static void deleteNode(CuratorFramework client,String nodePath)throws Exception{
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(nodePath);
        System.out.println("删除节点‘" + nodePath + "’成功");
    }
    /**
     * delete the given node and guarantee that it completes
     * @param client
     * @param path
     * @throws Exception
     */
    public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
        client.delete().guaranteed().forPath(path);
    }

    /**
     * Get children and set a watcher on the node. The watcher notification
     * will come through the CuratorListener (see setDataAsync() above).
     */
    public static List<String> watchedGetChildren(CuratorFramework client, String path) throws Exception {
        return client.getChildren().watched().forPath(path);
    }

    /**
     * Get children and set the given watcher on the node.
     */
    public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher) throws Exception {
        return client.getChildren().usingWatcher(watcher).forPath(path);
    }

    /**
     * 读取的路径
     * @param path
     * **/
    public static String readNode(CuratorFramework client,String path)throws Exception{
        return new String(client.getData().forPath(path),"utf-8");
    }

    /**
     * 获取某个节点下的所有子文件
     * */
    public static List<String> getRootChildren(CuratorFramework client)throws Exception{
        return client.getChildren().forPath("/");
    }

    /**
     * @param nodePath 路径
     * 获取某个节点下的所有子文件
     * */
    public static List<String> getChildrenFromNode(CuratorFramework client,String nodePath)throws Exception{
        return client.getChildren().forPath(nodePath);
    }
}
