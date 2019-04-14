package lyx.miaosha.service;

import lyx.miaosha.dao.goodsdao;
import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshagoods;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title goodsservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 16:30
 */

@Service
public class goodsservice {

    @Autowired
    private goodsdao goodsdao;

    public List<goodsvo> get(){
        return  goodsdao.get();
    }

    public goodsvo getbyid(long id){
        return goodsdao.getGoodsVoByGoodsId(id);
    }

    public boolean reducestock(goodsvo goodsvo){
        miaoshagoods g=new miaoshagoods();
        g.setGoodsId(goodsvo.getId());
       return goodsdao.reduceStock(g)>0;

    }

    public void resetStock(List<goodsvo> goodsList) {
        for(goodsvo goods : goodsList ) {
            miaoshagoods g = new miaoshagoods();
            g.setGoodsId(goods.getId());
            g.setStockCount(goods.getStockCount());
            goodsdao.reduceStock(g);
        }
    }
}
