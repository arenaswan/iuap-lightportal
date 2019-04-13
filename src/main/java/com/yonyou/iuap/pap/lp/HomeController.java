package com.yonyou.iuap.pap.lp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.iuap.pap.lp.init.ContextProperties;

@Controller
public class HomeController
{
    @RequestMapping(value ="/", method =RequestMethod.GET)
    public String home(Model model)
    {        
    	String homeurl = ContextProperties.getProperty("homeUrl");
    	return "redirect:" + homeurl;
    }
}
