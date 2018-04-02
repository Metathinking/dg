package com.hu.dg.util;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CodeUtil.java 2016/06/16 10:53
 */
public class CodeUtil {

    public static String getRandomCode(){
        int code = (int) (Math.random() * 9*100000)+100000;
        return code+"";
    }

    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            System.out.println(getRandomCode());
        }
    }
}