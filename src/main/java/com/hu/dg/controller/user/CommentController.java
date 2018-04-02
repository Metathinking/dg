package com.hu.dg.controller.user;

import com.hu.dg.domain.Comment;
import com.hu.dg.domain.CommentAboutInfo;
import com.hu.dg.domain.User;
import com.hu.dg.domain.vo.CommentVO;
import com.hu.dg.service.CommentService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentController.java 2016/06/26 17:43
 */
@Controller
@RequestMapping("user")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "commentArticle",method = RequestMethod.POST)
    @ResponseBody
    public Tip commentArticle(@RequestBody Comment comment, HttpSession session){
        try {
            if(StringUtils.isEmpty(comment.getContent())){
                return new Tip(false,101,"请添加评论内容",null);
            }
            User user = (User) session.getAttribute("USER");
            CommentVO commentVO = commentService.create(comment, user);
            return new Tip(true,100,"评论成功",commentVO);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="effectComment",method = RequestMethod.POST)
    @ResponseBody
    public Tip changeEffect(@RequestParam Integer id, @RequestParam Boolean effect, HttpSession session) {
        try {
            if (id == null || effect == null) {
                return new Tip(false, 101, "操作失败", null);
            }
            Object object = session.getAttribute("USER");

            User user = (User) object;
            CommentAboutInfo commentAboutInfo = commentService.changeEffect(id, effect, user);
            return new Tip(true, 100, "操作成功", commentAboutInfo);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}