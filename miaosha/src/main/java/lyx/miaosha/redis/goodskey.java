package lyx.miaosha.redis;

/**
 * @Title goodskey
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\8 0008 17:36
 */
public class goodskey extends baseprefix {
    private goodskey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static goodskey getGoodsList = new goodskey(60, "gl");
    public static goodskey getGoodsDetail = new goodskey(60, "gd");
    public static goodskey getMiaoshaGoodsStock= new goodskey(0, "gs");

}
