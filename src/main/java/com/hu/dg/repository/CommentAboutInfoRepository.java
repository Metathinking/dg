package com.hu.dg.repository;

import com.hu.dg.domain.CommentAboutInfo;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentAboutInfoRepository.java 2016/06/24 11:02
 */
@Repository
public interface CommentAboutInfoRepository {

    void create(CommentAboutInfo commentAboutInfo);

    void update(CommentAboutInfo commentAboutInfo);

    void delete(int id);

    CommentAboutInfo findById(int id);
}