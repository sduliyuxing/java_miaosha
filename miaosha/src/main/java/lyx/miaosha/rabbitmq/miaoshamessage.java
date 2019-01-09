package lyx.miaosha.rabbitmq;

import lyx.miaosha.pojo.miaoshauser;

/**
 * @Title miaoshamessage
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\8 0008 17:47
 */
public class miaoshamessage {
    private Long goodsid;
    private miaoshauser miaoshauser;

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public lyx.miaosha.pojo.miaoshauser getMiaoshauser() {
        return miaoshauser;
    }

    @Override
    public String toString() {
        return "miaoshamessage{" +
                "goodsid=" + goodsid +
                ", miaoshauser=" + miaoshauser +
                '}';
    }

    public void setMiaoshauser(lyx.miaosha.pojo.miaoshauser miaoshauser) {
        this.miaoshauser = miaoshauser;
    }
}
