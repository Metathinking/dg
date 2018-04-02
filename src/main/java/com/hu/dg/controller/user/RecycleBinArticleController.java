package com.hu.dg.controller.user;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;
import com.hu.dg.query.ArticlePageQuery;
import com.hu.dg.service.RecycleBinArticleService;
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
 * @(#) RecycleBinArticleController.java 2016/06/18 20:28
 */
@Controller
@RequestMapping("/user/recycleBinArticle")
public class RecycleBinArticleController {

    @Autowired
    private RecycleBinArticleService recycleBinArticleService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(@RequestParam(value = "index",required = false) Integer index,Model model){
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "user/recycle-bin-article-list";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody ArticlePageQuery query, HttpSession session){
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            User user = (User) session.getAttribute("USER");
            map.put("userId",user.getId());
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<Article> list = recycleBinArticleService.list(map);
            for(Article article:list){
                article.setCategory(ArticleCategory.valueOf(article.getCategory()).getDescription());
            }
            int count = recycleBinArticleService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Tip delete(@RequestParam int id, HttpSession session){
        try {
            User user = (User) session.getAttribute("USER");
            recycleBinArticleService.delete(id,user.getId());
            return new Tip(true,100,"删除成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    /**
     * 在回收站，把文章还原
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="restore",method = RequestMethod.POST)
    @ResponseBody
    public Tip restore(@RequestParam int id,HttpSession session){
        try {
            User user = (User) session.getAttribute("USER");
            recycleBinArticleService.restore(id,user.getId());
            return new Tip(true,100,"删除成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}