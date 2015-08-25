package com.bj58.emc.study.curator.demo.atomic;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;

import com.bj58.emc.study.curator.demo.utils.ClientFactory;
import com.google.common.collect.Lists;

/**
 * 这个类我没能运行出期待的结果来，谁检查一下我的代码？
 * 
 * @author shencl
 */
public class DistributedAtomicIntegerExample {
	private static CuratorFramework client = ClientFactory.newClient();
	private static final String PATH = "/counter";
	public static DistributedAtomicInteger counter;

	static {
		client.start();
		counter = new DistributedAtomicInteger(client, PATH, new RetryNTimes(3, 1000));
	}

	public static void main(String[] args) {
		try {
			List<Thread> jobs = Lists.newArrayList();
			// 开1k个线程，不用同步机制，同时启动
			for (int i = 0; i < 1000; i++) {
				jobs.add(new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							counter.increment();
						} catch (Exception e) {
						}
					}
				}));

			}

			for (Thread t : jobs) {
				t.start();
			}

			// 保证线程全部执行完毕
			Thread.sleep(10000);

			System.out.println("计数器最终的值=" + counter.get().postValue());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}
}
