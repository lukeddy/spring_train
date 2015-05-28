package com.tangzq.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@Controller
public class ExceptionController {
    public static final Log log = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value={IOException.class,SQLException.class})
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("exception:"+e.getMessage(),e);
        return "error/500";
    }
}
