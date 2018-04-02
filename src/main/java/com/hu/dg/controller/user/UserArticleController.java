package com.hu.dg.controller.user;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;
import com.hu.dg.query.ArticlePageQuery;
import com.hu.dg.service.ArticleService;
import com.hu.dg.service.UserEffectArticleService;
import com.hu.dg.type.ArticleCategory;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleController.java 2016/06/17 10:11
 */
@Controller
@RequestMapping("user")
public class UserArticleController {

    @Autowired
    private ArticleService articleService;


    @Autowired
    private UserEffectArticleService userEffectArticleService;

    @RequestMapping(value = "articleList", method = RequestMethod.GET)
    public String gotoArticleList(@RequestParam(value = "index",required = false) Integer index,Model model) {
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "user/article-list";
    }

    @RequestMapping(value = "articleList", method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody ArticlePageQuery query, HttpSession session) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            User user = (User) session.getAttribute("USER");
            map.put("userId", user.getId());
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            List<Article> list = articleService.list(map);
            for (Article article : list) {
                article.setCategory(ArticleCategory.valueOf(article.getCategory()).getDescription());
            }
            int count = articleService.getCount(map);
            query.setCount(count);
            Map<String, Object> backMap = new HashMap<String, Object>();
            backMap.put("list", list);
            backMap.put("pageQuery", query);
            return new Tip(true, 100, "查询成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "articleEdit", method = RequestMethod.GET)
    public String gotoArticleEdit(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            id = -1;
        }else{
            Article article = articleService.findById(id);
            model.addAttribute("article",article);
        }
        model.addAttribute("id", id);

        return "user/article-edit";
    }

    @RequestMapping(value = "articleEdit", method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody Article article, HttpSession session) {
        try {
            if (StringUtils.isEmpty(article.getTitle())) {
                return new Tip(false, 101, "文章标题不能为空", null);
            }
            if (article.getTitle().length() < 6 || article.getTitle().length() > 128) {
                return new Tip(false, 102, "文章标题长度在6——128个字符之间", null);
            }
            if(StringUtils.isEmpty(article.getCategory())){
                return new Tip(false,103,"请选择文章分类",null);
            }
            if(article.getCategory()==ArticleCategory.REPRINT.name()&&StringUtils.isEmpty(article.getOriginalUrl())){
                return new Tip(false,104,"您的文章为转载，请填写原文地址",null);
            }
            if(StringUtils.isEmpty(article.getContent())){
                return new Tip(false,105,"请填写文章内容",null);
            }
            if(StringUtils.isEmpty(article.getImage())){
                return new Tip(false,106,"请上传并选择文章的标题图片",null);
            }
            if(StringUtils.isEmpty(article.getTag())){
                return new Tip(false,107,"请填写文章标签，将被用于搜索引擎的搜索使用",null);
            }
            if(StringUtils.isEmpty(article.getDescription())){
                return new Tip(false,108,"请填写文章的简介，可在文章中摘录一段文字",null);
            }
            User user = (User) session.getAttribute("USER");
            article.setUserId(user.getId());
            article.setUserNickname(user.getNickname());
            articleService.edit(article);
            return new Tip(true, 100, "文章保存成功", null);
        } catch (Exception ex) {
            return ExceptionTipHandler.handler(ex);
        }
    }

    @RequestMapping(value = "getCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public Tip getCategoryList() {
        ArticleCategory[] values = ArticleCategory.values();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (ArticleCategory type : values) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", type.name());
            map.put("description", type.getDescription());
            list.add(map);
        }
        return new Tip(true, 100, "查询成功", list);
    }

    @RequestMapping(value = "changeOpen", method = RequestMethod.POST)
    @ResponseBody
    public Tip changeOpen(@RequestBody Article article, HttpSession session) {
        try {
            User user = (User) session.getAttribute("USER");
            articleService.changeOpen(article, user.getId());
            return new Tip(true, 100, "更改成功", null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "articleDelete", method = RequestMethod.GET)
    @ResponseBody
    public Tip articleDelete(@RequestParam int id, HttpSession session) {
        try {
            User user = (User) session.getAttribute("USER");
            articleService.delete(id, user.getId());
            return new Tip(true, 100, "删除成功", null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }


    /**
     * 改变影响状态
     *
     * @param id      文章id
     * @param effect  ;true,支持；false，反对
     * @param session
     * @return
     */
    @RequestMapping(value = "article/changeEffect", method = RequestMethod.POST)
    @ResponseBody
    public Tip changeEffect(@RequestParam Integer id, @RequestParam Boolean effect, HttpSession session) {
        try {
            if (id == null || effect == null) {
                return new Tip(false, 101, "操作失败", null);
            }
            Object object = session.getAttribute("USER");
            if (object == null) {
                return new Tip(false, 102, "请先登录", null);
            }
            User user = (User) object;
            Map<String, Object> backMap = userEffectArticleService.changeEffect(id, effect, user);
            return new Tip(true, 100, "操作成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }


}