package com.qianyvbytehub.oss.config;


import com.qianyvbytehub.oss.adapter.AliyunStorageAdapter;
import com.qianyvbytehub.oss.adapter.MinioStorageAdapter;
import com.qianyvbytehub.oss.adapter.StorageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RefreshScope
public class StorageConfig {

    @Value("${adapter.storage.ossType}")
    private String ossType;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
            if (ossType.equals("minio")) {
                log.info("StorageAdapter为：{}", ossType);
                return new MinioStorageAdapter();
            }else if (ossType.equals("aliyun")) {
                log.info("StorageAdapter为：{}", ossType);
                return new AliyunStorageAdapter();
            }else
                throw new RuntimeException("没有这个oss服务");
    }
}
