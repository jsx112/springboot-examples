package com.springboot.eureka.client.base.entity;

import java.io.Serializable;
import java.util.List;

/**
* 分页对象
* @author jiasx
* @create 2017/11/14 16:07
**/
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页码，从1开始
     */
    private int pageNum = 1;
    /**
     * 页面大小
     */
    private int pageSize = 10;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;

    /**
     * 业务数据
     */
    private List<T> result;

    public PageQuery() {
        calculateStartAndEndRow();
    }

    private PageQuery(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        calculateStartAndEndRow();
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        //分页合理化，针对不合理的页码自动处理
        this.pageNum = pageNum <= 0 ? 1 : pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        if (total == -1) {
            pages = 1;
            return;
        }
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
        //分页合理化，针对不合理的页码自动处理
        if (pageNum > pages) {
            pageNum = pages;
            calculateStartAndEndRow();
        }
    }


    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    }


    /**
     * 设置页面大小
     *
     * @param pageSize
     * @return
     */
    public void pageSize(int pageSize) {
        this.pageSize = pageSize;
        calculateStartAndEndRow();
    }

    @Override
    public String toString() {
        return "Page{" +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
}
