package com.hu.dg.service;

import com.hu.dg.domain.User;
import com.hu.dg.domain.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleService.java 2016/06/19 20:41
 */
public interface FocusUserService {

    Map<String,Object> changeFocus(int focusUserId, boolean focus, User user);

    List<UserVO> listFocusUser(Map<String, Object> params);

    int getFocusUserCount(Map<String, Object> params);

    List<UserVO> listFansUser(Map<String,Object> params);

    int getFansUserCount(Map<String,Object> params);
}