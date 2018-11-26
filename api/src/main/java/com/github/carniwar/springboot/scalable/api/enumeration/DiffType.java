package com.github.carniwar.springboot.scalable.api.enumeration;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "DiffType", description = "Types of data to be diffed")
public enum DiffType {

    LEFT,
    RIGHT,
    ;

}
