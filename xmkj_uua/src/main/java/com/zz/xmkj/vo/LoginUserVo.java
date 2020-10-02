package com.zz.xmkj.vo;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户登录请求参数
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "用户登录请求参数", description = "用户登录请求参数")
public class LoginUserVo
{
    @ApiModelProperty("用户名或者手机号")
    private String userNameOrTelphone;

    @ApiModelProperty("密码")
    private String password;

}