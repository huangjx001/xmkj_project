package com.zz.xmkj.rest;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.xmkj.data.R;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.enums.ErrorCode;
import com.zz.xmkj.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("/userInfo")
@Api(value = "用户相关接口", tags = "用户相关接口")
public class UserController
{
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/register")
    @PreAuthorize("permitAll")
    public R register(@RequestBody UserInfo userInfo)
    {
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", userInfo.getUserName());
        UserInfo user = userInfoService.getOne(qw);
        if (null != user)
        {
            return new R(ErrorCode.USER_IS_EXIST);
        }
        String password = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(password);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(new Date());
        userInfo.setCreateTime(currentTime);
        userInfoService.save(userInfo);
        return new R(ErrorCode.SUCCESS);
    }

    @ApiOperation(value = "登录用户", notes = "登录用户")
    @PostMapping("/login")
    public R login(@RequestBody UserInfo userInfo)
    {
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", userInfo.getUserName().trim());
        UserInfo user = userInfoService.getOne(qw);
        if (null == user)
        {
            return new R(ErrorCode.USER_NOT_EXIST);
        }
        String password = bCryptPasswordEncoder.encode(userInfo.getPassword());
        if (!password.equals(user.getPassword()))
        {
            return new R(ErrorCode.USER_PASSWORD_ERROR);
        }
        user.setPassword("******");
        return new R(user, ErrorCode.SUCCESS);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @GetMapping("/queryUserInfo")
    public R queryUserInfo(@RequestParam("userName") String userName)
    {
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", userName);
        UserInfo user = userInfoService.getOne(qw);
        if (null == user)
        {
            return new R(ErrorCode.USER_NOT_EXIST);
        }
        user.setPassword("******");
        return new R(user, ErrorCode.SUCCESS);
    }
    
    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping("/test")
    public R test()
    {
        return new R( ErrorCode.SUCCESS);
    }

}