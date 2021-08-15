package com.aurora.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//返回给前端的通用json数据串
@Data   //set/get方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor  //无参构造器
public class CommonResult<T> {
    //最常见的错误  404  not_found  所以一般java的通用返回结果集是数字编码和异常信息
    //所以定义code 和 message 能够给前端一个良好的显示
    private Integer code;
    private String message;
    private T data; //泛型，对应类型的json数据

    //自定义两个参数的构造方法
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}