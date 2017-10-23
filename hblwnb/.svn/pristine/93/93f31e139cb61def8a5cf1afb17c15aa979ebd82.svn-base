/**
 * 
 */
package com.vkl.hblw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xcc
 *
 */
@Controller
public class IndexController {

	
	/**
	 * 首页页面
	 * 
	 */
	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest req) throws Exception {
		return "/user/login";
	}
	/**
	 * 错误页面
	 * 
	 */
	@RequestMapping(value = "/error")
	public String error(Model model, HttpServletRequest req) throws Exception {
		return "/error";
	}

	/**
	 * 页面错误
	 * 
	 * 
	 */
	@RequestMapping(value = "/pageError")
	public String pageError(Model model, HttpServletRequest req) throws Exception {
		model.addAttribute("message", "错误提示信息");
		return "/page_error";
	}

	/**
	 * 页面不存在
	 * 
	 * 
	 */
	@RequestMapping(value = "/show404")
	public String show404() throws Exception {
		return "/404";
	}

	/**
	 * 服务器端错误页面
	 * 
	 *  
	 */
	@RequestMapping(value = "/show500")
	public String show500() throws Exception {
		return "/500";
	}
}
