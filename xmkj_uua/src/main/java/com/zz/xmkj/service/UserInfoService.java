package com.zz.xmkj.service;


import com.baomidou.mybatisplus.extension.service.IService;
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
     * 根据用户名获取相关用户信息，角色信息以及权限信息
     * 
     * @param userName
     * @return
     */
    UserRoleInfoVo getUserRolePermission(String userName);
}