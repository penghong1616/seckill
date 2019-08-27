package com.ph.miaosha.redis;

public interface KeyPrefix {
    public int getExpireSecond();
    public String getPrefix();
}
