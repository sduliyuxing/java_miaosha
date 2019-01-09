package lyx.miaosha.service;

import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshaorder;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.pojo.orderinfo;
import lyx.miaosha.redis.miaoshakey;
import lyx.miaosha.redis.redisservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title miaoshaservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\3 0003 11:00
 */

@Service
public class miaoshaservice {

    @Autowired
    goodsservice goodsService;

    @Autowired
    orderservice orderService;

    @Autowired
    redisservice redisservice;

    @Transactional
    public orderinfo miaosha(miaoshauser user, goodsvo goods) {
        //减库存 下订单 写入秒杀订单
        boolean success = goodsService.reducestock(goods);
        if(success) {
            //order_info maiosha_order
            return orderService.createOrder(user, goods);
        }else {//说明商品秒杀完了。做一个标记
            setGoodsOver(goods.getId());
            return null;
        }
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
        miaoshaorder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        if(order != null) {//秒杀成功
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisservice.set(miaoshakey.isGoodsOver, ""+goodsId, true);
    }

    private boolean getGoodsOver(long goodsId) {
        return redisservice.isexist(miaoshakey.isGoodsOver, ""+goodsId);
    }

    public void reset(List<goodsvo> goodsList) {
        goodsService.resetStock(goodsList);
        orderService.deleteOrders();
    }
}
