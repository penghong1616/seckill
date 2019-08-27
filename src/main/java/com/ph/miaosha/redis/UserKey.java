package com.ph.miaosha.redis;

public class UserKey extends  BaseKeyPrefix {
    public static final int TOKEN_EXPIRE=3600*24*2;//两天
    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static UserKey getById=new UserKey(TOKEN_EXPIRE,"id");
    public static UserKey token=new UserKey(TOKEN_EXPIRE,"tk");
    public static UserKey getByname=new UserKey(TOKEN_EXPIRE,"name");

}
