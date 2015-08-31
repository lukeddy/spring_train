package com.howtodoinjava.task;

import java.util.Date;
import java.util.TimerTask;

public class DemoTimerTask2 extends TimerTask {
	public void run() {
		System.out.println("DemoTimerTask2 running at: "
				+ new Date(this.scheduledExecutionTime()));
	}
}
