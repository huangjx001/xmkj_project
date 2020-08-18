package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户表
 * 
 * @author huangjx
 */

@TableName("uaa_user")
@Data
@ApiModel(value = "用户表", description = "用户表")
public class UserInfo
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("登录的用户名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("手机号")
    @Column(name = "telphone")
    private String telphone;

    @ApiModelProperty("邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty("别名")
    @Column(name = "nick_name")
    private String nickName;

    @ApiModelProperty("创建的时间")
    @Column(name = "create_time")
    private String createTime;

    @ApiModelProperty("创建人")
    @Column(name = "create_user")
    private String createUser;

    @ApiModelProperty("简介")
    @Column(name = "bio")
    private String bio;
    
    @ApiModelProperty("0:启用 1:停用")
    @Column(name = "status")
    private String status;
    
}