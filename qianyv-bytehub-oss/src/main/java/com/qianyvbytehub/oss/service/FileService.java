package com.qianyvbytehub.oss.service;


import com.qianyvbytehub.oss.adapter.StorageAdapter;
import com.qianyvbytehub.oss.entity.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {
    //构造器注入
    private StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    //各种业务给controller

    /**
     * 列出所有 bucket
     */
    public List<String> getBucketList() throws Exception {

        List<String> buckets = storageAdapter.getBucketList();

        return buckets;
    }

    /**
     * 获取文件路径
     */
    public String getUrl(String bucket, String object) throws Exception {
        return storageAdapter.getUrl(bucket,object);
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file, String bucketName, String objectName) throws Exception {
        FileInfo fileInfo = storageAdapter.uploadFile(file, bucketName);
        if (fileInfo != null) {
            return storageAdapter.getUrl(bucketName,fileInfo.getFileName());
        }
        return null;
    }


}
