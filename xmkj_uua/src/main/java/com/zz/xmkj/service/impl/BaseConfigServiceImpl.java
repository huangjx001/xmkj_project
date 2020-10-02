package com.zz.xmkj.service.impl;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.xmkj.dao.BaseConfigDao;
import com.zz.xmkj.service.BaseConfigService;
import com.zz.xmkj.domain.BaseConfig;
import com.zz.xmkj.constant.UuaConstant;


/**
 * 基础配置相关接口实现类
 * 
 * @author 001
 */
@Service
public class BaseConfigServiceImpl extends ServiceImpl<BaseConfigDao, BaseConfig> implements BaseConfigService
{
    @Autowired
    private BaseConfigDao baseConfigDao;

    /**
     * 获取配置的值
     * 
     * @param moduleName
     * @param moduleKeyName
     * @return
     */
    @Cacheable(value = UuaConstant.BASE_CONFIG_CACHE_KEY, key = "#moduleName+'-'+#moduleKeyName")
    public String getConfigValue(String moduleName, String moduleKeyName)
    {
        QueryWrapper<BaseConfig> qw = new QueryWrapper<BaseConfig>();
        qw.eq("module_name", moduleName);
        qw.eq("moduleKeyName", moduleKeyName);
        BaseConfig config = baseConfigDao.selectOne(qw);
        if (null != config)
        {
            return config.getModuleKeyValue();
        }
        return "";
    }

    /**
     * 获取模块名下的列表值
     * 
     * @param moduleName
     * @return
     */
    public Map<String, String> getConfigMap(String moduleName)
    {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        QueryWrapper<BaseConfig> qw = new QueryWrapper<BaseConfig>();
        qw.eq("module_name", moduleName);
        List<BaseConfig> configList = baseConfigDao.selectList(qw);
        if (CollectionUtils.isNotEmpty(configList))
        {
            configList.stream().forEach(a -> {
                map.put(a.getModuleKeyName(), a.getModuleKeyValue());
            });
        }
        return map;
    }
}