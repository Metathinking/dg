package com.hu.dg.service;

import com.hu.dg.domain.ImageInfo;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ImageInfoService.java 2016/06/22 13:37
 */
public interface ImageInfoService {

    void create(ImageInfo imageInfo);

    ImageInfo findByCode(String code);
}