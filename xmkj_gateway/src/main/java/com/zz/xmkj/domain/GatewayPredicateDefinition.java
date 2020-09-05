package com.zz.xmkj.domain;


import java.util.LinkedHashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * 路由断言模型
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "路由断言模型", description = "路由断言模型")
public class GatewayPredicateDefinition
{
    // 断言对应的Name
    private String name;

    // 配置的断言规则
    private Map<String, String> args = new LinkedHashMap<>();
}