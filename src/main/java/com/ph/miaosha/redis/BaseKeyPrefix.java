package com.ph.miaosha.redis;

public abstract class BaseKeyPrefix implements  KeyPrefix {
    private int expireSeconds;
    private String prefix;

    public BaseKeyPrefix(String prefix) {//默认0，永不过期
        this(0,prefix);
    }

    public BaseKeyPrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int getExpireSecond() {//默认0，永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+prefix;
    }
}
