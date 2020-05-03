package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * uaa菜单权限表
 * 
 * @author huangjx
 */

@TableName("uaa_url_permission")
@Data
@ApiModel(value = "uaa菜单权限表", description = "uaa菜单权限表")
public class Permission
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("模块名称")
    @Column(name = "url_name")
    private String urlName;

    @ApiModelProperty("菜单路径")
    @Column(name = "url_path")
    private String urlPath;

    @ApiModelProperty("所属父ID")
    @Column(name = "parent_id")
    private String parentId;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

}