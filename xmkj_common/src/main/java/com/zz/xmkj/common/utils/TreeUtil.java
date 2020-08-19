package com.zz.xmkj.common.utils;


import java.util.ArrayList;
import java.util.List;

import com.zz.xmkj.common.domain.TreeNode;


/**
 * 组成树工具类
 * 
 * @author huangjx
 */
public class TreeUtil
{
    /**
     * 两层循环实现建树
     * 
     * @param treeNodes
     *            传入的树节点列表
     * @return
     */
    public static List<TreeNode> bulid(List<TreeNode> treeNodes)
    {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes)
        {
            if ("0".equals(treeNode.getParentId()))
            {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes)
            {
                if (it.getParentId().equals(treeNode.getId()))
                {
                    if (treeNode.getChildren() == null)
                    {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }
}
