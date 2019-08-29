package com.ph.miaosha.dao;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao{
    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.seckill_price from sk_goods_seckill mg left join sk_goods  g on mg.goods_id =g.id")
    public List<GoodsVo> listGoodsVo();
    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.seckill_price from sk_goods_seckill mg left join sk_goods  g on mg.goods_id =g.id where g.id=#{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
    @Update("update sk_goods_seckill set stock_count=stock_count-1 where goods_id=#{id}")
    public int  reduceStock(Goods goods);
}
