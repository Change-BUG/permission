package com.ChangeBUG.model.system;

import com.ChangeBUG.model.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "permission.sys_department")
@ApiModel(value = "管理端部门-实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDepartment extends BaseEntity {

    /**
     * 部门名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "部门名称")
    private String name;

    /**
     * 部门权限
     */
    @TableField(value = "permission")
    @ApiModelProperty(value = "部门权限")
    private String permission;

}