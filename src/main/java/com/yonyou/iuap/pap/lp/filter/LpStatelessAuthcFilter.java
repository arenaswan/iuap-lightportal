package com.yonyou.iuap.pap.lp.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.web.util.WebUtils;

import com.yonyou.iuap.auth.shiro.StatelessAuthcFilter;

public class LpStatelessAuthcFilter extends StatelessAuthcFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		boolean flag = super.onAccessDenied(request, response);
		return flag;
	}

	@Override
	protected void redirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		HttpServletRequest hReq = (HttpServletRequest) request;
		String rURL = hReq.getRequestURI();
		String qryString = hReq.getQueryString();

		if ((qryString != null) && (!qryString.isEmpty())) {
			qryString = qryString + "?" + hReq.getQueryString();
		}

		rURL = Base64.encodeBase64URLSafeString(rURL.getBytes());

		String loginUrl = getLoginUrl() + "?r=" + rURL;
		WebUtils.issueRedirect(request, response, loginUrl);
	}

	protected void onAjaxAuthFail(ServletRequest request, ServletResponse resp)
			throws IOException {
		super.onAjaxAuthFail(request, resp);
		Cookie logints = new Cookie("u_logints", null);
		logints.setPath("/");
		HttpServletResponse response = (HttpServletResponse) resp;
		response.addCookie(logints);
	}
}