package lyx.miaosha.dao;

import lyx.miaosha.pojo.miaoshaorder;
import lyx.miaosha.pojo.orderinfo;
import org.apache.ibatis.annotations.*;

/**
 * @Title orderdao
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\3 0003 10:34
 */
@Mapper
public interface orderdao {


    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public miaoshaorder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId")long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long insert(orderinfo orderInfo);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertMiaoshaOrder(miaoshaorder miaoshaOrder);


    @Select("select * from order_info where id = #{orderId}")
    public orderinfo getOrderById(@Param("orderId")long orderId);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from miaosha_order")
    public void deleteMiaoshaOrders();
}
