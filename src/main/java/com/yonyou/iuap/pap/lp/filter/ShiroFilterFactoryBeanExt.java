package com.yonyou.iuap.pap.lp.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;

import com.yonyou.iuap.pap.lp.init.ContextProperties;
import com.yonyou.iuap.pap.lp.init.WebConstant;

/**
 * Created by Administrator on 2018/8/1.
 */
public class ShiroFilterFactoryBeanExt extends ShiroFilterFactoryBean {

    private String yhtLoginUrl;

    private String localLoginUrl;

    private Map<String, String> localFilterChainDefinitionMap = new LinkedHashMap<String, String>();

    private Map<String, String> yhtFilterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Override
    public String getLoginUrl() {
        String userType= ContextProperties.getUserType();
        if(WebConstant.USER_TYPE_YHT.equalsIgnoreCase(userType)){
            return this.getYhtLoginUrl();
        }else{
            return this.getLocalLoginUrl();
        }
    }

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> filterMappings = new LinkedHashMap<>();

        Map<String, String> xmlConfigMap = super.getFilterChainDefinitionMap();
        Map<String, String> localMap = this.getLocalFilterChainDefinitionMap();
        Map<String, String> yhtMap = this.getYhtFilterChainDefinitionMap();
        String userType= ContextProperties.getUserType();
        if(WebConstant.USER_TYPE_YHT.equalsIgnoreCase(userType)){
            if(yhtMap!=null && yhtMap.size()>0){
                filterMappings.putAll(yhtMap);

            }
        }else{
            if(localMap!=null && localMap.size()>0){
                filterMappings.putAll(localMap);
            }
        }
        filterMappings.putAll(xmlConfigMap);
        return filterMappings;
    }

    public String getYhtLoginUrl() {
        return yhtLoginUrl;
    }

    public void setYhtLoginUrl(String yhtLoginUrl) {
        this.yhtLoginUrl = yhtLoginUrl;
    }

    public String getLocalLoginUrl() {
        return localLoginUrl;
    }

    public void setLocalLoginUrl(String localLoginUrl) {
        this.localLoginUrl = localLoginUrl;
    }

    public Map<String, String> getLocalFilterChainDefinitionMap() {
        return localFilterChainDefinitionMap;
    }

    public void setLocalFilterChainDefinitionMap(Map<String, String> localFilterChainDefinitionMap) {
        this.localFilterChainDefinitionMap = localFilterChainDefinitionMap;
    }

    public Map<String, String> getYhtFilterChainDefinitionMap() {
        return yhtFilterChainDefinitionMap;
    }

    public void setYhtFilterChainDefinitionMap(Map<String, String> yhtFilterChainDefinitionMap) {
        this.yhtFilterChainDefinitionMap = yhtFilterChainDefinitionMap;
    }

    public void setLocalFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if(CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        this.setLocalFilterChainDefinitionMap(section);
    }

    public void setYhtFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if(CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        this.setYhtFilterChainDefinitionMap(section);
    }
}
