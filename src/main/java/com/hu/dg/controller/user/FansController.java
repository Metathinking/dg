package com.hu.dg.controller.user;

import com.hu.dg.domain.User;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.FocusUserService;
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
 * @(#) FocusUserController.java 2016/07/06 22:46
 */
@Controller
@RequestMapping("/user/fans")
public class FansController {
    @Autowired
    private FocusUserService focusUserService;



    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(@RequestParam(value = "index",required = false) Integer index,Model model){
        if(index==null){
            index=1;
        }
        model.addAttribute("index",index);
        return "user/fans-list";
    }

    @RequestMapping(value="list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query, HttpSession session){
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            User user = (User) session.getAttribute("USER");
            map.put("userId",user.getId());
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<UserVO> list = focusUserService.listFansUser(map);
            int count = focusUserService.getFansUserCount(map);
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