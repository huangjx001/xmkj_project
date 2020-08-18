package com.zz.xmkj.dao;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zz.xmkj.domain.Menu;


/**
 * 菜单Dao层接口
 * 
 * @author 001
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu>
{

}