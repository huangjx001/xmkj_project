package com.zz.xmkj.common.domain;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 菜单树结构
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "用户角色结构", description = "用户角色结构")
public class TreeNode
{
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("菜单路径")
    private String menuUrl;

    @ApiModelProperty("父目录ID")
    private String parentId;

    @ApiModelProperty("排序序号")
    private int orderNum;

    @ApiModelProperty("菜单名")
    private String menuName;

    @ApiModelProperty("权限设置,针对按钮级别的权限控制 菜单ID:action:Add,delete,query,update:Number")
    private String authorityLimit;

    @ApiModelProperty("创建的时间")
    private String createTime;

    @ApiModelProperty("创建人")
    private String createUserId;

    @ApiModelProperty("图标地址")
    private String iconUrl;

    @ApiModelProperty("节点类型,1:文件夹 2:页面 3按钮")
    private String nodeType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否选中")
    private boolean select = true;

    @ApiModelProperty("子节点")
    private List<TreeNode> children;

}