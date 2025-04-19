package com.qianyvbytehub.oss.entity;

import lombok.Data;

@Data
public class FileInfo {

    //文件名
    private String fileName;

    //url
    private String url;

    //文件头标签
    private String etag;

    //是否为文件夹
    private Boolean DirFlag;

    //文件大小
    private long size;


}
