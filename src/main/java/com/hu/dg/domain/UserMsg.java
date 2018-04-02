package com.hu.dg.domain;

/**
 * 用户信息
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserMsg.java 2016/06/06 09:35
 */
public class UserMsg {

    private int id;
    private String icon;//头像链接
    private String sex;
    private String introduction;//自我介绍 512

    private double height;//身高
    private double weight;//体重
    private double bust;//胸围bust
    private double waist;//腰围waist
    private double hips;//臀围hips

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBust() {
        return bust;
    }

    public void setBust(double bust) {
        this.bust = bust;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }
}