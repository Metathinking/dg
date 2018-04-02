package com.hu.dg.repository;

import com.hu.dg.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminRepository.java 2016/07/04 22:08
 */
@Repository
public interface AdminRepository {

    void create(Admin admin);

    void update(Admin admin);

    Admin find(@Param("id")String id,@Param("password")String password);

    int getCount();
}