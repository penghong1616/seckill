package com.ph.miaosha;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ph.miaosha.redis.RedisConfig;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.vo.GoodsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaApplicationTests {
    @Autowired
    GoodsService goodsService;
    @Test
    public void contextLoads() {
    }
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    JedisPool jedisPool;
    @Test
    public void redisTest(){
        if (redisConfig==null)
            System.out.println("redisConfig为空。/");
        else
            System.out.println("Test"+redisConfig);
        if (jedisPool!=null){
            System.out.println("jedis不为空");
        }
    }
    @Test
    public void pageHelper(){
        //pageNum：显示第几页，pageSize：每页显示的数量
        //PageHelper.startPage(2,2);
       //offset：偏移量，从第offset+1个开始显示，limit：查询结果显示结果集的数量
       PageHelper.offsetPage(1,2);
       List<GoodsVo > list=goodsService.listGoods();
       for (GoodsVo goodsVo:list){
           System.out.println(goodsVo.getId());
       }
    }
    @Test
    public void pageInfo(){
        PageHelper.startPage(2,2);
        PageInfo pageInfo=new PageInfo(goodsService.listGoods());
        List<GoodsVo>list=pageInfo.getList();
        for (GoodsVo goodsVo:list){
            System.out.println(goodsVo.getId());
        }
        System.out.println(pageInfo.isIsFirstPage());//false
        System.out.println(pageInfo.getPages());//总页数
        System.out.println(pageInfo.getStartRow());//当前结果集，在数据库中开始的行数
        System.out.println(Arrays.toString(pageInfo.getNavigatepageNums()));
        System.out.println(pageInfo.getNavigatePages());
        System.out.println(pageInfo.getNavigateFirstPage());
        System.out.println(pageInfo.getNavigateLastPage());

    }
}
