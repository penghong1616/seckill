package com.ph.miaosha.result;

/**
 * 对返回结果进行封装
 */
public class Result <T>{
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.data = data;
        this.code=0;
        this.msg="success";
    }

    public Result(CodeMsg cm) {
        if (cm==null)
            return;
        this.code=cm.getCode();
        this.msg=cm.getMsg();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
