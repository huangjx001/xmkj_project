package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 角色菜单表
 * 
 * @author huangjx
 */

@TableName("uaa_role_permission")
@Data
@ApiModel(value = "角色菜单表", description = "角色菜单表")
public class RolePermission
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("权限表ID")
    @Column(name = "permission_id")
    private String permissionId;

    @ApiModelProperty("角色ID")
    @Column(name = "role_id")
    private String roleId;

}