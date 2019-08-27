package com.ph.miaosha.controller;

import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.result.CodeMsg;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.service.OrderService;
import com.ph.miaosha.service.SecKillService;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecKillController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;
    @RequestMapping("/secKill")
    public String secKill(Model model, User user,@RequestParam("goodsId")long goodsId){
        model.addAttribute("user",user);
        if (user==null)return "login/login";
        //判断库存
        GoodsVo goodsVo=goodsService.getGoodsVoByGoodsId(goodsId);
        int stock=goodsVo.getGoodsStock();
        if (stock<=0){
            model.addAttribute("errmsg", CodeMsg.SRC_KILL_OVER.getMsg());
            return "secKill_fail";
        }
        //判断是否秒杀到
        SecKillOrder order=orderService.getSecKillOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null){
            model.addAttribute("errormsg",CodeMsg.REPEATE_SERCKILL.getMsg());
            return "secKill_fail";
        }
        //减库存，下订单，写入秒杀订单
        OrderInfo orderInfo=secKillService.secKill(user,goodsVo);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goodsVo);
        return "order_detail";
    }
}
