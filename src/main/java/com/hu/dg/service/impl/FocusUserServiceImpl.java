package com.hu.dg.service.impl;

import com.hu.dg.domain.*;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.repository.*;
import com.hu.dg.service.FocusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleServiceImpl.java 2016/06/19 20:42
 */
@Service
public class FocusUserServiceImpl implements FocusUserService {

    @Autowired
    private FocusUserRepository focusUserRepository;

    @Autowired
    private UserAboutInfoRepository userAboutInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMsgRepository userMsgRepository;
    /**
     *
     * @param focusUserId  被关注的人
     * @param focus true,收藏,false,取消收藏
     * @param user
     * @return
     */
    public Map<String,Object> changeFocus(int focusUserId, boolean focus, User user) {
        Map<String,Object> backMap = new HashMap<String, Object>();
        //用户不存在，或者是用户与登录人是同一个人，取消操作
        User toFocusUser =userRepository.findById(focusUserId);
        if(toFocusUser==null||toFocusUser.getId()==user.getId()){
            backMap.put("changed",false);
            return backMap;
        }
        if(focus){//添加关注
            FocusUser focusUser = focusUserRepository.find(user.getId(), focusUserId);
            if(focusUser!=null){
                backMap.put("changed",false);
                return backMap;
            }
            focusUser = new FocusUser();
            int maxId =  focusUserRepository.getMaxId();
            maxId++;
            focusUser.setId(maxId);
            focusUser.setFocusId(focusUserId);
            focusUser.setUserId(user.getId());
            focusUserRepository.create(focusUser);
            changeFocusUserInfo(focusUserId,backMap,1);
            changeLoginUserInfo(user.getId(),1);
            backMap.put("changed",true);
            backMap.put("focusUser",focusUser);
            return backMap;
        }else{//取消关注
            FocusUser focusUser = focusUserRepository.find(user.getId(), focusUserId);
            if(focusUser==null){
                backMap.put("changed",false);
                return backMap;
            }
            focusUserRepository.delete(focusUser.getId());
            changeFocusUserInfo(focusUserId,backMap,-1);
            changeLoginUserInfo(user.getId(),-1);
            backMap.put("changed",true);
            backMap.put("focusUser",null);
            return backMap;
        }
    }

    /**
     * 更新登录人的相关信息
     * @param userId
     */
    private void changeLoginUserInfo(int userId,int changeFocusCount) {
        UserAboutInfo userAboutInfo = userAboutInfoRepository.findById(userId);
        if(userAboutInfo==null){
            userAboutInfo = new UserAboutInfo();
            userAboutInfo.setUserId(userId);
            userAboutInfo.setArticleCount(0);
            userAboutInfo.setFocusArticleCount(0);
            if(changeFocusCount>0){
                userAboutInfo.setFocusUserCount(changeFocusCount);
            }else{
                userAboutInfo.setFocusUserCount(0);
            }
            userAboutInfo.setFansCount(0);
            userAboutInfo.setRecommendCount(0);
            userAboutInfoRepository.create(userAboutInfo);
        }else{
            if(userAboutInfo.getFocusUserCount()+changeFocusCount>0){
                userAboutInfo.setFocusUserCount(userAboutInfo.getFocusUserCount()+changeFocusCount);
            }else{
                userAboutInfo.setFocusUserCount(0);
            }
            userAboutInfoRepository.update(userAboutInfo);
        }
    }

    /**
     * 更新被关注人的相关信息
     * @param focusUserId
     * @param backMap
     */
    private void changeFocusUserInfo(int focusUserId,Map<String,Object> backMap,int changeFansCount){
        UserAboutInfo focusUserAboutInfo = userAboutInfoRepository.findById(focusUserId);
        if(focusUserAboutInfo==null){
            focusUserAboutInfo = new UserAboutInfo();
            focusUserAboutInfo.setUserId(focusUserId);
            focusUserAboutInfo.setArticleCount(0);
            focusUserAboutInfo.setFocusArticleCount(0);
            focusUserAboutInfo.setFocusUserCount(0);
            if(changeFansCount>0){
                focusUserAboutInfo.setFansCount(1);
            }else{
                focusUserAboutInfo.setFansCount(0);
            }
            focusUserAboutInfo.setRecommendCount(0);
            userAboutInfoRepository.create(focusUserAboutInfo);
        }else{
            if(focusUserAboutInfo.getFansCount()+changeFansCount>0){
                focusUserAboutInfo.setFansCount(focusUserAboutInfo.getFansCount()+changeFansCount);
            }else{
                focusUserAboutInfo.setFansCount(0);
            }
            userAboutInfoRepository.update(focusUserAboutInfo);
        }
        backMap.put("userAboutInfo",focusUserAboutInfo);
    }


    public List<UserVO> listFocusUser(Map<String, Object> params) {
        List<User> list = focusUserRepository.listFocusUser(params);
        List<UserVO> voList = getVOList(list);
        return voList;
    }



    public int getFocusUserCount(Map<String, Object> params) {
        return focusUserRepository.getFocusUserCount(params);
    }

    public List<UserVO> listFansUser(Map<String, Object> params) {
        List<User> list = focusUserRepository.listFans(params);
        List<UserVO> voList = getVOList(list);
        return voList;
    }

    public int getFansUserCount(Map<String, Object> params) {
        return focusUserRepository.getFansCount(params);
    }

    private List<UserVO> getVOList(List<User> list){
        List<UserVO> voList = new ArrayList<UserVO>();
        for(User user:list){
            UserVO vo = new UserVO();
            vo.setUser(user);
            vo.setUserAboutInfo(userAboutInfoRepository.findById(user.getId()));
            vo.setUserMsg(userMsgRepository.findById(user.getId()));
            voList.add(vo);
        }
        return voList;
    }
}