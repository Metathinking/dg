package com.hu.dg.repository;

import com.hu.dg.domain.Remark;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RemarkRepository.java 2016/06/07 14:51
 */
@Repository
public interface RemarkRepository {

    void remark(Remark remark);
}