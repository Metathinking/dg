package com.hu.dg.repository;

import com.hu.dg.domain.UserAboutInfo;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleAboutInfoRepository.java 2016/06/19 19:26
 */
@Repository
public interface UserAboutInfoRepository {

    void create(UserAboutInfo userAboutInfo);

    void update(UserAboutInfo userAboutInfo);

    void delete(int userId);

    UserAboutInfo findById(int userId);
}