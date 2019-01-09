package lyx.miaosha.redis;

/**
 * @Title userkey
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\27 0027 17:59
 */
public class userkey extends baseprefix {

    public userkey(String keyprefix) {
        super(keyprefix);
    }

    public static userkey getid=new userkey("id");

    public static userkey getname=new userkey("name");

}
