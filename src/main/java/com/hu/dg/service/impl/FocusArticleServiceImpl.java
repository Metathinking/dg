package com.hu.dg.service.impl;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.ArticleAboutInfo;
import com.hu.dg.domain.FocusArticle;
import com.hu.dg.domain.User;
import com.hu.dg.repository.ArticleAboutInfoRepository;
import com.hu.dg.repository.ArticleRepository;
import com.hu.dg.repository.FocusArticleRepository;
import com.hu.dg.service.FocusArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleServiceImpl.java 2016/06/19 20:42
 */
@Service
public class FocusArticleServiceImpl implements FocusArticleService {

    @Autowired
    private FocusArticleRepository focusArticleRepository;

    @Autowired
    private ArticleAboutInfoRepository aboutInfoRepository;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     *
     * @param articleId
     * @param focus true,收藏,false,取消收藏
     * @param user
     * @return
     */
    public Map<String,Object> changeFocus(int articleId, boolean focus, User user) {
        Map<String,Object> backMap = new HashMap<String, Object>();
        //文章不存在，或者是作者自己的文章，不进行收藏
        Article article = articleRepository.findSimpleById(articleId);
        if(article==null||article.getUserId()==user.getId()){
            backMap.put("changed",false);
            return backMap;
        }
        if(focus){//添加关注
            FocusArticle focusArticle = focusArticleRepository.find(user.getId(), articleId);
            if(focusArticle!=null){
                backMap.put("changed",false);
                return backMap;
            }
            focusArticle = new FocusArticle();
            int maxId = focusArticleRepository.getMaxId();
            maxId++;
            focusArticle.setId(maxId);
            focusArticle.setArticleId(articleId);
            focusArticle.setUserId(user.getId());
            focusArticleRepository.create(focusArticle);
            ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
            articleAboutInfo.setFocusCount(articleAboutInfo.getFocusCount()+1);
            aboutInfoRepository.update(articleAboutInfo);
            backMap.put("changed",true);
            backMap.put("articleAboutInfo",articleAboutInfo);
            backMap.put("focusArticle",focusArticle);
            return backMap;
        }else{//取消关注
            FocusArticle focusArticle = focusArticleRepository.find(user.getId(), articleId);
            if(focusArticle==null){
                backMap.put("changed",false);
                return backMap;
            }
            focusArticleRepository.delete(focusArticle.getId());
            ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
            if(articleAboutInfo.getFocusCount()==0){
                backMap.put("changed",true);
                backMap.put("articleAboutInfo",articleAboutInfo);
                backMap.put("focusArticle",null);
                return backMap;
            }
            articleAboutInfo.setFocusCount(articleAboutInfo.getFocusCount()-1);
            aboutInfoRepository.update(articleAboutInfo);
            backMap.put("changed",true);
            backMap.put("articleAboutInfo",articleAboutInfo);
            backMap.put("focusArticle",null);
            return backMap;
        }
    }


    public List<Article> list(Map<String, Object> params) {
        return focusArticleRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return focusArticleRepository.getCount(params);
    }
}