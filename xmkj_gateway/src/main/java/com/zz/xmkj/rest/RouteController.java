package com.zz.xmkj.rest;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zz.xmkj.domain.GatewayFilterDefinition;
import com.zz.xmkj.domain.GatewayPredicateDefinition;
import com.zz.xmkj.domain.GatewayRouteDefinition;
import com.zz.xmkj.service.impl.DynamicRouteServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;


/**
 * 路由配置基础类
 * 
 * @author huangjx
 */

@RestController
@RequestMapping("/route")
@Api(value = "路由相关接口", tags = "路由相关接口")
public class RouteController
{

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    // 增加路由
    @PostMapping("/add")
    @ApiOperation(value = "新增路由", notes = "新增路由")
    public String add(@RequestBody GatewayRouteDefinition gwdefinition)
    {
        String flag = "fail";
        try
        {
            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            flag = this.dynamicRouteService.add(definition);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    // 删除路由
    @DeleteMapping("/routes/{id}")
    @ApiOperation(value = "删除路由", notes = "删除路由")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id)
    {
        try
        {
            return this.dynamicRouteService.delete(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 更新路由
    @PostMapping("/update")
    @ApiOperation(value = "修改路由", notes = "修改路由")
    public String update(@RequestBody GatewayRouteDefinition gwdefinition)
    {
        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(definition);
    }

    // 把传递进来的参数转换成路由对象
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition)
    {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(gwdefinition.getId());
        definition.setOrder(gwdefinition.getOrder());

        // 设置断言
        List<PredicateDefinition> pdList = new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gwdefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList)
        {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        // 设置过滤器
        List<FilterDefinition> filters = new ArrayList<FilterDefinition>();
        List<GatewayFilterDefinition> gatewayFilters = gwdefinition.getFilters();
        for (GatewayFilterDefinition filterDefinition : gatewayFilters)
        {
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri = null;
        if (gwdefinition.getUri().startsWith("http"))
        {
            uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
        }
        else
        {
            // uri为 lb://consumer-service 时使用下面的方法
            uri = URI.create(gwdefinition.getUri());
        }
        definition.setUri(uri);
        return definition;
    }
}