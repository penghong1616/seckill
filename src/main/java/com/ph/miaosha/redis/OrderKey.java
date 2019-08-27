package com.ph.miaosha.redis;

public class OrderKey extends BaseKeyPrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
