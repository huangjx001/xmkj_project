package com.zz.xmkj.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.xmkj.dao.MenuDao;
import com.zz.xmkj.service.MenuService;
import com.zz.xmkj.service.UserInfoService;
import com.zz.xmkj.domain.Menu;
import com.zz.xmkj.enums.MenuNodeType;
import com.zz.xmkj.common.domain.TreeNode;
import com.zz.xmkj.common.utils.TreeUtil;


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
    public List<TreeNode> getMenuInfo(String userName)
    {
        List<Menu> menus = userInfoService.getMenus(userName);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        if (CollectionUtils.isNotEmpty(menus))
        {
            menus = menus.stream().filter(
                a -> !MenuNodeType.BUTTON.getCode().equals(a.getNodeType())).collect(
                    Collectors.toList());
            treeNodes = TreeUtil.bulid(covertToTreeList(menus));
        }
        return treeNodes;
    }

    /**
     * 转成树的列表结构
     * 
     * @param menus
     * @return
     */
    private List<TreeNode> covertToTreeList(List<Menu> menus)
    {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        menus.stream().forEach(a -> {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(a.getMenuId());
            treeNode.setMenuUrl(a.getMenuUrl());
            treeNode.setParentId(a.getParentId());
            treeNode.setOrderNum(a.getOrderNum());
            treeNode.setMenuName(a.getMenuName());
            treeNode.setAuthorityLimit(a.getAuthorityLimit());
            treeNode.setCreateTime(a.getCreateTime());
            treeNode.setCreateUserId(a.getCreateUserId());
            treeNode.setIconUrl(a.getIconUrl());
            treeNode.setNodeType(a.getNodeType());
            treeNode.setRemark(a.getRemark());
            treeNodes.add(treeNode);
        });
        return treeNodes;
    }
}