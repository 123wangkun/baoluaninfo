package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/system")
@Api(value = "知识管理后台API", description = "菜单管理", tags = {"MenuController"})
public class MenuController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IMenuService service;

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @RequestMapping(value = "/deleteMenu/{menuId}", method = RequestMethod.GET)
    public void deleteRole(@PathVariable("menuId") String menuId, HttpServletRequest request) {
//        service.deleteRole(roleId);
    }
}
