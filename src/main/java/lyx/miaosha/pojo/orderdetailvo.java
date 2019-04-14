package lyx.miaosha.pojo;

/**
 * @Title orderdetailvo
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 15:22
 */
public class orderdetailvo {
  private goodsvo goods;
  private orderinfo order;

    @Override
    public String toString() {
        return "orderdetailvo{" +
                "goods=" + goods +
                ", order=" + order +
                '}';
    }

    public goodsvo getGoods() {
        return goods;
    }

    public void setGoods(goodsvo goods) {
        this.goods = goods;
    }

    public orderinfo getOrder() {
        return order;
    }

    public void setOrder(orderinfo order) {
        this.order = order;
    }
}
