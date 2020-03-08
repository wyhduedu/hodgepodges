package com.wy.hodgepodges.sdk.response.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author heqiang
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 4161721150157143007L;
    private Integer code;
    private String message;
    private List<T> date;
    private Boolean isSuceess;

    public Response() {
    }

    /**
     * 返回数据
     * @param data
     */
    public static Response create(List data){
        Response response=new Response();
        response.setDate(data);
        response.setCode(200);
        response.setMessage("");
        response.setIsSuceess(Boolean.TRUE);
        return response;
    }

    /**
     * 返回错误信息
     * @param
     */
    public static Response create(String message, Integer code){
        Response response=new Response();
        response.setDate(null);
        response.setCode(code);
        response.setMessage(message);
        response.setIsSuceess(Boolean.FALSE);
        return response;
    }

    /**
     * 返回错误信息
     * @param
     */
    public static Response create(Boolean status, String message, Integer code){
        Response response=new Response();
        response.setDate(null);
        response.setCode(code);
        response.setMessage(message);
        response.setIsSuceess(status);
        return response;
    }


    /**
     * 返回结果
     * @param
     */
    public static Response create(){
        Response response=new Response();
        response.setDate(null);
        response.setCode(200);
        response.setMessage("成功");
        response.setIsSuceess(Boolean.TRUE);
        return response;
    }


}
