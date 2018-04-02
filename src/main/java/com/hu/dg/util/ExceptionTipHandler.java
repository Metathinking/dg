package com.hu.dg.util;

import com.hu.dg.exception.ServiceException;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ExceptionTipHandler.java 2016/06/08 13:57
 */
public class ExceptionTipHandler {

    public static Tip handler(Exception ex){
        if(ex instanceof ServiceException){
            ServiceException serviceException = (ServiceException) ex;
            return new Tip(false,serviceException.getExceptionCode(),serviceException.getExceptionMessage(),null);
        }else{
            // TODO: 2016/6/14.0014
            ex.printStackTrace();
            return new Tip(false,300,ex.getMessage(),null);
        }
    }
}