package com.server.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 */
public class PageModel<T> implements Serializable {
    private static final long serialVersionUID = -6022537849785809781L;
    private int totalPage;
    private List<T> data;
    private int currentPage;
    private int nextPage;
    private int totalRecord;



    public PageModel() {
    }

    public PageModel(int totalPage, List<T> data, int currentPage) {
        this.totalPage = totalPage;
        this.data = data;
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNextPage() {
        return (currentPage+1)<=totalPage?currentPage+1:0;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
