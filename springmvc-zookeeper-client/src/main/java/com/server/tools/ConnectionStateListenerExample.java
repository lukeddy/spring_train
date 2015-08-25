package com.server.tools;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 客户端监听与zookeeper连接状态测试
 */
public class ConnectionStateListenerExample {

    public static void main(String[] args) throws Exception {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, Integer.MAX_VALUE);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.253:2181,192.168.10.159:2181,192.168.1.254:2181", retryPolicy);
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("** STATE CHANGED TO : " + newState);
            }
        });
        client.start();
        client.getZookeeperClient().blockUntilConnectedOrTimedOut();

        Thread.sleep(Integer.MAX_VALUE);
    }

}