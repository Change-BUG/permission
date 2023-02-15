package com.ChangeBUG.model.system;

import com.ChangeBUG.model.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "permission.sys_resource")
@ApiModel(value = "后台接口权限表-实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysResource extends BaseEntity {

    /**
     * 接口名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "接口名称")
    private String name;

    /**
     * 接口URL
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "接口URL")
    private String url;

    /**
     *  接口权限
     */
    @TableField(value = "permission")
    @ApiModelProperty(value = "接口权限")
    private String permission;

}
