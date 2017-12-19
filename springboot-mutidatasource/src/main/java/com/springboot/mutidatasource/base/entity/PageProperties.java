package com.springboot.mutidatasource.base.entity;

/**
* 分页查询  入参统一接收类
* @author jiasx
* @create 2017/12/4 14:14
**/
public class PageProperties {

    /**
     * 当前页  从1开始
     */
    protected Integer pageNum = 1;

    /**
     * 页面大小
     */
    protected Integer pageSize = 10;


    public Integer getPageNum() {
        return pageNum-1;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
    }

}