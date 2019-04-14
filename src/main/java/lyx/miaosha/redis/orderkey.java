package lyx.miaosha.redis;

/**
 * @Title orderkey
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\27 0027 17:58
 */
public class orderkey extends baseprefix {

    public orderkey(int expiresecond, String keyprefix) {
        super(expiresecond, keyprefix);
    }
}
