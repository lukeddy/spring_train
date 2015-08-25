package com.bj58.emc.study.curator.demo.framework;

import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.utils.CloseableUtils;

import com.bj58.emc.study.curator.demo.utils.ClientFactory;

/**
 * 事务操作
 * 
 * @author shencl
 */
public class TransactionExamples {
	private static CuratorFramework client = ClientFactory.newClient();

	public static void main(String[] args) {
		try {
			client.start();
			// 开启事务
			CuratorTransaction transaction = client.inTransaction();

			Collection<CuratorTransactionResult> results = transaction.create()
					.forPath("/a/path", "some data".getBytes()).and().setData()
					.forPath("/another/path", "other data".getBytes()).and().delete().forPath("/yet/another/path")
					.and().commit();

			for (CuratorTransactionResult result : results) {
				System.out.println(result.getForPath() + " - " + result.getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放客户端连接
			CloseableUtils.closeQuietly(client);
		}

	}
}