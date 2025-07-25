package com.itxuan.controller;

import com.itxuan.pojo.Result;
import com.itxuan.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;


    //这个是本地存储的方式
//    @PostMapping("/upload")
//    public Result upload(String username, MultipartFile file) throws  Exception{
//        log.info("上传文件，参数：username={},file={}",username,file);
//        //这里都只是保存在临时目录，没有保存磁盘中
//
//        String originalFilename = file.getOriginalFilename();
//
//
//        //我们需要自己给文件名重新生成，因为前端传回来的文件名可能存在重复
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String destFilename = UUID.randomUUID().toString() + extension;
//
//        file.transferTo(new File("E:\\project\\Actual-develop\\Tlias-educate-management\\upload\\" + destFilename));
//        return Result.success();
//    }

    //这个是使用阿里云OSS的存储
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());

        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传完成,地址：{}",url);
        return Result.success(url);
    }
}
