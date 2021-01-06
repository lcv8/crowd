package com.crowd.util;

import java.io.Serializable;

/**
 * @author
 * 用于统一项目中所有 Ajax 请求的返回值类型
 * */
public class ResultEntity<T> implements Serializable {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";
    //用来封装当前请求处理的结果是成功还是失败
    private String result;
    //请求处理失败时，返回的错误消息
    private String message;
    //要返回的数据
    private T data;

    public String getResult() {
        System.out.println("调用getResult");
        return result;
    }

    public void setResult(String result) {
        System.out.println("调用setResult");
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //请求处理成功且不需要返回数据时使用的工具方法
    public static <T> ResultEntity<T> successWithoutData() {
        return new ResultEntity<T>(SUCCESS, null, null);
    }
    //请求处理成功且需要数据时使用的工具方法  要返回的数据
    public static <T> ResultEntity<T> successWithData(T data) {
        return new ResultEntity<T>(SUCCESS, null, data);
    }
    //请求处理失败后使用的工具方法   失败的错误消息
    public static <T> ResultEntity<T> failed(String message) {
        return new ResultEntity<T>(FAILED, message, null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        System.out.println("调用hou===》"+result);
        this.result = result;
        this.message = message;
        this.data = data;
    }
    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
