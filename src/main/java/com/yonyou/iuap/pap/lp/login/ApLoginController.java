package com.yonyou.iuap.pap.lp.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.iuap.pap.lp.init.ContextProperties;
import com.yonyou.iuap.pap.lp.init.WebConstant;

@Controller
@RequestMapping(value = "/user")
public class ApLoginController{
	private Logger log = LoggerFactory.getLogger(ApLoginController.class);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(ContextProperties.getUserType().equals(WebConstant.USER_TYPE_YHT)){
			String logouturl = ContextProperties.getProperty("yhtlogoutUrl");
			if(StringUtils.isBlank(logouturl)){
				log.error("no config [yhtlogoutUrl] item");
				return ;
			}
			
			String yhtlogoutLocaltUrl = ContextProperties.getProperty("yhtlogoutLocaltUrl");
			String rURL = Base64.encodeBase64URLSafeString(logouturl.getBytes());
			response.sendRedirect(yhtlogoutLocaltUrl+rURL);
		}else{
			String logouturl = ContextProperties.getProperty("logoutUrl");
			response.sendRedirect(logouturl);
		}
	}
}