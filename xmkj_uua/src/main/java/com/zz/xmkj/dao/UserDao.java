package com.zz.xmkj.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zz.xmkj.domain.Permission;
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
    List<Role> findRolesByUsername(String userName);

    /**
     * 根据角色ID获取对应的菜单权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> findPermissionsByRoleId(String roleId);
}