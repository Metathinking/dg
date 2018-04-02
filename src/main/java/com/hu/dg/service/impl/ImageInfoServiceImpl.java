package com.hu.dg.service.impl;

import com.hu.dg.domain.ImageInfo;
import com.hu.dg.repository.ImageInfoRepository;
import com.hu.dg.service.ImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ImageInfoServiceImpl.java 2016/06/22 13:38
 */
@Service
public class ImageInfoServiceImpl implements ImageInfoService {

    @Autowired
    private ImageInfoRepository imageInfoRepository;

    /**
     * 保存图片信息
     * @param imageInfo
     */
    public void create(ImageInfo imageInfo) {
        int maxId = imageInfoRepository.getMaxId();
        maxId++;
        imageInfo.setId(maxId);
        imageInfoRepository.create(imageInfo);
    }

    public ImageInfo findByCode(String code) {
        return imageInfoRepository.findByCode(code);
    }
}