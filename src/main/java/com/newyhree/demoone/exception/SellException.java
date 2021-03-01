package com.newyhree.demoone.exception;

import com.newyhree.demoone.enums.ResultEnum;

public class SellException extends Exception {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super( resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
