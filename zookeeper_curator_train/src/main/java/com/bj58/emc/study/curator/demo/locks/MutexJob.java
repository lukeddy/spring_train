package com.bj58.emc.study.curator.demo.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

/**
 * 互斥任务
 * 
 * @author shencl
 */
public class MutexJob implements Runnable {

	private final String name;

	private final InterProcessLock lock;

	// 锁等待时间
	private final int wait_time = 10;

	MutexJob(String name, InterProcessLock lock) {
		this.name = name;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			doWork();
		} catch (Exception e) {
			// ingore;
		}
	}

	public void doWork() throws Exception {
		try {
			if (!lock.acquire(wait_time, TimeUnit.SECONDS)) {
				System.err.println(name + "等待" + wait_time + "秒，仍未能获取到lock,准备放弃。");
			}
			// 模拟job执行时间0-2000毫秒
			int exeTime = new Random().nextInt(2000);
			System.out.println(name + "开始执行,预计执行时间= " + exeTime + "毫秒----------");
			Thread.sleep(exeTime);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.release();
		}
	}
}
