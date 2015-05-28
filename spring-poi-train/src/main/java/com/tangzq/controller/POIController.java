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
@RequestMapping(value="/poi")
public class POIController {

    /**
     * 跳转到poi测试页面
     * @return
     */
    @RequestMapping(value="/")
    public String poiIndexPage(){
        return "poi_test";
    }

    /**
     * 导出Excel
     * @return
     */
    @RequestMapping(value="/excel")
    public ModelAndView exportExcel(ModelMap model){
        model.addAttribute("bookList",createBookList());
        return new ModelAndView(new ExcelView(),model);
    }

    /**
     * 导出PDF
     * @return
     */
    @RequestMapping(value="/pdf")
    public ModelAndView exportPdf(ModelMap model){
        model.addAttribute("bookList",createBookList());
        return new ModelAndView(new PdfView(),model);
    }

    /**
     * 创建数据对象
     * @return
     */
    private List<Book> createBookList(){
        List<Book> listBooks = new ArrayList<Book>();
        listBooks.add(new Book("Effective Java", "Joshua Bloch", "0321356683","May 28, 2008", 38.11F));
        listBooks.add(new Book("Head First Java", "Kathy Sierra & Bert Bates","0596009208", "February 9, 2005", 30.80F));
        listBooks.add(new Book("Java Generics and Collections", "Philip Wadler", "0596527756", "Oct 24, 2006", 29.52F));
        listBooks.add(new Book("Thinking in Java", "Bruce Eckel", "0596527756","February 20, 2006", 43.97F));
        listBooks.add(new Book("Spring in Action", "Craig Walls", "1935182358","June 29, 2011", 31.98F));
        return  listBooks;
    }
}
