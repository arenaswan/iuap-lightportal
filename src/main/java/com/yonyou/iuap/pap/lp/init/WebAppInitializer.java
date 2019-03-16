package com.yonyou.iuap.pap.lp.init;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.yonyou.yht.web.cas.AuthenticationFilter2;
import com.yonyou.yht.web.cas.ProxyReceivingTicketValidationFilter;
import com.yonyou.yht.web.cas.sso.SingleSignOutFilter;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private String URL1 = "/platform/cas/*";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return null;
    }


    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        // 系统启动时注册Listener
        servletContext.addListener(ContextLoaderListener.class);
        // 系统启动时注册filter
        
        String userType= ContextProperties.getUserType();
        
        if(WebConstant.USER_TYPE_YHT.equalsIgnoreCase(userType)){
            FilterRegistration casSingleSignOutFilter = servletContext.addFilter("CAS Single Sign Out Filter", SingleSignOutFilter.class);
            casSingleSignOutFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, "/*");

            FilterRegistration authenticationFilter2 = servletContext.addFilter("CAS Authentication Filter", AuthenticationFilter2.class);
            authenticationFilter2.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, URL1);

            FilterRegistration proxyReceivingTicketValidationFilter = servletContext.addFilter("CAS Validation Filter", ProxyReceivingTicketValidationFilter.class);
            proxyReceivingTicketValidationFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, URL1);
            proxyReceivingTicketValidationFilter.setInitParameter("encoding", "UTF-8");

            FilterRegistration httpServletRequestWrapperFilter = servletContext.addFilter("CAS HttpServletRequest Wrapper Filter", HttpServletRequestWrapperFilter.class);
            httpServletRequestWrapperFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, URL1);

            FilterRegistration assertionThreadLocalFilter = servletContext.addFilter("CAS Assertion Thread Local Filter", AssertionThreadLocalFilter.class);
            assertionThreadLocalFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, URL1);

        }

        FilterRegistration shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, "/*");
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");

        FilterRegistration encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding","UTF-8");
        encodingFilter.setInitParameter("forceEncoding","true");
        encodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class) , true, "/*");

        super.onStartup(servletContext);
    }


}