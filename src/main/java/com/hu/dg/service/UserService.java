package com.hu.dg.service;

import com.hu.dg.domain.EmailValidate;
import com.hu.dg.domain.User;
import com.hu.dg.domain.bo.UserBO;
import com.hu.dg.domain.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserService.java 2016/06/06 18:15
 */
public interface UserService {

    UserVO login(UserBO user, HttpServletRequest request);

    User register(UserBO userBO);

    void updateNickname(int id,String nickname);

    void updatePasswordForForget(String email,String password);

    void delete(int id);

    List<User> list(Map<String,Object> params);

    int getCount(Map<String, Object> params);

    User findById(int id);

    void emailValidate(int id);

    List<User> listByEmail(String email);

    Map<String,Object> findUserInfo(User loginUser,int showUserId);
}