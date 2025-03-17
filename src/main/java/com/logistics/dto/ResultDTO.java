package com.logistics.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果数据传输对象
 */
@Data
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 状态码
     */
    private Integer code;
    
    /**
     * 消息
     */
    private String message;
    
    /**
     * 数据
     */
    private T data;
    
    /**
     * 成功
     * @param data 数据
     * @return 返回结果
     */
    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }
    
    /**
     * 成功
     * @param message 消息
     * @param data 数据
     * @return 返回结果
     */
    public static <T> ResultDTO<T> success(String message, T data) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    
    /**
     * 失败
     * @param message 消息
     * @return 返回结果
     */
    public static <T> ResultDTO<T> error(String message) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
    
    /**
     * 失败
     * @param code 状态码
     * @param message 消息
     * @return 返回结果
     */
    public static <T> ResultDTO<T> error(Integer code, String message) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
} 