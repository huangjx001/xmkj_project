package com.zz.xmkj.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import com.zz.xmkj.vo.RolePermissionInfoVo;
import com.zz.xmkj.vo.UserRoleInfoVo;
import com.zz.xmkj.vo.security.IntegrationAuthenticationEntity;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zz.xmkj.domain.Permission;
import com.zz.xmkj.domain.UserInfo;
import com.zz.xmkj.oauth.IntegrationAuthenticationContext;
import com.zz.xmkj.oauth.IntegrationAuthenticator;


@Service
public class MyUserDetailService implements UserDetailsService
{

    @Autowired
    private UserInfoService userInfoService;

    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators)
    {
        this.authenticators = authenticators;
    }

    /**
     * 根据用户名加载权限信息
     */
    @Override
    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException
    {
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();
        if (entity == null)
        {
            entity = new IntegrationAuthenticationEntity();
        }

        UserInfo pojo = this.authenticate(entity);
        if (pojo == null)
        {
            throw new UsernameNotFoundException("此账号不存在!");
        }
        userName = pojo.getUserName();
        UserRoleInfoVo userRoleInfoVo = userInfoService.getUserRolePermission(userName);

        if (userRoleInfoVo == null)
        {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (CollectionUtils.isNotEmpty(userRoleInfoVo.getRolePermission()))
        {
            for (RolePermissionInfoVo rolePermission : userRoleInfoVo.getRolePermission())
            {
                if (CollectionUtils.isNotEmpty(rolePermission.getPermissions()))
                {
                    // 角色必须是ROLE_开头，可以在数据库中设置
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                        rolePermission.getRoleName());
                    grantedAuthorities.add(grantedAuthority);
                    // 获取权限
                    for (Permission permission : rolePermission.getPermissions())
                    {
                        GrantedAuthority authority = new SimpleGrantedAuthority(
                            permission.getUrlPath());
                        grantedAuthorities.add(authority);
                    }
                }

            }
        }
        User user = new User(userName, userRoleInfoVo.getUserInfo().getPassword(), true, true,
            true, true, grantedAuthorities);
        return user;
    }

    private UserInfo authenticate(IntegrationAuthenticationEntity entity)
    {
        if (this.authenticators != null)
        {
            for (IntegrationAuthenticator authenticator : authenticators)
            {
                if (authenticator.support(entity))
                {
                    return authenticator.authenticate(entity);
                }
            }
        }
        throw new OAuth2Exception("无效的auth_type参数值！");
    }

}
