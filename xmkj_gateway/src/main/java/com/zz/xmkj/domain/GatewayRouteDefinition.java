package com.zz.xmkj.domain;


import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * 路由配置基础类
 * 
 * @author huangjx
 */

@Data
@ApiModel(value = "路由配置基础类", description = "路由配置基础类")
public class GatewayRouteDefinition
{
    // 路由的Id
    private String id;

    // 路由规则转发的目标uri
    private String uri;

    // 路由执行的顺序
    private int order = 0;

    // 路由断言集合配置
    private List<GatewayPredicateDefinition> predicates = new ArrayList<GatewayPredicateDefinition>();

    // 路由过滤器集合配置
    private List<GatewayFilterDefinition> filters = new ArrayList<GatewayFilterDefinition>();

}