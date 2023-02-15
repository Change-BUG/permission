package com.ChangeBUG.model.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "基本数据-实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    /**
     * 编号
     */
    @TableId(value = "_id", type = IdType.AUTO)
    @ApiModelProperty(value = "编号", required = true)
    private Integer _id;

    /**
     * 描述
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "introduction")
    @ApiModelProperty(value = "描述")
    private String introduction;

    /**
     * 状态
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "status")
    @ApiModelProperty(value = "状态 1正常 2停用 3删除")
    private Integer status;

    /**
     * 创建时间
     */
    // 转为JSON不输出 对象可以接受
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateTineSerialize.class)
    @TableField(value = "add_time")
    @ApiModelProperty(value = "创建时间")
    private Date add_time;

    /**
     * 修改时间
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateTineSerialize.class)
    @TableField(value = "upd_time")
    @ApiModelProperty(value = "修改时间")
    private Date upd_time;

}
