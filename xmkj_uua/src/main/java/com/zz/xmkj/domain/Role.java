package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 角色表
 * 
 * @author huangjx
 */

@TableName("uaa_role")
@Data
@ApiModel(value = "角色表", description = "角色表")
public class Role
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("角色名")
    @Column(name = "role_name")
    private String roleName;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

}