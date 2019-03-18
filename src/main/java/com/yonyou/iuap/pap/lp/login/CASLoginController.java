package com.yonyou.iuap.pap.lp.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.iuap.pap.lp.condition.UserYhtCondition;
import com.yonyou.iuap.pap.lp.init.ContextProperties;

@Controller
@RequestMapping(value = "/platform/cas")
@Conditional(UserYhtCondition.class)
public class CASLoginController  {

	private Logger log = LoggerFactory.getLogger(CASLoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String logouturl = ContextProperties.getProperty("yhtLoginLocalUrl","/wbalone/platform/cas/login?service=/iuap-lightportal-fe/index.html#/");
		response.sendRedirect(logouturl);
	}
}
