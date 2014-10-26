package com.tzq.common.exception;

import org.apache.log4j.Logger;

public class ServiceException extends Exception{

	private static final long serialVersionUID = -71434260745841874L;
	
	private Logger log = null;

	@SuppressWarnings("unchecked")
	public ServiceException(Class clazz, String message) {
		super(message);
		log = Logger.getLogger(clazz);
		log.error(message);
	}
	
	@SuppressWarnings("unchecked")
	public ServiceException(Class clazz, Throwable throwable) {
		super(throwable);
		log = Logger.getLogger(clazz);
		log.error(throwable.getMessage());
	}

}
