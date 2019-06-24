package ${package.Controller};

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.validation.annotation.Validated;
/**
 * @author ${author}
 */

@Validated
@RestController
@RequestMapping("/system")
@Api(value = "知识管理后台API", description = "用户角色--相关API", tags = {"${table.controllerName}"})
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ExceptionController {
    <#else>
public class ${table.controllerName} extends ExceptionController {
    </#if>
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ${table.serviceName} service;
}
</#if>
