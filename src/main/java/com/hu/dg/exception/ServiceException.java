package com.hu.dg.exception;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ServiceException.java 2016/04/06 22:47
 */
public class ServiceException extends RuntimeException {

    private int exceptionCode;
    private String exceptionMessage;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.exceptionMessage= message;
    }

    public ServiceException(int exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}