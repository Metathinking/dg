package com.hu.dg.service.impl;

import com.hu.dg.domain.CodeImage;
import com.hu.dg.repository.CodeImageRepository;
import com.hu.dg.service.CodeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CodeImageServiceImpl.java 2016/09/20 09:47
 */
@Service
public class CodeImageServiceImpl implements CodeImageService{

    @Autowired
    private CodeImageRepository codeImageRepository;

    public void create(CodeImage codeImage) {
        codeImageRepository.create(codeImage);
    }

    public CodeImage findById(int id) {
        return codeImageRepository.findById(id);
    }
}