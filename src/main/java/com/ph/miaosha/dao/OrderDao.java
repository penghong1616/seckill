package com.ph.miaosha.dao;

import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
    @Select("select * from sk_order where user_id=#{userId} and goods_id=#{goodsId}")
    public SecKillOrder getSecKillOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId) ;
    @Insert("insert into sk_order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,status,create_date) " +
            "values(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);
    @Insert("insert into sk_order(user_id,goods_id,order_id) values(#{userId},#{goodsId},#{orderId})")
    void insertSecKillOrder(SecKillOrder secKillOrder);
    @Select("select * from sk_order_info where user_id=#{userId} and goods_Id=#{goodsId}")
    public OrderInfo getOrderInfoByUserIdAndGoodsId(Long userId, Long goodsId);
}
