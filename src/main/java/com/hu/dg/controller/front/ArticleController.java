package com.hu.dg.controller.front;

import com.google.zxing.WriterException;
import com.hu.dg.cache.SiteCache;
import com.hu.dg.domain.Article;
import com.hu.dg.domain.CodeImage;
import com.hu.dg.domain.User;
import com.hu.dg.query.ArticlePageQuery;
import com.hu.dg.service.ArticleService;
import com.hu.dg.service.CodeImageService;
import com.hu.dg.type.ArticleCategory;
import com.hu.dg.util.CodeImageUtil;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleController.java 2016/06/09 11:09
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CodeImageService codeImageService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String gotoDetail(@PathVariable int id, Model model, HttpServletRequest request) {
        model.addAttribute("id", id);
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("statement", getStatement(article));
        try {
            model.addAttribute("codeImageUrl", getCodeImageUrl(article.getId(), request));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        addHeadInfo(model, article);
        return "front/article-show";
    }

    private String getCodeImageUrl(int articleId, HttpServletRequest request) throws IOException, WriterException {
        String url = getArticleUrl(articleId);
        CodeImage codeImage = codeImageService.findById(articleId);
        if (codeImage == null) {
            codeImage = new CodeImage();
            codeImage.setId(articleId);
            String codeImageUrl = CodeImageUtil.createQRCodeImage(articleId, url, request);

//            return new Tip(true,100,"保存成功",serverUrl+filePath);
            codeImage.setUrl(codeImageUrl);
            codeImageService.create(codeImage);
        }
        return codeImage.getUrl();
    }

    /**
     * <head></head>内部的信息
     *
     * @param model
     * @param article
     */
    private void addHeadInfo(Model model, Article article) {
        model.addAttribute("title", article.getTitle());
        model.addAttribute("description", article.getDescription());
        String tags = article.getTag().replaceAll("\\|", ",");
        model.addAttribute("keywords", tags);
    }

    /**
     * 添加声明信息
     *
     * @param article
     */
    private String getStatement(Article article) {

        if (ArticleCategory.ORIGINAL.name().equals(article.getCategory())) {
            return "本文为原创文章，转载请注明原地址：" + getArticleUrl(article.getId());
        } else if (ArticleCategory.REPRINT.name().equals(article.getCategory())) {
            return "本文为转载文章，原文地址为：" + article.getOriginalUrl();
        }
        return "";
    }

    private String getArticleUrl(int id) {
        String url = SiteCache.getSiteInfo().getUrl();
        if (!url.endsWith("/")) {
            url += "/";
        }
        return url + "article/" + id + ".html";
    }

    @RequestMapping(value = "findArticlePageMsg", method = RequestMethod.POST)
    @ResponseBody
    public Tip findArticlePageMsg(@RequestParam Integer id, HttpSession session,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            Object obj = session.getAttribute("USER");
            User user = null;
            if (obj != null) {
                user = (User) obj;
            }
            Map<String, Object> backMap = articleService.findArticlePageMsg(id, user, request, response);
            return new Tip(true, 100, "查找成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody ArticlePageQuery query) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            map.put("keywords", query.getKeywords());
            List<Article> list = articleService.list(map);
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


}