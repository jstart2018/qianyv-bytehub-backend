package com.qianyvbytehub.oss.utils;


import com.qianyvbytehub.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 判断 桶 是否已存在
     */
    public Boolean checkBucketExists(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists
                (BucketExistsArgs.builder().bucket(bucketName).build());
        return exists;
    }

    /**
     * 创建 bucket桶
     * @param bucketName
     * @throws Exception
     */
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
    public void uploadFile(InputStream stream, String bucketName, String fileName ) throws Exception {
        minioClient.putObject(PutObjectArgs.builder()
        /**
         *stream的参数分别是文件：
         * 输入流：
         * 文件大小：-1表示MinIO 会尝试根据输入流本身来确定文件的大小
         * partSize：指定“文件内容”部分的最大长度，-1 表示上传流的长度为 fileSize，即上传整个文件内容。
         *           这个参数通常用于分块上传的场景，在大文件上传时，流可能会被分成多个块进行上传，
         *           但如果文件较小或是流本身不会分块，可以传入 -1
         */
                        .stream(stream,-1,-1)
                        .bucket(bucketName)
                        .object(fileName)
                        .build());
    }

    /**
     * 下载文件
     */
    public InputStream downloadFile(String bucketName, String fileName) throws Exception {
        GetObjectResponse objectResponse = minioClient.getObject
                (GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        return objectResponse;//GetObjectResponse继承了FilterInputStream
    }

    /**
     * 删除bucket
     */
    public void bucketDelete(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除文件
     */
    public void deleteFile(String bucketName, String fileName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
    }


    /**
     * 列出所有 bucket
     */
    public List<String> getBucketList() throws Exception {
        List<String> bucketName = new LinkedList<>();

        List<Bucket> buckets = minioClient.listBuckets();
        buckets.forEach(bucket -> {
            bucketName.add(bucket.name());
        });
        return bucketName;
    }

    /**
     * 列出特定bucket的所有文件
     */
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
