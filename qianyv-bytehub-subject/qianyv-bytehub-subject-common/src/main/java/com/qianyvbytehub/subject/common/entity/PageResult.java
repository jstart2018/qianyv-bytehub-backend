package com.qianyvbytehub.subject.common.entity;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页响应实体
 */
public class PageResult<T> implements Serializable {
    //当前页
    private Integer pageNo = 1;
    //一页展示数
    private Integer pageSize = 20;
    //总条数
    private Long total = 0L;
    //总页数
    private Integer totalPages = 0;
    //返回当前页具体数据
    private List<T> result = Collections.emptyList();
    //当前页的起始条数（数据库条数是索引从0开始，计算机时按照索引计算，得出的数再加 1）
    private Integer start = 1;
    //当前页的结束条数
    private Integer end = 0;

    public PageResult() {}
    public PageResult(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //插入数据
    public void setRecord(List<T> result){
        this.result = result;
        int total = result.size();
        if(total > 0 && result != null){
            setTotle((long) total);//插入总条数
            setTotalPage(total);//插入总页数
            setStart(total); //插入起始条数
            setEnd(total); //插入结束条数
        }
    }

    //插入总条数
    public void setTotle(Long total) {
        this.total = total;
    }
    //插入总页数
    private void setTotalPage(int total) {
        if (pageSize>=0 && pageSize<=total) {
            this.totalPages = total/this.pageSize + (total%this.pageSize==0?0:1);//末尾加上不足一页时的情况
        }else
            this.totalPages = 0;
    }
    //插入起始条数
    private void setStart(int total) {
        //数据库条数是索引从0开始，计算机时按照索引计算，得出的数再加 1
        this.start = (this.pageSize>0?(this.pageNo-1)*this.pageSize : 0)+1;
    }
    //插入结束条数
    private void setEnd(int total) {
        this.end = (this.start - 1 +
                (this.pageNo!=totalPages?this.pageSize:total%pageSize) //是否是最后一页加的数目不一样
                        * (this.pageNo > 0 ? 1 : 0));
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<T> getResult() {
        return result;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }
}
