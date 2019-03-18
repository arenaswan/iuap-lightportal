//package com.yonyou.iuap.pap.lp.login;
//
//import iuap.portal.context.Platform;
//import iuap.portal.context.ViewProcessService;
//import iuap.portal.login.ILoginService;
//import iuap.portal.login.LoginException;
//import iuap.portal.model.User;
//import iuap.portal.web.BaseController;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.jasig.cas.client.authentication.AttributePrincipal;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.yonyou.iuap.context.InvocationInfoProxy;
//import com.yonyou.iuap.tenant.sdk.TenantCenter;
//import com.yonyou.iuap.utils.CookieUtil;
//import com.yonyou.uap.common.utils.LineSymbolFilterUtil;
//import com.yonyou.uap.fourA.yht.condition.UserYhtCondition;
//import com.yonyou.uap.i18n.entity.Description;
//import com.yonyou.uap.i18n.service.IDescriptionService;
//import com.yonyou.uap.user.entity.WBUser;
//import com.yonyou.uap.wb.common.BusinessException;
//import com.yonyou.uap.wb.utils.JsonResponse;
//
//@Controller
//@RequestMapping(value = "/platform/cas")
//@Conditional(CASLoginController.class)
//public class CASLoginController extends BaseController {
//
//	private final String USERTYPE = "userType";
//	private final String ERRORMSG = "errormsg";
//	private final String STATUS = "status";
//	private Logger log = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	private ILoginService loginService;
//	@Autowired
//	private ViewProcessService viewProcessService;
//
//	@Autowired
//	private IDescriptionService descriptionService;
//
//	
//	@RequestMapping(value = "/getAllTenant", method = RequestMethod.GET)
//	public @ResponseBody String getAllTenant(){
//		//获取租户信息
////		Map<String,String> resultMap = new HashMap<String,String>();
//		String tenantInfos = TenantCenter.findTenantsByUserId(InvocationInfoProxy.getUserid());
////		JSONObject jsonObject = JSONObject.parseObject(tenantInfos);
////		com.alibaba.fastjson.JSONArray tenantArray = (JSONArray) jsonObject.get(JsonResponse.DATA);
////		for (Object object : tenantArray) {
////			Map<String,Object> userMap = (Map<String, Object>)object;
////			if(MapUtils.isNotEmpty(userMap)){
////				resultMap.put("tenantId", String.valueOf(userMap.get("tenantId")));
////				resultMap.put("tenantName", String.valueOf(userMap.get("tenantName")));
////			}
////		}
//		return tenantInfos;
//	}
//
//	/**
//	 * @deprecated(since 3.0)
//	 * @param request
//	 * @param response
//	 */
//	@Deprecated
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public void logout(HttpServletRequest request, HttpServletResponse response) {
//		String userid = InvocationInfoProxy.getUserid();
//		if (StringUtils.isBlank(userid)){
//			userid = getUserId();
//		}
//		String token = CookieUtil.findCookieValue(request.getCookies(), "token");
//		javax.servlet.http.Cookie[] cookies = loginService.getLogoutCookie(userid, token);
//		addCookies(cookies);
//		response.setHeader("cache-control", "no-store");
//		try {
//			response.sendRedirect(Platform.get().getSystemProperty("yhtLogoutUrl"));
//		} catch (IOException e) {
//			log.error(e.getMessage(), e);
//		}
//	}
//	
//
//}
