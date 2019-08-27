package com.ph.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;
    //通用异常
    public static CodeMsg SUCCESS=new CodeMsg(0,"SUCCESS");
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务器端异常");
    public static CodeMsg BIND_ERROR=new CodeMsg(500101,"数据校验异常：%s");
    public static CodeMsg SESSION_ERROR=new CodeMsg(500210,"Session不能存在或已经失效");
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500211,"密码不能为空");
    public static CodeMsg MOBILE_EMPTY=new CodeMsg(500212,"电话号码不能为空");
    public static CodeMsg MOBILE_ERROR=new CodeMsg(500213,"电话号码错误");
    public static CodeMsg MOBILE_NOT_EXT=new CodeMsg(500214,"电话号码不存在");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500215,"密码错误");
    //登录模块
    //秒杀模块5005XX
    public static CodeMsg  SRC_KILL_OVER=new CodeMsg(500500,"商品秒杀完毕");
    public static CodeMsg REPEATE_SERCKILL=new CodeMsg(500501,"不能重复秒杀");
    private CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
    public CodeMsg fillArgs(Object...args){
        int code=this.code;
        String message=String.format(this.msg,args);
        return new CodeMsg(code,message);
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
}
