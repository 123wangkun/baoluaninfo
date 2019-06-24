package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.system.ParamMapper;
import com.zl.school.business.service.CommonService;
import com.zl.school.business.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class CommonServiceImpl  implements CommonService {
    @Autowired
    private ParamMapper paramMapper;

    /**
     * 证书上传
     * @return
     */
    @Override
    public Map upload(MultipartFile multipartFile, String type){
        QiniuUtils qinNiu = new QiniuUtils();
        //判断上传的文件是否为空
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new BusinessException(ErrorCode.ERROR_FILE, "上传的图片为空，请重新上传");
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException(ErrorCode.ERROR_FILE, "文件的contentType值为空");
        }
        if (multipartFile.getSize() > 2 * 1024 * 1024) {
            // 文件大于2M 不让上传
            throw new BusinessException(ErrorCode.ERROR_FILE, "只允许上传小于2M的文件");
        }
        Long fileSize = multipartFile.getSize();
        String rootFileName = multipartFile.getOriginalFilename();
        String upToken = qinNiu.getQiNiuToken();

        //设置保存路径
        String path = "/u01/app/deecard/tmp";
        File dest = new File(path + "/" + rootFileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        try{
            multipartFile.transferTo(dest.getCanonicalFile());
        }catch (IllegalStateException e) {
            throw new BusinessException(ErrorCode.ERROR_FILE, "文件过大,内存溢出异常");
        }catch (IOException e) {
            throw new BusinessException(ErrorCode.ERROR_FILE, "文件路径错误,IO异常");
        }
        //如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        String res = qinNiu.upFileToQiNiuZone(upToken, dest.getAbsolutePath(), dest.getName().substring(dest.getName().lastIndexOf(".")));
        dest.delete();
        Map result = new HashMap();
        result.put("url", paramMapper.selectByName("QiniuUrl") + "/" + res);
        result.put("fileSize", fileSize/(1024));
        result.put("type", type);
        result.put("name", rootFileName);
        return result;
    }

    /**
     * 删除七牛云的资源
     *
     * @param resourceName
     * @return
     */
    @Override
    public boolean deleteResources(String resourceName) {
        QiniuUtils qinNiu = new QiniuUtils();
        // 从Url中提取文件名
        String fileName = resourceName.replace(paramMapper.selectByName("QiniuUrl") + "/", "");
        return qinNiu.deteteBucketFile(fileName);
    }
}
