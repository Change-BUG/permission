package com.ChangeBUG.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@ApiModel(value = "返回数据定义-工具类")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespListUtils {

    @ApiModelProperty(value = "编号")
    private long code;

    @ApiModelProperty(value = "描述")
    private String message;

    @ApiModelProperty(value = "数据")
    private Object object;

    /**
     * 成功 返回 不带结果
     */
    public static RespListUtils success(String message) {
        return new RespListUtils(200, message, null);
    }

    /**
     * 成功 返回 结果
     */
    public static RespListUtils success(String message, Object object) {
        return new RespListUtils(200, message, object);
    }

    /**
     * 失败 返回 不带结果
     */
    public static RespListUtils error(String message) {
        return new RespListUtils(500, message, null);
    }

    /**
     * 失败 返回 结果
     */
    public static RespListUtils error(String message, Object object) {
        return new RespListUtils(500, message, object);
    }

}