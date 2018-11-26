package com.github.carniwar.springboot.scalable.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Transfer object for returning result of diff operation for left and right data for an ID")
public class DiffResultDTO {

    @ApiModelProperty(notes = "Id of the diffed entity", required = true)
    private String id;

    @ApiModelProperty(notes = "Left and Right have the same data", required = true)
    private Boolean equals;

    @ApiModelProperty(notes = "Left and Right have the same size", required = true)
    private Boolean size;

    @ApiModelProperty(notes = "First index of RIGHT data that contains a difference from LEFT data", required = true)
    private Integer indexFirstDiff;

}