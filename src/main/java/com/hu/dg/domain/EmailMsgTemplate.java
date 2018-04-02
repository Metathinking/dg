package com.hu.dg.domain;

/**
 * 邮件信息模板
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgTemplate.java 2016/06/15 08:44
 */
public class EmailMsgTemplate {

    private int id;
    /**
     * @see com.hu.dg.type.EmailMsgType
     */
    private String type;//类型
    private String subject;//标题
    private String description;//描述
    private String content;//内容
    private boolean use;//默认设置
    private long time;//创建时间

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}