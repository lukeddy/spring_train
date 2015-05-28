package com.tangzq.controllers;

import com.tangzq.vo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/controller")
public class HomeController{

	/**
	 * 普通页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/test")
	public String test(ModelMap model){
		model.addAttribute("hello","Hello Spring");
		return "test";
	}

	/**
	 * JSON,XML,PDF,Excel输出
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(ModelMap model) {
		model.addAttribute("booksList",createBookList());
		return new ModelAndView("bookView",model);
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
