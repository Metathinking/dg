package com.hu.dg.controller.user;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;
import com.hu.dg.query.ArticlePageQuery;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.FocusArticleService;
import com.hu.dg.type.ArticleCategory;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleController.java 2016/06/29 17:33
 */
@Controller
@RequestMapping("user/focusArticle")
public class FocusArticleController {


    @Autowired
    private FocusArticleService focusArticleService;

    /**
     * 重置收藏状态
     *
     * @param id      文章id
     * @param focus   是否收藏;true,收藏；false，取消收藏
     * @param session
     * @return
     */
    @RequestMapping(value = "changeFocus", method = RequestMethod.POST)
    @ResponseBody
    public Tip changeFocus(@RequestParam Integer id, @RequestParam Boolean focus, HttpSession session) {
        try {
            if (id == null || focus == null) {
                return new Tip(false, 101, "操作失败", null);
            }
            Object object = session.getAttribute("USER");
            if (object == null) {
                return new Tip(false, 102, "请先登录", null);
            }
            User user = (User) object;
            Map<String, Object> backMap = focusArticleService.changeFocus(id, focus, user);
            return new Tip(true, 100, "操作成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(@RequestParam(value = "index",required = false) Integer index,Model model){
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "user/focus-article-list";
    }

    @RequestMapping(value="list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody ArticlePageQuery query, HttpSession session){
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            User user = (User) session.getAttribute("USER");
            map.put("userId",user.getId());
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<Article> list = focusArticleService.list(map);
            for(Article article:list){
                article.setCategory(ArticleCategory.valueOf(article.getCategory()).getDescription());
            }
            int count = focusArticleService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }
}