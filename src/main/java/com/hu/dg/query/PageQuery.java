package com.hu.dg.query;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) PageQuery.java 2016/03/10 19:09
 */
public class PageQuery {

    private int index;//索引
    private int size;//每页条数
    private int start;//起始位置
    private int end;//截止位置

    private int count;//总条数
    private int pageCount;//总页数
    private boolean current;//是否是当前页

    private void resetStartAndEnd() {
        if (size == 0) {
            size=20;
        }
        if (index == 0){
            index = 1;
        }
        start = (index - 1) * size;
        end = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        resetStartAndEnd();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        resetStartAndEnd();
    }

    public int getStart() {
        resetStartAndEnd();
        return start;
    }

    public int getEnd() {
        resetStartAndEnd();
        return end;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        setPageCount();
    }

    public int getPageCount() {
        return pageCount;
    }

    private void setPageCount() {
        if(getCount()%getSize()==0){
            pageCount=getCount()/getSize();
        }else{
            pageCount=getCount()/getSize()+1;
        }
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}