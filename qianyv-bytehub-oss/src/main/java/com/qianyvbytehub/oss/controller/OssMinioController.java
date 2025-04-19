package com.qianyvbytehub.oss.controller;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.common.base.Preconditions;
import com.qianyvbytehub.oss.entity.Result;
import com.qianyvbytehub.oss.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("oss")
@RefreshScope
public class OssMinioController {


    private static final Logger log = LoggerFactory.getLogger(OssMinioController.class);

    @Resource
    private FileService fileService;



    /**
     * 列出所有 bucket桶
     * @throws Exception
     */
    @GetMapping("/testOss")
    public String testOss() throws Exception {
        List<String> bucketList = fileService.getBucketList();
        return bucketList.toString();
    }


    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, String bucketName, String objectName) throws Exception {
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(bucketName),"bucketName不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(objectName),"objectName不能为空");

            String url = fileService.uploadFile(file, bucketName, objectName);
            if (StringUtils.isBlank(url)) {
                throw new RuntimeException("url为空");
            }
            return Result.ok(url);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.fail();
        }
    }




}
