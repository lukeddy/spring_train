package com.bj58.emc.study.curator.demo.queue;

import java.nio.charset.Charset;

import org.apache.curator.framework.recipes.queue.QueueSerializer;

public class StringQueueSerializer implements QueueSerializer<String> {
	private static final Charset charset = Charset.forName("utf-8");

	@Override
	public byte[] serialize(String item) {
		return item.getBytes(charset);
	}

	@Override
	public String deserialize(byte[] bytes) {
		return new String(bytes, charset);
	}
}
