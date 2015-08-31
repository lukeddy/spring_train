package com.howtodoinjava.task;

import java.util.Date;

/**
 * No need to implement any interface
 * */
public class DemoTimerTask {
	
	//Define the method to be called as configured
	public void execute()
	{
		System.out.println("Executed task on :: " + new Date());
	}
}
