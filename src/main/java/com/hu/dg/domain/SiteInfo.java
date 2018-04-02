package com.hu.dg.domain;

/**
 * 网站信息
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteInfo.java 2016/07/03 21:43
 */
public class SiteInfo {

    private int id;
    private String name;//网站名称
    private String keywords;//网站meta 关键词
    private String description;//网站描述
    private String icon;//网站图标地址
    private String footer;//页脚
    private String url;//服务器域名地址
    private long time;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}