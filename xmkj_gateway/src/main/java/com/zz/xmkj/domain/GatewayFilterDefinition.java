package com.zz.xmkj.domain;


import java.util.LinkedHashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * 过滤器模型
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "过滤器模型", description = "过滤器模型")
public class GatewayFilterDefinition
{
    // Filter Name
    private String name;

    // 对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}