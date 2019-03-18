package com.yonyou.iuap.pap.lp.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.iuap.pap.lp.init.ContextProperties;

@Controller
@RequestMapping(value = "/user")
public class ApLoginController{
	private Logger log = LoggerFactory.getLogger(ApLoginController.class);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String logouturl = ContextProperties.getProperty("logoutUrl","/wbalone/account/logout?service=/lightportal");
		response.sendRedirect(logouturl);
	}
}