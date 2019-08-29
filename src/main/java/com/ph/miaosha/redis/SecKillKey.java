package com.ph.miaosha.redis;

public class SecKillKey extends  BaseKeyPrefix {
    public SecKillKey(String prefix) {
        super(prefix);
    }
    public static final SecKillKey  isGoodsOver=new SecKillKey("go");
}
