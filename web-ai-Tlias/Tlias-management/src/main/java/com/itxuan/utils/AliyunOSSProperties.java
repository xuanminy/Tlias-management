package com.itxuan.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//批量将多个属性注入到bean对象中
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
