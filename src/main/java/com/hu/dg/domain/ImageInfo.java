package com.hu.dg.domain;

/**
 * 通过md5验证图片是否存在
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ImageInfo.java 2016/06/22 13:00
 */
public class ImageInfo {

    private int id;
    private String url;
    private String md5Code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }
}