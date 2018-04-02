package com.hu.dg.service.impl;

import com.hu.dg.domain.Column;
import com.hu.dg.repository.ColumnRepository;
import com.hu.dg.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ColumnServiceImpl.java 2016/07/31 10:41
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    private final String preUrl = "/search.html?keywords=";

    public List<Column> edit(List<Column> list) {
        List<Column> createList = new ArrayList<Column>();
        List<Column> updateList = new ArrayList<Column>();
        for(Column column:list){
            column.setUrl(preUrl+column.getKeywords());
            if(column.getId()==0){
                createList.add(column);
            }else{
                updateList.add(column);
            }
        }
        for(Column column:updateList){
            Column dbColumn = columnRepository.findById(column.getId());
            if(dbColumn==null){
                createList.add(dbColumn);
            }else{
                dbColumn.setName(column.getName());
                dbColumn.setUrl(column.getUrl());
                dbColumn.setKeywords(column.getKeywords());
                dbColumn.setOrder(column.getOrder());
                columnRepository.update(dbColumn);
            }
        }
        if(createList.size()>0){
            int maxId = columnRepository.getMaxId();
            for(Column column:createList){
                maxId++;
                column.setId(maxId);
            }
            columnRepository.create(createList);
        }
        return list();
    }

    public List<Column> delete(int id) {
        columnRepository.delete(id);
        return list();
    }

    public List<Column> list() {
        return columnRepository.list();
    }
}