package com.ph.miaosha.redis;

public class OrderKey extends BaseKeyPrefix {
    public static final OrderKey getSecKillOrderByUidGid=new OrderKey(0,"gskbg");

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
