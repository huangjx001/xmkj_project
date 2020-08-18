package com.zz.xmkj.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.xmkj.dao.UserDao;
import com.zz.xmkj.domain.Role;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.vo.RoleMenuInfoVo;
import com.zz.xmkj.vo.UserRoleInfoVo;
import com.zz.xmkj.domain.Menu;


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
    public List<Role> getRoleInfo(String userName)
    {
        List<Role> roleList = userDao.findRolesByUsername(userName);
        return roleList;
    }

    @Override
    public UserRoleInfoVo getUserRoleMenu(String userName)
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
            List<RoleMenuInfoVo> roleMenus = new ArrayList<RoleMenuInfoVo>();
            roles.stream().forEach(a -> {
                RoleMenuInfoVo roleMenuInfoVo = new RoleMenuInfoVo();
                roleMenuInfoVo.setRoleName(a.getRoleName());
                List<Menu> menus = userDao.findMenusByRoleId(a.getId());
                if (CollectionUtils.isNotEmpty(menus))
                {
                    roleMenuInfoVo.setMenus(menus);
                }
                roleMenus.add(roleMenuInfoVo);
            });
            UserRoleInfoVo.setRoleMenu(roleMenus);
        }
        return UserRoleInfoVo;
    }

    @Override
    @Cacheable(value = "userMenuCache", key = "#userName")
    public List<Menu> getMenus(String userName)
    {
        List<Menu> menus = userDao.getMenus(userName);
        return menus;
    }

}