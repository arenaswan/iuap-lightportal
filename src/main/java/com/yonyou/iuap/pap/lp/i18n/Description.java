package com.yonyou.iuap.pap.lp.i18n;


import java.io.Serializable;

public class Description implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String prelocale;

    private Integer serialid;

    private String pageshow;

    private String description;

    private Integer enabled;

    private Integer i18nDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPrelocale() {
        return prelocale;
    }
    public void setPrelocale(String prelocale) {
        this.prelocale = prelocale;
    }
    public Integer getSerialid() {
        return serialid;
    }
    public void setSerialid(Integer serialid) {
        this.serialid = serialid;
    }
    public String getPageshow() {
        return pageshow;
    }
    public void setPageshow(String pageshow) {
        this.pageshow = pageshow;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getI18nDefault() {
        return i18nDefault;
    }

    public void setI18nDefault(Integer i18nDefault) {
        this.i18nDefault = i18nDefault;
    }
}
