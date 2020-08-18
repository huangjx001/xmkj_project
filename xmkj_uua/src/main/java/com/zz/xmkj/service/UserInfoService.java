package com.zz.xmkj.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zz.xmkj.domain.Menu;
import com.zz.xmkj.domain.Role;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.vo.UserRoleInfoVo;


public interface UserInfoService extends IService<UserInfo>
{
    /**
     * 根据用户名获取用户信息
     * 
     * @param userName
     * @return
     */
    UserInfo getUserInfo(String userName);

    /**
     * 根据用户名获取角色信息
     * 
     * @param userName
     * @return
     */
    List<Role> getRoleInfo(String userName);

    /**
     * 根据用户名获取相关用户信息，角色信息以及菜单信息
     * 
     * @param userName
     * @return
     */
    UserRoleInfoVo getUserRoleMenu(@Param("userName") String userName);

    /**
     * 根据用户名获取菜单信息
     * 
     * @param userName
     * @return
     */
    List<Menu> getMenus(String userName);
}