package com.hu.dg.repository;

import com.hu.dg.domain.CodeImage;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CodeImageRepository.java 2016/09/20 09:43
 */
@Repository
public interface CodeImageRepository {

    void create(CodeImage codeImage);

    CodeImage findById(int id);
}