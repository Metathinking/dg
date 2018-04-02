package com.hu.dg.service.impl;

import com.hu.dg.domain.Recommend;
import com.hu.dg.domain.User;
import com.hu.dg.repository.RecommendRepository;
import com.hu.dg.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecommendServiceImpl.java 2016/06/29 19:57
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendRepository recommendRepository;

    public void edit(Recommend recommend, User user) {
        if (recommend.getId() == 0) {
            save(recommend,user);
        } else {
            Recommend dbRecommend = recommendRepository.findById(recommend.getId());
            if (dbRecommend == null) {
                save(recommend,user);
            } else {
                dbRecommend.setImage(recommend.getImage());
                dbRecommend.setDescription(recommend.getDescription());
                dbRecommend.setShopUrl(recommend.getShopUrl());
                dbRecommend.setRecommendUrl(recommend.getRecommendUrl());
                dbRecommend.setTime(System.currentTimeMillis());
                recommendRepository.update(dbRecommend);
            }
        }
    }

    private void save(Recommend recommend,User user){
        int maxId = recommendRepository.getMaxId();
        maxId++;
        recommend.setId(maxId);
        recommend.setTime(System.currentTimeMillis());
        recommend.setUserId(user.getId());
        recommendRepository.create(recommend);
    }

    public List<Recommend> list(Map<String, Object> params) {
        return recommendRepository.list(params);
    }

    public void delete(int id) {
        recommendRepository.delete(id);
    }

    public int getCount(Map<String, Object> params) {
        return recommendRepository.getCount(params);
    }

    public Recommend findById(int id) {
        return recommendRepository.findById(id);
    }
}