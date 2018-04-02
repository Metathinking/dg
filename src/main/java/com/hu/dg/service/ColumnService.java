package com.hu.dg.service;

import com.hu.dg.domain.Column;

import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ColumnService.java 2016/07/31 10:40
 */
public interface ColumnService {

    List<Column> edit(List<Column> list);

    List<Column> delete(int id);

    List<Column> list();
}