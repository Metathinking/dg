package com.hu.dg.service.impl;

import com.hu.dg.domain.*;
import com.hu.dg.domain.vo.CommentVO;
import com.hu.dg.repository.ArticleAboutInfoRepository;
import com.hu.dg.repository.CommentAboutInfoRepository;
import com.hu.dg.repository.CommentRepository;
import com.hu.dg.repository.UserEffectCommentRepository;
import com.hu.dg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentServiceImpl.java 2016/06/24 14:25
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentAboutInfoRepository  commentAboutInfoRepository;
    @Autowired
    private ArticleAboutInfoRepository articleAboutInfoRepository;

    @Autowired
    private UserEffectCommentRepository userEffectCommentRepository;

    public CommentVO create(Comment comment, User user) {
        int maxId = commentRepository.getMaxId();
        maxId++;
        comment.setId(maxId);
        comment.setUserId(user.getId());
        comment.setUserNickname(user.getNickname());
        comment.setTime(System.currentTimeMillis());
        comment.setPreId(-1);
        commentRepository.create(comment);
        updateArticleAboutInfo(comment);
        CommentAboutInfo commentAboutInfo = createCommentAboutInfo(comment);
        CommentVO commentVO = new CommentVO();
        commentVO.setComment(comment);
        commentVO.setCommentAboutInfo(commentAboutInfo);
        return commentVO;
    }

    private CommentAboutInfo createCommentAboutInfo(Comment comment){
        CommentAboutInfo commentAboutInfo = new CommentAboutInfo();
        commentAboutInfo.setCommentId(comment.getId());
        commentAboutInfo.setSupportCount(0);
        commentAboutInfo.setOpposeCount(0);
        commentAboutInfoRepository.create(commentAboutInfo);
        return commentAboutInfo;
    }

    private void updateArticleAboutInfo(Comment comment){
        ArticleAboutInfo articleAboutInfo = articleAboutInfoRepository.findById(comment.getArticleId());
        articleAboutInfo.setCommentCount(articleAboutInfo.getCommentCount()+1);
        articleAboutInfoRepository.update(articleAboutInfo);
    }

    public List<Comment> list(Map<String, Object> params) {
        return commentRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return commentRepository.getCount(params);
    }

    public void delete(int id) {
        commentRepository.delete(id);
    }

    public CommentAboutInfo changeEffect(Integer id, Boolean effect, User user) {
        UserEffectComment dbUserEffectComment = userEffectCommentRepository.find(user.getId(), id);
        if(dbUserEffectComment!=null){
            CommentAboutInfo commentAboutInfo = commentAboutInfoRepository.findById(id);
            if(commentAboutInfo==null){
                commentAboutInfo = createCommentAboutInfo(id,effect);
            }
            return commentAboutInfo;
        }
        int maxId = userEffectCommentRepository.getMaxId();
        maxId++;
        UserEffectComment userEffectComment = new UserEffectComment();
        userEffectComment.setId(maxId);
        userEffectComment.setCommentId(id);
        userEffectComment.setUserId(user.getId());
        userEffectComment.setEffect(effect);
        userEffectCommentRepository.create(userEffectComment);

        CommentAboutInfo commentAboutInfo = commentAboutInfoRepository.findById(id);
        if(commentAboutInfo==null){//加强容错性
            commentAboutInfo = createCommentAboutInfo(id,effect);
        }else{
            if(effect){
                commentAboutInfo.setSupportCount(commentAboutInfo.getSupportCount()+1);
            }else{
                commentAboutInfo.setOpposeCount(commentAboutInfo.getOpposeCount()+1);
            }
            commentAboutInfoRepository.update(commentAboutInfo);
        }
        return commentAboutInfo;
    }

    private CommentAboutInfo createCommentAboutInfo(int commentId,boolean effect){
        CommentAboutInfo commentAboutInfo = new CommentAboutInfo();
        commentAboutInfo.setCommentId(commentId);
        if(effect){
            commentAboutInfo.setSupportCount(1);
            commentAboutInfo.setOpposeCount(0);
        }else{
            commentAboutInfo.setSupportCount(0);
            commentAboutInfo.setOpposeCount(1);
        }
        commentAboutInfoRepository.create(commentAboutInfo);
        return commentAboutInfo;
    }
}