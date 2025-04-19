package com.qianyvbytehub.oss.adapter;


import com.qianyvbytehub.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface StorageAdapter {

    /**
     * 判断 桶 是否已存在
     */
    Boolean checkBucketExists(String bucketName) throws Exception;

    /**
     * 创建 bucket桶
     *
     * @param bucketName
     * @throws Exception
     */
    boolean createBucket(String bucketName) throws Exception;

    /**
     * 获取文件路径
     */
      String getUrl(String bucketName, String objectName);

    /**
     * 上传文件
     */
    FileInfo uploadFile(MultipartFile file, String bucketName) throws Exception;

    /**
     * 下载文件
     */
    InputStream downloadFile(String bucketName, String fileName) throws Exception;

    /**
     * 删除bucket
     */
    void bucketDelete(String bucketName) throws Exception;

    /**
     * 删除文件
     */
    void deleteFile(String bucketName, String fileName) throws Exception;


    /**
     * 列出所有 bucket
     */
    List<String> getBucketList() throws Exception;

    /**
     * 列出特定bucket的所有文件
     */
    List<FileInfo> getFileInfoList(String bucketName) throws Exception;

}
