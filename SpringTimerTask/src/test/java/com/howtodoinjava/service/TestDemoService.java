package com.howtodoinjava.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemoService {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:schedule-basicUsageCron-example.xml");
		new ClassPathXmlApplicationContext("classpath:schedule-basicUsageFixedDelay-example.xml");
		new ClassPathXmlApplicationContext("classpath:schedule-xmlConfig-example.xml");
		new ClassPathXmlApplicationContext("classpath:schedule-PropertiesConfig-example.xml");
	}	
}
