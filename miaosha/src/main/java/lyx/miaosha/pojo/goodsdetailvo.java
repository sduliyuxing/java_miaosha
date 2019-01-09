package lyx.miaosha.pojo;

/**
 * @Title goodsdetailvo
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 15:21
 */
public class goodsdetailvo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private goodsvo goods ;
    private miaoshauser user;

    @Override
    public String toString() {
        return "goodsdetailvo{" +
                "miaoshaStatus=" + miaoshaStatus +
                ", remainSeconds=" + remainSeconds +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public goodsvo getGoods() {
        return goods;
    }

    public void setGoods(goodsvo goods) {
        this.goods = goods;
    }

    public miaoshauser getUser() {
        return user;
    }

    public void setUser(miaoshauser user) {
        this.user = user;
    }
}
