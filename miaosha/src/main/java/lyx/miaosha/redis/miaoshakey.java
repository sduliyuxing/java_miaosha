package lyx.miaosha.redis;

/**
 * @Title miaoshakey
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\9 0009 9:00
 */
public class miaoshakey extends baseprefix {

    private miaoshakey(String prefix) {
        super(prefix);
    }
    public static miaoshakey isGoodsOver = new miaoshakey("go");
}
