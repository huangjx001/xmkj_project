package com.zz.xmkj.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

import com.zz.xmkj.domain.Permission;


/**
 * 角色菜单结构
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "角色菜单结构", description = "角色菜单结构")
public class RolePermissionInfoVo
{
    private String roleName;

    private List<Permission> permissions;
}