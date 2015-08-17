package com.tangzq.controller;

import com.tangzq.model.Book;
import com.tangzq.view.ExcelView;
import com.tangzq.view.PdfView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用POI技术导出Excel,PDF文档控制器
 */
@Controller
@RequestMapping(value="/front")
public class FrontController {

    /**
     * 跳转到poi测试页面
     * @return
     */
    @RequestMapping(value="/test")
    public String frontTestPage(
         ModelMap model
    ){
        model.addAttribute("mewho","I am Mr.Tang");
        return "front_test";
    }

}
