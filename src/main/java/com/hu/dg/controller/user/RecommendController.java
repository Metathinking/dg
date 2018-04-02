package com.hu.dg.controller.user;

import com.hu.dg.domain.Recommend;
import com.hu.dg.domain.User;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.RecommendService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecommendController.java 2016/06/29 20:04
 */
@Controller
@RequestMapping("user/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String gotoEdit(@RequestParam(value="id",required = false) Integer id , Model model){
        if(id==null){
            id=-1;
        }
        model.addAttribute("id",id);
        return "user/recommend-edit";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody Recommend recommend, HttpSession session){
        try {
            if(StringUtils.isEmpty(recommend.getShopUrl())){
                return new Tip(false,101,"请输入商品购买链接",null);
            }
            if(StringUtils.isEmpty(recommend.getRecommendUrl())){
                return new Tip(false,102,"请输入您的推荐链接",null);
            }
            User user = (User) session.getAttribute("USER");
            recommendService.edit(recommend,user);
            return new Tip(true,100,"保存成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "findById",method = RequestMethod.POST)
    @ResponseBody
    public Tip findById(@RequestParam(value = "id")Integer id){
        try {
            if(id==null){
                return new Tip(false,101,"请求有误",null);
            }
            Recommend recommend = recommendService.findById(id);
            return new Tip(true,100,"查询成功",recommend);
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
        return "user/recommend-list";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestParam(value="index",required = false) Integer index,
                    @RequestParam(value="size",required = false) Integer size,
                    HttpSession session){
        try{
            PageQuery query = new PageQuery();
            if(index!=null){
                query.setIndex(index);
            }
            if(size!=null){
                query.setSize(size);
            }
            Map<String,Object> map = new HashMap<String, Object>();
            User user = (User) session.getAttribute("USER");
            map.put("userId",user.getId());
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<Recommend> list = recommendService.list(map);
            int count = recommendService.getCount(map);
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
    public Tip delete(@RequestParam int id){
        try {
            recommendService.delete(id);
            return new Tip(true,100,"删除成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}