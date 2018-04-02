package com.hu.dg.controller.admin;

import com.hu.dg.domain.Article;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.ArticleService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleManagerController.java 2016/06/13 08:32
 */
@Controller
@RequestMapping("admin/articleManager")
public class ArticleManagerController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String gotoUserManager(){
        return "admin/article-manager";
    }

    @RequestMapping(value="list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<Article> list = articleService.list(map);
            int count = articleService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }

    }
}