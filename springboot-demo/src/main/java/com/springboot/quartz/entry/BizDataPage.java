package com.springboot.quartz.entry;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 响应数据  统一分页  【从PageInfo中取部分字段返回】
 * 【支持两种分页查询结果集封装：PageHelper(via mybatis) 和 Page(via spring jpa)】
 * @author renzh 2017/5/11
 */
public class BizDataPage<T> implements Serializable {

    private static final long serialVersionUID = -2384421849690276824L;
    /** 当前页 */
    private int pageNum = 0;    //默认查询第一页

    /** 每页的数量 */
    private int pageSize = 10;  //默认每页10条记录

    /** 总记录数 */
    private long total;

    /** 总页数 */
    private int pages;

    /** 结果集 */
    private List list;


    public BizDataPage() {
    }

    /**
     * 构造函数
     * @param object  统一处理分页查询结果集
     */
    public BizDataPage(Object object) {
        if (object != null) {
            if(object instanceof PageInfo){
                PageInfo pageInfo = (PageInfo) object;
                this.pageNum = pageInfo.getPageNum();
                this.pageSize = pageInfo.getPageSize();
                this.total = pageInfo.getTotal();
                this.pages = pageInfo.getPages();
                this.list = pageInfo.getList();
            } else if (object instanceof Page) {
                Page page = (Page) object;
                this.pageNum = page.getNumber();
                this.pageSize = page.getSize();
                this.total = page.getTotalElements();
                this.pages = page.getTotalPages();
                this.list = page.getContent();
            }
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        if (pageSize == 0) {
            pageSize = 10;
        }
        long pagesTmp = total / pageSize;
        long mod = total % pageSize;
        if (mod > 0) {
            pages = Integer.parseInt("" + pagesTmp) + 1;
        }
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
