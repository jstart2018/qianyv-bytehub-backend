package com.qianyvbytehub.auth.common.entity;

/**
 * 分页查询发送的请求实体
 */
public class PageInfo {
    //当前页码
    private Integer pageNo = 1;
    //一页展示数
    private Integer pageSize = 10;
    //


    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }


    public Integer getPageSize() {
        if (pageSize == null || pageNo < 1 || pageSize>Integer.MAX_VALUE ) {
            pageNo = 20;
        }
        return pageSize;
    }
}
