package com.zz.xmkj.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.xmkj.dao.MenuDao;
import com.zz.xmkj.service.MenuService;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.domain.Menu;


/**
 * 菜单相关接口实现类
 * 
 * @author 001
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService
{
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public List<Menu> getMenuInfo(String userName)
    {
        List<Menu> menus = userInfoService.getMenus(userName);
        return menus;
    }
}