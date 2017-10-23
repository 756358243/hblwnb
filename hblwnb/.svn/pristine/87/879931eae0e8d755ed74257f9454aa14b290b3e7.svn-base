package com.vkl.hblw.setting.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.json.NotLoginResult;
import com.vkl.hblw.common.bean.user.UserSessionBean;



public class UserLoginProtectInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(UserLoginProtectInterceptor.class);

	/*@Autowired
	private UserService userService;*/ 
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String ctxPath = request.getServletContext().getContextPath();
		HttpSession session = request.getSession();

		boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));

		String uri = request.getRequestURI();
		uri = uri.substring(ctxPath.length());

		if (uri.startsWith("/user/")) {
			String[] notProtectedUri = { "/user/login.do", "/logoff.do", "/user/register.do", "/user/doLogin.do",
					"/user/front/itemList.do", "/user/front/itemDetail.do", "/user/front/zhinan.do" };

			for (String s : notProtectedUri) {
				if (s.equalsIgnoreCase(uri)) {
					return true;
				} 
			}
			
			UserSessionBean user = (UserSessionBean) session.getAttribute(UserSessionBean.SESSION_USER_KEY);
			// 验证sesstion
			if (user != null) {
				return true;
			} else {
				log.info("UserSessionBean is null");
				if (isAjax) {
					JsonResult result = new NotLoginResult();
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(JsonUtil.toJson(result));
				} else {
					String queryString = request.getQueryString();
					String url = request.getRequestURL().toString();
					if (!StringUtils.isEmpty(queryString))
						url += "?" + queryString;
					response.sendRedirect(ctxPath + "?url=" + url.toString());
				}
				return false;
			}
		} else {
			return true;
		}
		
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