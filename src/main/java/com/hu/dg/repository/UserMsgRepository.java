package com.hu.dg.repository;

import com.hu.dg.domain.UserMsg;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserMsgRepository.java 2016/07/09 21:40
 */
@Repository
public interface UserMsgRepository {

    void create(UserMsg userMsg);

    void update(UserMsg userMsg);

    void delete(int id);

    UserMsg findById(int id);
}