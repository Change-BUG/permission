package com.ChangeBUG.model.system;


import com.ChangeBUG.model.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName(value = "permission.sys_user")
@ApiModel(value = "管理端用户-实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseEntity {

    @TableField(value = "avatar")
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @TableField(value = "account")
    @ApiModelProperty(value = "用户账号")
    private String account;

    // 转为JSON不输出 对象可以接受
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    @TableField(value = "password")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @TableField(value = "department")
    @ApiModelProperty(value = "用户部门")
    private String department;

}
