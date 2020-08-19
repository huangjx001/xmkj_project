package com.zz.xmkj.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zz.xmkj.common.data.R;
import com.zz.xmkj.common.domain.TreeNode;
import com.zz.xmkj.domain.Menu;
import com.zz.xmkj.common.enums.ErrorCode;
import com.zz.xmkj.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@SuppressWarnings({"rawtypes"})
@RestController
@RequestMapping("/menu")
@Api(value = "菜单相关接口", tags = "菜单相关接口")
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "新增或修改菜单", notes = "新增或修改菜单")
    @PostMapping("/addOrUpdateMenu")
    public R addMenu(@RequestBody Menu menu)
    {
        menuService.saveOrUpdate(menu);
        return new R(ErrorCode.SUCCESS);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/deleteMenu")
    public R deleteMenu(@RequestParam("menuId") String menuId)
    {
        menuService.removeById(menuId);
        return new R(ErrorCode.SUCCESS);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @PostMapping("/queryMenu")
    public R queryMenu(@RequestParam("userName") String userName)
    {
        List<TreeNode> treeNodes = menuService.getMenuInfo(userName);
        return new R(treeNodes, ErrorCode.SUCCESS);
    }
}