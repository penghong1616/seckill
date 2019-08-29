package com.ph.miaosha.redis;

public class GoodsKey extends BaseKeyPrefix {
    public GoodsKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }
    public static GoodsKey getGoodsList=new GoodsKey(60,"gl");
    public static GoodsKey getDetail=new GoodsKey(60,"gd");
    public static GoodsKey getSecKillGoodsStock=new GoodsKey(0,"gs");
}
