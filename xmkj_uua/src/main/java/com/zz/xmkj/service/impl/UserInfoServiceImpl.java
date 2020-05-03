package com.zz.xmkj.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.xmkj.dao.UserDao;
import com.zz.xmkj.domain.Role;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.vo.RolePermissionInfoVo;
import com.zz.xmkj.vo.UserRoleInfoVo;
import com.zz.xmkj.domain.Permission;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserDao, UserInfo> implements UserInfoService
{
    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getUserInfo(String userName)
    {
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", userName);
        UserInfo userInfo = userDao.selectOne(qw);
        return userInfo;
    }

    @Override
    public UserRoleInfoVo getUserRolePermission(String userName)
    {
        QueryWrapper<UserInfo> qw = new QueryWrapper<UserInfo>();
        qw.eq("user_name", userName);
        UserInfo userInfo = userDao.selectOne(qw);
        if (null == userInfo)
        {
            return null;
        }
        UserRoleInfoVo UserRoleInfoVo = new UserRoleInfoVo();
        UserRoleInfoVo.setUserInfo(userInfo);

        // 获取角色信息
        List<Role> roles = userDao.findRolesByUsername(userName);
        if (CollectionUtils.isNotEmpty(roles))
        {
            List<RolePermissionInfoVo> rolePermission = new ArrayList<RolePermissionInfoVo>();
            roles.stream().forEach(a -> {
                RolePermissionInfoVo rolePermissionInfoVo = new RolePermissionInfoVo();
                rolePermissionInfoVo.setRoleName(a.getRoleName());
                List<Permission> permissions = userDao.findPermissionsByRoleId(a.getId());
                if (CollectionUtils.isNotEmpty(permissions))
                {
                    rolePermissionInfoVo.setPermissions(permissions);
                }
                rolePermission.add(rolePermissionInfoVo);
            });
            UserRoleInfoVo.setRolePermission(rolePermission);
        }
        return UserRoleInfoVo;
    }

}