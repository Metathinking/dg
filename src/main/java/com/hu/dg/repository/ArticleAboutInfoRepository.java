package com.hu.dg.repository;

import com.hu.dg.domain.ArticleAboutInfo;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleAboutInfoRepository.java 2016/06/19 19:26
 */
@Repository
public interface ArticleAboutInfoRepository {

    void create(ArticleAboutInfo articleAboutInfo);

    void update(ArticleAboutInfo articleAboutInfo);

    void delete(int articleId);

    ArticleAboutInfo findById(int articleId);
}