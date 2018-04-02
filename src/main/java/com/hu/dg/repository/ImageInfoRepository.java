package com.hu.dg.repository;

import com.hu.dg.domain.ImageInfo;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ImageInfoRepository.java 2016/06/22 13:11
 */
@Repository
public interface ImageInfoRepository {

    void create(ImageInfo imageInfo);

    void delete(int id);

    ImageInfo findByCode(String code);

    int getMaxId();
}