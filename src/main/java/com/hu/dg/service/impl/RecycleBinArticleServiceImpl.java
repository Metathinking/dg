package com.hu.dg.service.impl;

import com.hu.dg.domain.Article;
import com.hu.dg.repository.ArticleAboutInfoRepository;
import com.hu.dg.repository.ArticleRepository;
import com.hu.dg.repository.RecycleBinArticleRepository;
import com.hu.dg.service.RecycleBinArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecycleBinArticleServiceImpl.java 2016/06/18 20:35
 */
@Service
public class RecycleBinArticleServiceImpl implements RecycleBinArticleService {

    @Autowired
    private RecycleBinArticleRepository recycleBinArticleRepository;

    @Autowired
    private ArticleAboutInfoRepository aboutInfoRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> list(Map<String, Object> params) {
        return recycleBinArticleRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return recycleBinArticleRepository.getCount(params);
    }

    public Article findById(int id) {
        return recycleBinArticleRepository.findById(id);
    }

    /**
     * 删除文章，同时删除相关信息
     * @param id
     * @param userId
     */
    public void delete(int id, int userId) {
        recycleBinArticleRepository.delete(id,userId);
        aboutInfoRepository.delete(id);
    }

    public void restore(int id, int userId) {
        Article dbArticle = recycleBinArticleRepository.findById(id);
        if(dbArticle==null){
            return;
        }
        if(dbArticle.getUserId()!=userId){
            return;
        }
        recycleBinArticleRepository.delete(dbArticle.getId(),userId);
        articleRepository.create(dbArticle);
    }
}