package com.server.service;

import org.apache.commons.io.FileUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * zookeeper 测试类
 */
public class TestCuratorFramework {
    static CuratorFramework zkclient=null;
    static String nameSpace="php";

    static {
        String zkhosts="192.168.1.253:2181,192.168.10.159:2181,192.168.1.254:2181";//zk的host
        RetryPolicy rp=new ExponentialBackoffRetry(1000, 3);//重试机制
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkhosts)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(5000)
                .retryPolicy(rp);
        builder.namespace(nameSpace);
        CuratorFramework zclient = builder.build();
        zkclient=zclient;
        zkclient.start();// 放在这前面执行
        zkclient.newNamespaceAwareEnsurePath("/"+nameSpace);
    }

    /**
     * 创建或更新一个节点
     *
     * @param path 路径
     * @param content 内容
     * **/
    public void createOrUpdate(String path,String content)throws Exception{
        zkclient.newNamespaceAwareEnsurePath(path).ensure(zkclient.getZookeeperClient());
        zkclient.setData().forPath(path,content.getBytes());
        System.out.println("添加成功！！！");
    }
    /**
     * 删除zk节点
     * @param path 删除节点的路径
     *
     * **/
    public void delete(String path)throws Exception{
        zkclient.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        System.out.println("删除成功!");
    }
    /**
     * 判断路径是否存在
     * @param path
     * **/
    public void checkExist(String path)throws Exception{
        if(zkclient.checkExists().forPath(path)==null){
            System.out.println("路径不存在!");
        }else{
            System.out.println("路径已经存在!");
        }
    }
    /**
     * 读取的路径
     * @param path
     * **/
    public void read(String path)throws Exception{
        String data=new String(zkclient.getData().forPath(path),"gbk");
        System.out.println("读取的数据:"+data);
    }
    /**
     * @param path 路径
     * 获取某个节点下的所有子文件
     * */
    public void getListChildren(String path)throws Exception{
        List<String> paths=zkclient.getChildren().forPath(path);
        for(String p:paths){
            System.out.println(p);
        }
    }
    /**
     * @param zkPath zk上的路径
     * @param localpath 本地上的文件路径
     *
     * **/
    public void upload(String zkPath,String localpath)throws Exception{
        createOrUpdate(zkPath, "");//创建路径
        byte[] bs= FileUtils.readFileToByteArray(new File(localpath));
        zkclient.setData().forPath(zkPath, bs);
        System.out.println("上传文件成功！");
    }

    /**
     * 测试创建节点
     */
    @Test
    public void testCreateNode(){
        TestCuratorFramework ct=new TestCuratorFramework();
        for(int i=1;i<=3;i++){
            try {
                ct.createOrUpdate("/mynode"+i,"I am data from node mynode"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zkclient.close();
    }

    /**
     * 测试取得数据
     */
    @Test
    public void testGetListChildren(){
        TestCuratorFramework ct=new TestCuratorFramework();
        for(int i=1;i<=3;i++){
            try {
                String currNode="/mynode" + i;
                ct.checkExist(currNode);
                ct.read(currNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zkclient.close();
    }

    /**
     * 节点是否存在
     */
    @Test
    public void testNodeExist(){
        TestCuratorFramework ct=new TestCuratorFramework();
        for(int i=1;i<=3;i++){
            try {
                ct.checkExist("/mynode" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zkclient.close();
    }

    /**
     * 测试删除节点
     */
    @Test
    public void testDelete(){
        TestCuratorFramework ct=new TestCuratorFramework();
        try {
            ct.delete("/mynode1");
            ct.checkExist("/mynode1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        zkclient.close();
    }

    public static void main(String[] args)throws Exception {
        //TestCuratorTools ct=new  TestCuratorTools();
        //ct.getListChildren("/zk/bb");
        //ct.upload("/jianli/123.txt", "D:\\123.txt");
        //ct.createOrUpdate("/zk/cc334/zzz","c");
        //ct.delete("/qinb/bb");
        //ct.checkExist("/zk");
        //ct.read("/jianli/123.txt");
        //zkclient.close();
    }
}
