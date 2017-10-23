package com.vkl.hblw.setting.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lh.ucenter.result.CUser;
import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;

/**
 * API拦截器，所有API均是对其他子系统提供，本系统内部不使用，API均要登入后带token
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ApiInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

/*		String ctxPath = request.getServletContext().getContextPath();
		String uri = request.getRequestURI();
		uri = uri.substring(ctxPath.length());
		if (uri.startsWith("/api/")) {
			log.info("Req Uri:" + uri);

			String token = request.getParameter("token");
			log.info("token:" + token);

			CUser cuser = null; 
			if (cuser != null) {
				request.setAttribute("TokenUser", cuser);
			}else {
				JsonResult result = new JsonResult();
				response.setContentType("text/html;charset=UTF-8");
				result.setCode(JsonResult.CODE_NOT_LOGIN);
				result.setInfo(new String("认证失败".getBytes("utf-8"), "utf-8"));
				response.getWriter().write(JsonUtil.toJson(result));
				return false;
			}

			return true;
		} else{
			return true;
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}