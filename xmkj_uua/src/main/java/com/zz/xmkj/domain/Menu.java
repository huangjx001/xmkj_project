package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 菜单表
 * 
 * @author huangjx
 */

@TableName("uaa_menu")
@Data
@ApiModel(value = "菜单表", description = "菜单表")
public class Menu
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("菜单路径")
    @Column(name = "menu_url")
    private String menuUrl;

    @ApiModelProperty("父目录ID")
    @Column(name = "parent_id")
    private String parentId;

    @ApiModelProperty("排序序号")
    @Column(name = "order_num")
    private int orderNum;

    @ApiModelProperty("菜单名")
    @Column(name = "menu_name")
    private String menuName;
    
    @ApiModelProperty("权限设置,针对按钮级别的权限控制 菜单ID:action:Add,delete,query,update:Number")
    @Column(name = "authority_limit")
    private String authorityLimit;
    
    @ApiModelProperty("创建的时间")
    @Column(name = "create_time")
    private String createTime;

    @ApiModelProperty("创建人")
    @Column(name = "create_user_id")
    private String createUserId;

    @ApiModelProperty("图标地址")
    @Column(name = "icon_url")
    private String iconUrl;

    @ApiModelProperty("节点类型,1:文件夹 2:页面 3按钮")
    @Column(name = "node_type")
    private String nodeType;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

}