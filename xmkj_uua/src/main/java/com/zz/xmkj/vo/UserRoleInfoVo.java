package com.zz.xmkj.vo;


import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.zz.xmkj.domain.UserInfo;


/**
 * 用户角色结构
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "用户角色结构", description = "用户角色结构")
public class UserRoleInfoVo
{
    private UserInfo UserInfo;

    private List<RolePermissionInfoVo> rolePermission;

}