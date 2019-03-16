package com.yonyou.iuap.pap.lp.i18n;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.iuap.pap.lp.JsonResponse;
import com.yonyou.iuap.pap.lp.init.ContextProperties;
import com.yonyou.iuap.utils.HttpUtil;

@Controller
@RequestMapping(value = "/i18n/classification")
public class LanguageController {

    private static final Logger log = LoggerFactory.getLogger(LanguageController.class);
    private final String SUCCESS = "success";


    private String MSG1 = "未查询到任何信息！";
    private String MSG2 = "服务异常，请稍后重试！";
    private String MSG3 = "服务异常：";

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAll(HttpServletRequest request, HttpServletResponse response) {
        JsonResponse results = new JsonResponse();
        String serurl = ContextProperties.getProperty("workbench.base.url")+"/i18n/classification/list";
        try {
        	String resdata = HttpUtil.getInstance().doGetForString(serurl);
        	if(StringUtils.isBlank(resdata)){
        		  results.failed(serurl+" return data is null");
                  return results;
        	}
        		
            List<Description> desList = JSONArray.parseArray(resdata, Description.class);
            if (desList != null && !desList.isEmpty()) {
                int defaultSerial = 1; // 默认语种序号
                for(Description desc : desList){
                    if(desc != null && desc.getI18nDefault() != null && desc.getI18nDefault().intValue() == 1){ // 1=true
                        defaultSerial = desc.getSerialid();
                        break;
                    }
                }

                Cookie default_serial =null;
                default_serial = new Cookie("default_serial", String.valueOf(defaultSerial));
                default_serial.setPath("/");
                default_serial.setMaxAge(7 * 24 * 3600 * 1000);
                response.addCookie(default_serial);

                results.success(SUCCESS, "data", desList);


            } else {
                results.failed(MSG1);
                return results;
            }
        } catch (Exception e) {
            results.failed(MSG2);
            log.error(MSG3, e);
        }

        return results;
    }
}

