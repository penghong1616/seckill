package com.ph.miaosha.controller;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.result.CodeMsg;
import com.ph.miaosha.result.Result;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.service.OrderService;
import com.ph.miaosha.service.SecKillService;
import com.ph.miaosha.vo.GoodsOrderVo;
import com.ph.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;
    @RequestMapping("/detail")
    @ResponseBody
    public Result<GoodsOrderVo> detail(Model model, User user,  Long goodsId){
        OrderInfo orderInfo=orderService.getOrderInfoByUserIdAndGoodsId(user.getId(),goodsId);
        Goods goods=goodsService.getGoodsVoByGoodsId(goodsId);
        GoodsOrderVo goodsOrderVo=new GoodsOrderVo();
        goodsOrderVo.setGoods(goods);
        goodsOrderVo.setOrderInfo(orderInfo);
        return Result.success(goodsOrderVo);
    }
}
