package com.zz.xmkj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * uua基础数据的配置表
 * 
 * @author huangjx
 */

@TableName("uua_base_config")
@Data
@ApiModel(value = "uua基础数据的配置表", description = "uua基础数据的配置表")
public class BaseConfig
{
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("模块名")
    @Column(name = "module_name")
    private String moduleName;

    @ApiModelProperty("配置项的名称")
    @Column(name = "module_key_name")
    private String moduleKeyName;

    @ApiModelProperty("配置项的值")
    @Column(name = "module_key_value")
    private String moduleKeyValue;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

}