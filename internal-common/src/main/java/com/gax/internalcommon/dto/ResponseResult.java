package com.gax.internalcommon.dto;

import com.gax.internalcommon.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 统一的失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    /**
     * 失败:自定义 错误码和提示信息
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code, String message,String data){
        return new ResponseResult().setData(code).setMessage(message).setData(data);
    }
}
