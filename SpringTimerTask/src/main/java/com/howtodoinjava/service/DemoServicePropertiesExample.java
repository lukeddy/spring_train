package com.howtodoinjava.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

public class DemoServicePropertiesExample {
	
	@Scheduled(cron = "${cron.expression}")
	public void demoServiceMethod()
	{
		System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
	}

}
