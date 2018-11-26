package com.github.carniwar.springboot.scalable.api.enumeration;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "MessageCode", description = "Message codes to be thrown by a BusinessException in result of an business rule violation")
public enum MessageCode {

    DATA_NOT_BASE64,
    DIFF_DATA_NOT_FOUND,
    DIFF_LEFT_DATA_BLANK,
    DIFF_RIGHT_DATA_BLANK
    ;

}
