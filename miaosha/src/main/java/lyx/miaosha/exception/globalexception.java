package lyx.miaosha.exception;

import lyx.miaosha.result.codemsg;

/**
 * @Title globalexception
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 17:46
 */
public class globalexception extends RuntimeException {

    private codemsg codemsg;

    public globalexception(codemsg codemsg) {
        super(codemsg.toString());
        this.codemsg = codemsg;
    }

    public codemsg getCodemsg() {
        return codemsg;
    }

    public void setCodemsg(codemsg codemsg) {
        this.codemsg = codemsg;
    }
}
