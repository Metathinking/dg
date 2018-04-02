package com.hu.dg.service.impl;

import com.hu.dg.domain.*;
import com.hu.dg.domain.bo.UserBO;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.exception.ServiceException;
import com.hu.dg.repository.*;
import com.hu.dg.service.RemarkService;
import com.hu.dg.service.UserService;
import com.hu.dg.type.UserStatus;
import com.hu.dg.util.CodeUtil;
import com.hu.dg.util.Md5Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserServiceImpl.java 2016/06/06 18:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private RemarkService remarkService;

    @Autowired
    private UserAboutInfoRepository userAboutInfoRepository;
    @Autowired
    private FocusUserRepository focusUserRepository;

    @Autowired
    private UserMsgRepository userMsgRepository;


    public UserVO  login(UserBO userBO, HttpServletRequest request) {
        User dbUser = userRepository.find(userBO);
        if (dbUser == null) {
            throw new ServiceException(202, "用户名或密码错误");
        }
        saveLoginLog(request, dbUser);
        UserVO userVO = new UserVO();
        userVO.setUser(dbUser);
        UserAboutInfo userAboutInfo = userAboutInfoRepository.findById(dbUser.getId());
        if(userAboutInfo==null){
            userAboutInfo = new UserAboutInfo();
        }
        userVO.setUserAboutInfo(userAboutInfo);
        UserMsg userMsg = userMsgRepository.findById(dbUser.getId());
        if(userMsg==null){
            userMsg = new UserMsg();
        }
        userVO.setUserMsg(userMsg);
        return userVO;
    }

    /**
     * 保存登录日志
     *
     * @param request
     * @param dbUser
     */
    private void saveLoginLog(HttpServletRequest request, User dbUser) {
        LoginLog loginLog = new LoginLog();
        int maxId = loginLogRepository.getMaxId();
        maxId++;
        loginLog.setId(maxId);
        loginLog.setUserId(dbUser.getId());
        loginLog.setIp(request.getRemoteAddr());
        loginLog.setTime(System.currentTimeMillis());
        loginLogRepository.create(loginLog);
    }

    public User register(UserBO userBO) {
        int countByNickName = userRepository.getCountByNickname(userBO.getNickname());
        if (countByNickName >0) {
            throw new ServiceException(201, "用户名已存在，请重新输入");
        }
        int countByEmail = userRepository.getCountByEmail(userBO.getEmail());
        if(countByEmail>0){
            throw new ServiceException(202,"邮箱已使用，请重新输入");
        }
        int maxId = userRepository.getMaxId();
        User user = new User();
        maxId++;
        user.setId(maxId);
        user.setNickname(userBO.getNickname());
        user.setEmail(userBO.getEmail());
        user.setEmailValidate(false);
        user.setStatus(UserStatus.OPEN.name());
        user.setPassword(userBO.getPassword());
        remarkService.remark(user);
        user.setPassword(Md5Factory.encoding(userBO.getPassword()));
        user.setTime(System.currentTimeMillis());
        userRepository.register(user);
        createUserAboutInfo(user);
        user.setPassword("");
       return user;
    }

    private void createUserAboutInfo(User user) {
        UserAboutInfo userAboutInfo = new UserAboutInfo();
        userAboutInfo.setUserId(user.getId());
        userAboutInfo.setRecommendCount(0);
        userAboutInfo.setArticleCount(0);
        userAboutInfo.setFocusUserCount(0);
        userAboutInfo.setFansCount(0);
        userAboutInfo.setFocusArticleCount(0);
        userAboutInfoRepository.create(userAboutInfo);
    }

    public void updateNickname(int id, String nickname) {

    }

    public void updatePasswordForForget(String email, String password) {
        List<User> users = userRepository.listByEmail(email);
        User user = users.get(0);
        password= Md5Factory.encoding(password);
        userRepository.updatePassword(user.getId(),password);
    }


    // TODO: 2016/6/16.0016 是否真的删除
    public void delete(int id) {
//        userRepository.delete(id);
    }

    public List<User> list(Map<String, Object> params) {
        List<User> list = userRepository.list(params);
        return list;
    }

    public int getCount(Map<String, Object> params) {
        int count = userRepository.getCount(params);
        return count;
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void emailValidate(int id) {
        userRepository.emailValidate(id);
    }

    public List<User> listByEmail(String email) {
        return userRepository.listByEmail(email);
    }

    /**
     *
     * @param loginUser 登录用户
     * @param showUserId 显示的用户id
     * @return
     */
    public Map<String, Object> findUserInfo(User loginUser, int showUserId) {
        Map<String,Object>  backData = new HashMap<String, Object>();
        if(loginUser!=null){
            if(loginUser.getId()==showUserId){
                backData.put("own",true);
            }else{
                backData.put("own",false);
            }
            FocusUser focusUser = focusUserRepository.find(loginUser.getId(), showUserId);
            backData.put("focusUser",focusUser);
        }
        User showUser = userRepository.findById(showUserId);
        UserAboutInfo userAboutInfo = userAboutInfoRepository.findById(showUserId);
        UserMsg userMsg = userMsgRepository.findById(showUserId);
        backData.put("user",showUser);
        backData.put("userAboutInfo",userAboutInfo);
        backData.put("userMsg",userMsg);
        return backData;
    }
}