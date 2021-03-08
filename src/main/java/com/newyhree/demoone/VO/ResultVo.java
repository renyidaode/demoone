package com.newyhree.demoone.VO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/*
http返回的最外层对象
 */

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {
    //状态码
    private Integer code;

    //提示信息
    private String msg;

    //返回的具体内容
    private T data;
}
