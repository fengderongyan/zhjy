package com.ruoyi.framework.interceptor.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.RoleBlockedException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.LoginService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 描述：web端拦截设置
 * @author yanbs
 * @Date 2019-08-30
 */
@Component
public class WebUrlInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private LoginService loginService;
	
	private static final Logger log = LoggerFactory.getLogger(WebUrlInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURL().toString();
		if(!"".equals(Constants.MOBILE_URL) && StringUtils.isContains(url, Constants.MOBILE_URL.split(","))){//不拦截的移动端路径
			return true;
		}
//		if(url.indexOf("/web/webIndex") > -1){
//        	Session session  = SecurityUtils.getSubject().getSession();
//        	String webUser = (String)session.getAttribute("webUser");
//        	if(webUser == null){
//        		//session.setAttribute("webUser", "test");
//        		//System.out.println("session:设置");
//				response.sendRedirect(request.getContextPath() + "/web/webLogin");
//        	}else{
//        		System.out.println("session:获取" + session.getAttribute("webUser"));
//        		session.removeAttribute("webUser");
//        	}
//	            
//		}
		
		return true;
	}
}
