package com.zz.xmkj.service;


import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zz.xmkj.domain.BaseConfig;


public interface BaseConfigService extends IService<BaseConfig>
{

    /**
     * 获取配置的值
     * 
     * @param moduleName
     * @param moduleKeyName
     * @return
     */
    String getConfigValue(String moduleName, String moduleKeyName);

    /**
     * 获取模块名下的列表值
     * 
     * @param moduleName
     * @return
     */
    Map<String, String> getConfigMap(String moduleName);
}