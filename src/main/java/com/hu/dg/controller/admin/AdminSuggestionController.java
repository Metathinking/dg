package com.hu.dg.controller.admin;

import com.hu.dg.domain.Suggestion;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.SuggestionService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminSuggestionController.java 2016/07/04 16:57
 */
@Controller
@RequestMapping("admin/suggestion")
public class AdminSuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(){
        return "admin/suggestion-list";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<Suggestion> list = suggestionService.list(map);
            int count = suggestionService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="handle",method = RequestMethod.POST)
    @ResponseBody
    public Tip handle(@RequestParam int id){
        try {
            suggestionService.handle(id);
            return new Tip(true,100,"更新成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Tip delete(@RequestParam int id){
        try{
            suggestionService.delete(id);
            return new Tip(true,100,"删除成功",null);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }
}