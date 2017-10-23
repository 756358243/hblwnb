package com.vkl.hblw.setting.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.velocity.app.FieldMethodizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vkl.common.util.StrUtil;
import com.vkl.common.util.Util;
import com.vkl.hblw.setting.config.APIDataConfig;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(CommonInterceptor.class);

	@Autowired
	private Util util;
	@Autowired
	private StrUtil strUtil;
	@Autowired
	private APIDataConfig apiDataConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("util") == null) {
			session.setAttribute("util", util);
		}
		if (session.getAttribute("strUtil") == null) {
			session.setAttribute("strUtil", strUtil);
		}
		if (session.getAttribute("APIDataConfig") == null) {
			session.setAttribute("apiDataConfig", apiDataConfig);
		}
		 
		String ctxPath = request.getServletContext().getContextPath();
		if (session.getAttribute("ctxPath") == null) {
			session.setAttribute("ctxPath", ctxPath);
		}
		String uri = request.getRequestURI();
		session.setAttribute("requestUri", uri);
		int CopyrightYear = StrUtil.getYear(new Date());
		session.setAttribute("CrtYear", CopyrightYear);
		log.info("Req Uri:" + uri);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}