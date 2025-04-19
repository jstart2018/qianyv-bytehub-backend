package com.qianyvbytehub.oss.config;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    private static final Logger log = LoggerFactory.getLogger(MinioConfig.class);
    //minioUrl
    @Value("${minio.url}")
    private String url;

    //minio账户
    @Value("${minio.accessKey}")
    private String accessKey;

    //minio密码
    @Value("${minio.secretKet}")
    private String secretKet;

    /**
     * 连接minio
     * @return
     */
    @Bean
    public MinioClient getMinioClient() {
        log.info("初始化minio：{}",url);
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKet)
                .build();
    }


}
