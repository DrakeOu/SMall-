package com.xjtuse.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author 失了秩
 * @date 2020/3/14 14:36
 * @description
 */
public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值(100MB)
        factory.setMaxFileSize(DataSize.ofBytes(100*1024*1024));
        /// 设置总上传数据总大小(1GB)
        factory.setMaxRequestSize(DataSize.ofBytes(1024*1024*1024));
        return factory.createMultipartConfig();
    }
}
