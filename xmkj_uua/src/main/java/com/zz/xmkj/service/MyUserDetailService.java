package com.zz.xmkj.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zz.xmkj.vo.RolePermissionInfoVo;
import com.zz.xmkj.vo.UserRoleInfoVo;
import com.zz.xmkj.domain.Permission;


@Service
public class MyUserDetailService implements UserDetailsService
{

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据用户名加载权限信息
     */
    @Override
    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException
    {
        UserRoleInfoVo userRoleInfoVo = userInfoService.getUserRolePermission(userName);
        if (userRoleInfoVo == null)
        {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (RolePermissionInfoVo rolePermission : userRoleInfoVo.getRolePermission())
        {
            // 角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                rolePermission.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            // 获取权限
            for (Permission permission : rolePermission.getPermissions())
            {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUrlPath());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(userName, userRoleInfoVo.getUserInfo().getPassword(), enabled,
            accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }

}
