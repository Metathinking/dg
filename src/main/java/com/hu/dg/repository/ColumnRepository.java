package com.hu.dg.repository;

import com.hu.dg.domain.Column;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ColumnRepository.java 2016/07/31 10:32
 */
@Repository
public interface ColumnRepository {

    void create(List<Column> list);

    void update(Column column);

    void delete(int id);

    List<Column> list();

    int getMaxId();

    Column findById(int id);
}