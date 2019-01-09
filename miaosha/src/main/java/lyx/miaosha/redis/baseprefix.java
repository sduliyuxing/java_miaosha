package lyx.miaosha.redis;

/**
 * @Title baseprefix
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\27 0027 17:35
 */

public abstract class baseprefix implements keyprefix{

    private int expiresecond;

    private String keyprefix;

    public baseprefix(String keyprefix) {
        this.expiresecond=0;
        this.keyprefix = keyprefix;
    }

    public baseprefix(int expiresecond, String keyprefix) {
        this.expiresecond = expiresecond;
        this.keyprefix = keyprefix;
    }

    @Override
    public int expiresecond() {
        return expiresecond;
    }

    @Override
    public String getkeyprefix() {
        String classname=getClass().getSimpleName();
        String name=classname+":"+keyprefix+":";
        return name;
    }
}
