package lyx.miaosha.dao;

import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshagoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Title goodsdao
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 16:23
 */

@Mapper
public interface goodsdao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<goodsvo> get();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsid}")
    public goodsvo getGoodsVoByGoodsId(@Param("goodsid") long goodsid);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsid}")
    public int reduceStock(miaoshagoods g);

}
