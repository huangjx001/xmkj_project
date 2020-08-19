package com.zz.xmkj.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zz.xmkj.common.domain.TreeNode;
import com.zz.xmkj.domain.Menu;


public interface MenuService extends IService<Menu>
{
    /**
     * 根据用户名获取用户菜单信息
     * 
     * @param userName
     * @return
     */
    List<TreeNode> getMenuInfo(String userName);

}