package com.hu.dg.controller.front;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.ArticleService;
import com.hu.dg.service.FocusArticleService;
import com.hu.dg.service.FocusUserService;
import com.hu.dg.service.UserService;
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
 * @(#) FrontUserController.java 2016/07/06 13:04
 */
@Controller
@RequestMapping("front/user")
public class FrontUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FocusArticleService focusArticleService;

    @Autowired
    private FocusUserService focusUserService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String gotoArticle(@RequestParam int id, Model model){
//        model.addAttribute("id",id);
//        return "front/user/article-list";
//    }

    @RequestMapping(value="articleList/{id}",method = RequestMethod.GET)
    public String gotoArticleList(@PathVariable int id,@RequestParam(value = "index",required = false) Integer index, Model model){
        model.addAttribute("id",id);
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "front/user/article-list";
    }

    @RequestMapping(value = "articleList/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Tip articleList(@PathVariable int id, @RequestBody PageQuery query){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            map.put("userId",id);
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

    @RequestMapping(value="findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Tip findById(@PathVariable int id, HttpSession session){
        try {
            User user = null;
            Object userObject = session.getAttribute("USER");
            if(userObject!=null){
                user= (User) userObject;
            }
            Map<String, Object> userInfo = userService.findUserInfo(user, id);
            return new Tip(true,100,"查询成功",userInfo);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="focusArticleList/{id}",method = RequestMethod.GET)
    public String gotoFocusArticleList(@PathVariable int id,@RequestParam(value = "index",required = false) Integer index, Model model){
        model.addAttribute("id",id);
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "front/user/focus-article-list";
    }

    @RequestMapping(value = "focusArticleList/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Tip focusArticleList(@PathVariable int id, @RequestBody PageQuery query){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId",id);
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            List<Article> list = focusArticleService.list(map);
            int count = focusArticleService.getCount(map);
            query.setCount(count);
            Map<String, Object> backMap = new HashMap<String, Object>();
            backMap.put("list", list);
            backMap.put("pageQuery", query);
            return new Tip(true, 100, "查询成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="focusUserList/{id}",method = RequestMethod.GET)
    public String gotoFocusUserList(@PathVariable int id,@RequestParam(value = "index",required = false) Integer index, Model model){
        model.addAttribute("id",id);
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "front/user/focus-user-list";
    }

    @RequestMapping(value = "focusUserList/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Tip focusUserList(@PathVariable int id, @RequestBody PageQuery query){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId",id);
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            List<UserVO> list = focusUserService.listFocusUser(map);
            int count = focusUserService.getFocusUserCount(map);
            query.setCount(count);
            Map<String, Object> backMap = new HashMap<String, Object>();
            backMap.put("list", list);
            backMap.put("pageQuery", query);
            return new Tip(true, 100, "查询成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="fansList/{id}",method = RequestMethod.GET)
    public String gotoFansList(@PathVariable int id,@RequestParam(value = "index",required = false) Integer index, Model model){
        model.addAttribute("id",id);
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "front/user/fans-list";
    }

    @RequestMapping(value = "fansList/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Tip fansList(@PathVariable int id, @RequestBody PageQuery query){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId",id);
            map.put("start", query.getStart());
            map.put("size", query.getSize());
            List<UserVO> list = focusUserService.listFansUser(map);
            int count = focusUserService.getFansUserCount(map);
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