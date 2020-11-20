package com.myappbbsbackend.planningcontrol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;

/**
 * @ Description: 规范返回数据的格式
 * @ Author: 小火锅
 * @ Date: 2020/11/19 14:13
 */
@Data
public class ApiResp<T> implements Serializable {
    private static final long serialVersionUID = 123456789L;
    /**
     * 正常响应码
     */
    private static final int SUCCESS_CODE = 200;
    /**
     * 正常响应消息
     */
    private static final String SUCCESS_MSG = "success";
    /**
     * 错误码
     */
    private int code = SUCCESS_CODE;
    /**
     * 错误信息
     */
    private String msg = SUCCESS_MSG;
    /**
     * 响应内容，默认为null
     */
    private T data = null;

    /**
     * 是否是正常响应
     */
    @JsonIgnore
    public boolean isOK() {
        return code == SUCCESS_CODE;
    }

    /**
     * 无 data 正常返回
     *  @return
     */
//    @org.jetbrains.annotations.NotNull
//    @org.jetbrains.annotations.Contract(" -> new")
    public static ApiResp retOK()
    {
        return new ApiResp();
    }

    /**
     * 有data 正常返回
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ApiResp<T> retOK(T data)
    {
        ApiResp<T> response = new ApiResp<>();
        response.setData(data);
        return response;
    }

    /**
     * 无 data 错误返回
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static<T> ApiResp<T> retFail(int code,String msg )
    {
        ApiResp<T> response =  new ApiResp<>();
        response.setCode(code);
        response.setMsg(msg);
        return  response;
    }

    /**
     * 有数据错误返回
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> retFail(T data,int code ,String msg)
    {
        ApiResp<T> response =  new ApiResp<>();
        response.setData(data);
        response.setCode(code);
        response.setMsg(msg);
        return  response;
    }




}
