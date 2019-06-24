package com.zl.school.business.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CommonService {
    /**
     * 证书上传
     * @return
     */
    Map upload(MultipartFile multipartFile, String type);
    /**
     * 删除七牛云上的资源
     * @return
     */
    boolean deleteResources(String resourceName);
}
