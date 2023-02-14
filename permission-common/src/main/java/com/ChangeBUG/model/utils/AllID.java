package com.ChangeBUG.model.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "编号-承载类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllID {

    @ApiModelProperty(value = "编号")
    private String _id;

}
