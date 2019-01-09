package lyx.miaosha.redis;

/**
 * @Title miaoshauserkey
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 8:52
 */

public class miaoshauserkey extends baseprefix{

    public static final int TOKEN_EXPIRE = 180;

    public miaoshauserkey(int expiresecond, String keyprefix) {
        super(expiresecond, keyprefix);
    }

    public static miaoshauserkey token=new miaoshauserkey(TOKEN_EXPIRE,"tk");

}
