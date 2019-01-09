package lyx.miaosha.service;


import lyx.miaosha.dao.orderdao;
import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshaorder;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.pojo.orderinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;


/**
 * @Title orderservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\3 0003 10:47
 */
@Service
public class orderservice {

    @Autowired
     private orderdao orderdao;

    public miaoshaorder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderdao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    public orderinfo getOrderById(long orderId) {
        return orderdao.getOrderById(orderId);
    }


    @Transactional
    public orderinfo createOrder(miaoshauser user, goodsvo goods) {
        orderinfo orderInfo = new orderinfo();
        orderInfo.setCreateDate(new Timestamp(new Date().getTime()));
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderdao.insert(orderInfo);
        miaoshaorder miaoshaOrder = new miaoshaorder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        orderdao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }

    public void deleteOrders() {
        orderdao.deleteOrders();
        orderdao.deleteMiaoshaOrders();
    }
}
