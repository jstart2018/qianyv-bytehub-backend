package com.qianyvbytehub.oss.adapter;


import com.qianyvbytehub.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AliyunStorageAdapter implements StorageAdapter {
    @Override
    public Boolean checkBucketExists(String bucketName) throws Exception {
        return null;
    }

    @Override
    public boolean createBucket(String bucketName) throws Exception {
        return false;
    }

    @Override
    public String getUrl(String bucketName, String objectName) {
        return "";
    }

    @Override
    public FileInfo uploadFile(MultipartFile file, String bucketName) throws Exception {
        return null;
    }

    @Override
    public InputStream downloadFile(String bucketName, String fileName) throws Exception {
        return null;
    }

    @Override
    public void bucketDelete(String bucketName) throws Exception {

    }

    @Override
    public void deleteFile(String bucketName, String fileName) throws Exception {

    }

    @Override
    public List<String> getBucketList() throws Exception {
        List<String> list = new ArrayList();
        list.add("aliyun");
        return list;
    }

    @Override
    public List<FileInfo> getFileInfoList(String bucketName) throws Exception {
        return Collections.emptyList();
    }
}
