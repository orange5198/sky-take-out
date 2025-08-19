package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api("通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile  file){
        log.info("文件上传，上传的文件：{}", file);
        try {
            String OriginalFilename =file.getOriginalFilename();//获取原始文件名
            String extension = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
            //构建新文件名称
            String fileName = UUID.randomUUID().toString() + extension;
            //上传文件
            String filePath =aliOssUtil.upload(file.getBytes(), fileName);
             return  Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
