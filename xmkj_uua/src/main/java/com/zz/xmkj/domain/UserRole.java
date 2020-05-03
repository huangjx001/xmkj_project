package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户角色表
 * 
 * @author huangjx
 */

@TableName("uaa_user_role")
@Data
@ApiModel(value = "用户角色表", description = "用户角色表")
public class UserRole
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("用户表ID")
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty("角色ID")
    @Column(name = "role_id")
    private String roleId;

}