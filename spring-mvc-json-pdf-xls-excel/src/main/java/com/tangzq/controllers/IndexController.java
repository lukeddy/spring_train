package com.tangzq.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IndexController extends ExceptionController {


	/**
	 * 返回到首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value="/exceptionTest")
	public String exceptionTest() throws IOException {
		throw new IOException("IO异常抛出");
	}

}
