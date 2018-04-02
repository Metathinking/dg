package com.hu.dg.repository;

import com.hu.dg.domain.BrowseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) BrowseRecordRepository.java 2016/08/02 00:25
 */
@Repository
public interface BrowseRecordRepository {

    void create(BrowseRecord browseRecord);

    void update(BrowseRecord browseRecord);

    List<BrowseRecord> list(Map<String,Object> params);

    BrowseRecord findById(int id);

    BrowseRecord findByArticleIdAndUserId(@Param("articleId") int articleId,@Param("userId")int userId);

    BrowseRecord findByArticleIdAndIp(@Param("articleId") int articleId,@Param("ip")String ip);

    int getCount(Map<String,Object> params);

    int getMaxId();
}