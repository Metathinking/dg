package com.hu.dg.domain;

/**
 * 首页栏目
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) Column.java 2016/07/31 10:22
 */
public class Column {

    private int id;
    private String name;//名称
    private String url;//地址
    private String keywords;//关键词
    private int order;//排序

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}