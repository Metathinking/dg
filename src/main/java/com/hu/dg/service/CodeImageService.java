package com.hu.dg.service;

import com.hu.dg.domain.CodeImage;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CodeImageService.java 2016/09/20 09:46
 */
public interface CodeImageService {

    void create(CodeImage codeImage);

    CodeImage findById(int id);
}