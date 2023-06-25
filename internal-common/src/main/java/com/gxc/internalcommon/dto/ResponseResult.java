package com.gxc.internalcommon.dto;

import com.gxc.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 返回无参成功的方法
     * @param <T>
     * @return
     */
    public static  <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    public static ResponseResult fail(CommonStatusEnum commonStatusEnum){
        return new ResponseResult().setCode(commonStatusEnum.getCode()).setMessage(commonStatusEnum.getValue());
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
