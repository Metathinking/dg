package com.hu.dg.service.impl;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.ArticleAboutInfo;
import com.hu.dg.domain.User;
import com.hu.dg.domain.UserEffectArticle;
import com.hu.dg.repository.ArticleAboutInfoRepository;
import com.hu.dg.repository.ArticleRepository;
import com.hu.dg.repository.UserEffectArticleRepository;
import com.hu.dg.service.ArticleService;
import com.hu.dg.service.UserEffectArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserEffectArticleServiceImpl.java 2016/06/20 11:51
 */
@Service
public class UserEffectArticleServiceImpl implements UserEffectArticleService {

    @Autowired
    private UserEffectArticleRepository effectArticleRepository;

    @Autowired
    private ArticleAboutInfoRepository aboutInfoRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public Map<String, Object> changeEffect(int articleId, boolean effect, User user) {
        Map<String,Object> backMap = new HashMap<String, Object>();
        //文章不存在，或者是作者自己的文章，不进行操作
        Article article = articleRepository.findSimpleById(articleId);
        if(article==null||article.getUserId()==user.getId()){
            backMap.put("changed",false);
            return backMap;
        }
        if(effect){//支持该文章
            UserEffectArticle userEffectArticle = effectArticleRepository.find(user.getId(), articleId);
            //没有支持、反对过文章，创建userEffectArticle,更新 articleAboutInfo
            if(userEffectArticle==null){
                int maxId = effectArticleRepository.getMaxId();
                maxId++;
                userEffectArticle = new UserEffectArticle();
                userEffectArticle.setId(maxId);
                userEffectArticle.setUserId(user.getId());
                userEffectArticle.setArticleId(articleId);
                userEffectArticle.setEffect(true);
                effectArticleRepository.create(userEffectArticle);
                ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
                articleAboutInfo.setSupportCount(articleAboutInfo.getSupportCount()+1);
                aboutInfoRepository.update(articleAboutInfo);
                backMap.put("changed",true);
                backMap.put("articleAboutInfo",articleAboutInfo);
                return backMap;
            }else{
                //支持过该文章，不在更改
                if(userEffectArticle.isEffect()){
                    backMap.put("changed",false);
                    return backMap;
                }else{//反对过该文章，更新userEffectArticle, 更新articleAboutInfo
                    userEffectArticle.setEffect(true);
                    effectArticleRepository.update(userEffectArticle);
                    ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
                    articleAboutInfo.setSupportCount(articleAboutInfo.getSupportCount()+1);
                    if(articleAboutInfo.getOpposeCount()>0){
                        articleAboutInfo.setOpposeCount(articleAboutInfo.getOpposeCount()-1);
                    }
                    aboutInfoRepository.update(articleAboutInfo);
                    backMap.put("changed",true);
                    backMap.put("articleAboutInfo",articleAboutInfo);
                    return backMap;
                }
            }
        }else{//反对该文章
            UserEffectArticle userEffectArticle = effectArticleRepository.find(user.getId(), articleId);
            //没有支持、反对过文章，创建userEffectArticle,更新 articleAboutInfo
            if(userEffectArticle==null){
                int maxId = effectArticleRepository.getMaxId();
                maxId++;
                userEffectArticle = new UserEffectArticle();
                userEffectArticle.setId(maxId);
                userEffectArticle.setUserId(user.getId());
                userEffectArticle.setArticleId(articleId);
                userEffectArticle.setEffect(false);
                effectArticleRepository.create(userEffectArticle);
                ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
                articleAboutInfo.setOpposeCount(articleAboutInfo.getOpposeCount()+1);
                aboutInfoRepository.update(articleAboutInfo);
                backMap.put("changed",true);
                backMap.put("articleAboutInfo",articleAboutInfo);
                return backMap;
            }else{
                //反对过该文章，不在更改
                if(!userEffectArticle.isEffect()){
                    backMap.put("changed",false);
                    return backMap;
                }else{//支持过该文章，更新userEffectArticle, 更新articleAboutInfo
                    userEffectArticle.setEffect(false);
                    effectArticleRepository.update(userEffectArticle);
                    ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
                    if(articleAboutInfo.getSupportCount()>0){
                        articleAboutInfo.setSupportCount(articleAboutInfo.getSupportCount()-1);
                    }
                    articleAboutInfo.setOpposeCount(articleAboutInfo.getOpposeCount()+1);
                    aboutInfoRepository.update(articleAboutInfo);
                    backMap.put("changed",true);
                    backMap.put("articleAboutInfo",articleAboutInfo);
                    return backMap;
                }
            }

        }
    }
}