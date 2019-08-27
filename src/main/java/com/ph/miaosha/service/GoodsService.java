package com.ph.miaosha.service;

import com.ph.miaosha.dao.GoodsDao;
import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    public List<GoodsVo> listGoods(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goodsVo) {
        Goods goods=new Goods();
        goods.setId(goodsVo.getId());
        goods.setGoodsStock(goodsVo.getStockCount()-1);
        goodsDao.reduceStock(goods);
    }
}
