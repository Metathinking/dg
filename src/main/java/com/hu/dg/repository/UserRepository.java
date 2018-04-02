package com.hu.dg.repository;

import com.hu.dg.domain.User;
import com.hu.dg.domain.bo.UserBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserRepository.java 2016/06/06 14:46
 */
@Repository
public interface UserRepository {

//    User findByNickname(String nickname);

    int getMaxId();

    User find(UserBO user);

    void register(User user);

    void updateNickname(int id,String nickname);

    void updatePassword(@Param("id") int id, @Param("password") String password);

    void delete(int id);

    List<User> list(Map<String,Object> params);

    int getCount(Map<String, Object> params);

    User findById(int id);

    void emailValidate(int id);

//    User findByEmail(String email);

    int getCountByNickname(String nickname);

    int getCountByEmail(String email);

    List<User> listByEmail(String email);
}