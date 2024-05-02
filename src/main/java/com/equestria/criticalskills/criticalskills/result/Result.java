package com.equestria.criticalskills.criticalskills.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static<T> Result<T> success() {
        Result<T>result=new Result<T>();
        result.setCode(1);
        return result;
    }

    public static<T> Result<T> success(T object) {
        Result<T> result=new Result<T>();
        result.setCode(1);
        result.setData(object);
        return result;
    }

    public static<T> Result<T> error(String msg) {
        Result<T> result=new Result<T>();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

}
