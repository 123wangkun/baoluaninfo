package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.system.DeleteResourcesReq;
import com.zl.school.business.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/system")
@Api(value = "common", description = "文件上传接口", tags = {"CommonController"})
public class CommonController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(SmsCodeController.class);

    @Autowired
    private CommonService commonService;

    /**
     * upload 图片上传方法
     *
     * @throws
     */
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @RequestMapping(value = "/upload_files/{type}", method = RequestMethod.POST)
    public Map upload(@RequestParam("editormd-image-file") MultipartFile multipartFile,@PathVariable("type") String type){
        return commonService.upload(multipartFile,type);
    }

    /**
     * 删除七牛云上的资源
     */
    @ApiOperation(value = "删除七牛云上的资源", notes = "删除七牛云上的资源")
    @ApiImplicitParam(name = "resouceName", value = "修改用户", required = true, dataType = "DeleteResourcesReq")
    @RequestMapping(value = "/deleteResources", method = RequestMethod.POST)
    public boolean deleteResources(@RequestBody DeleteResourcesReq resouceName) {
        return commonService.deleteResources(resouceName.getFileUrl());
    }
}
