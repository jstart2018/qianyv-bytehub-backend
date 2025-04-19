package com.qianyvbytehub.oss.adapter;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.qianyvbytehub.oss.entity.FileInfo;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioClient minioClient;


    /**
     * 判断 桶 是否已存在
     */
    @SneakyThrows
    public Boolean checkBucketExists(String bucketName) throws Exception{
        boolean exists = minioClient.bucketExists
                (BucketExistsArgs.builder().bucket(bucketName).build());
        return exists;
    }

    /**
     * 获取文件的url
     */
    @SneakyThrows
    public  String getUrl(String bucketName, String objectName) {
        String url = null;

        try {
            if (StringUtils.isBlank(objectName)) {
                log.error("获取文件预览url失败，文件名为空！");
                return null;
            }
            // 判断桶是否存在
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            // 桶存在
            if (isExist) {
                url = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.PUT)
                                .bucket(bucketName)
                                .object(objectName)
                                .expiry(1, TimeUnit.DAYS) // 一天过期时间
                                .build());
                return url;
            } else {  // 桶不存在
                log.error("获取文件预览url失败，桶{}不存在", bucketName);
            }
        } catch (Exception e) {
            log.error("获取文件url异常",e.getMessage());
        }
        return url;
    }

    /**
     * 创建 bucket桶
     * @param bucketName
     * @throws Exception
     */
    @SneakyThrows
    public boolean createBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists
                (BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists){
            throw new RuntimeException("该桶已存在");
        }
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        return true;
    }

    /**
     * 上传文件
     */
    @SneakyThrows
    public FileInfo uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 获取桶名
        log.info("开始向桶 {} 上传文件", bucketName);
        //给文件生成一个唯一名称  当日日期-uuid.后缀名
        String folderName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        String uuid = String.valueOf(UUID.randomUUID());
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//文件后缀名
        String objectName = folderName + "-" + uuid + extName;

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            // 配置参数
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName)
                    .stream(inputStream, file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.error("文件上传失败: " + e);
            throw new RuntimeException(e);
        }
        log.info("文件上传成功，文件名为：{}", objectName);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(objectName);

        return fileInfo;
    }

    /**
     * 下载文件
     */
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String fileName) throws Exception {
        GetObjectResponse objectResponse = minioClient.getObject
                (GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        return objectResponse;//GetObjectResponse继承了FilterInputStream
    }

    /**
     * 删除bucket
     */
    @SneakyThrows
    public void bucketDelete(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除文件
     */
    @SneakyThrows
    public void deleteFile(String bucketName, String fileName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
    }


    /**
     * 列出所有 bucket
     */
    @SneakyThrows
    public List<String> getBucketList() throws Exception {
        List<String> bucketNames = new LinkedList<>();

        List<Bucket> buckets = minioClient.listBuckets();
        buckets.forEach(bucket -> {
            bucketNames.add(bucket.name());
        });
        return bucketNames;
    }

    /**
     * 列出特定bucket的所有文件
     */
    @SneakyThrows
    public List<FileInfo> getFileInfoList(String bucketName) throws Exception {
        List<FileInfo> getFileInfoList = new LinkedList<>();
        Iterable<Result<Item>> results = minioClient
                .listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        for (Result<Item> result : results) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(result.get().objectName());
            fileInfo.setSize(result.get().size());
            fileInfo.setEtag(result.get().etag());
            fileInfo.setDirFlag(result.get().isDir());
            getFileInfoList.add(fileInfo);
        }
        return getFileInfoList;
    }

}
