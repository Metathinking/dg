package com.hu.dg.cache;

import com.hu.dg.domain.Article;
import com.hu.dg.service.ArticleService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleCache.java 2016/07/30 14:43
 */
@Component
public class ArticleCache {

    private static Map<String,Object> map = new HashMap<String, Object>();

    @Autowired
    private ArticleService articleService;

    private static ArticleService staticArticleService;

    @PostConstruct
    public void init(){
        staticArticleService = this.articleService;
    }

    public static final String HOT_ARTICLE = "HOT_ARTICLE";
    public static final String NEW_ARTICLE="NEW_ARTICLE";

//    public static List<Article> getHotArticleList(){
//        Object object = map.get(HOT_ARTICLE);
//        if(object==null){
//            List<Article> hotArticleList = staticArticleService.listHotArticle();
//            map.put(HOT_ARTICLE,hotArticleList);
//            return hotArticleList;
//        }
//        return (List<Article>) object;
//    }
//
//    public static List<Article> getNewArticleList(){
//        Object object = map.get(NEW_ARTICLE);
//        if(object==null){
//            List<Article> newArticleList = staticArticleService.listNewArticle();
//            map.put(NEW_ARTICLE,newArticleList);
//            return newArticleList;
//        }
//        return (List<Article>) object;
//    }
}