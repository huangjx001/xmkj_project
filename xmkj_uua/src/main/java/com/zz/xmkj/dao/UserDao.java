package com.zz.xmkj.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zz.xmkj.domain.Menu;
import com.zz.xmkj.domain.Role;
import com.zz.xmkj.domain.UserInfo;


@Mapper
public interface UserDao extends BaseMapper<UserInfo>
{

    /**
     * 根据用户名获取角色信息
     * 
     * @param userName
     * @return
     */
    List<Role> findRolesByUsername(@Param("userName") String userName);

    /**
     * 根据角色ID获取对应的菜单
     * 
     * @param roleId
     * @return
     */
    List<Menu> findMenusByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户名获取对应的菜单
     * 
     * @param roleId
     * @return
     */
    List<Menu> getMenus(@Param("userName") String userName);
}