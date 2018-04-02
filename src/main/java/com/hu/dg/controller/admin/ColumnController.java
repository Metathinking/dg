package com.hu.dg.controller.admin;

import com.hu.dg.cache.SiteCache;
import com.hu.dg.domain.Column;
import com.hu.dg.service.ColumnService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ColumnController.java 2016/07/31 13:15
 */
@Controller
@RequestMapping("admin/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(){
        return "admin/column";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(){
        try {
            List<Column> list = columnService.list();
            return new Tip(true,100,"查询成功",list);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody List<Column> columnList,HttpSession session){
        try {
            List<Column> saveList = new ArrayList<Column>();
            for(Column column:columnList){
                if(StringUtils.isEmpty(column.getName())&&StringUtils.isEmpty(column.getKeywords())){
                    continue;
                }
                if(StringUtils.isEmpty(column.getName())){
                    return new Tip(false,101,"请输入栏目名称",null);
                }
                if(StringUtils.isEmpty(column.getKeywords())){
                    return new Tip(false,102,"请输入关键词",null);
                }
                saveList.add(column);
            }
            List<Column> dbColumns = columnService.edit(saveList);
            updateSessionAndCache(session,dbColumns);
            return new Tip(true,100,"更新成功",dbColumns);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    private void updateSessionAndCache(HttpSession session, List<Column> columns){
        session.setAttribute(SiteCache.COLUMN_LIST,columns);
        SiteCache.put(SiteCache.COLUMN_LIST,columns);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Tip delete(@RequestParam Integer id){
        try {
            if(id==null){
                return new Tip(false,101,"请求错误",null);
            }
            List<Column> columnList = columnService.delete(id);
            return new Tip(true,100,"删除成功",columnList);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}