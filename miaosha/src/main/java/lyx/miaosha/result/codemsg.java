package lyx.miaosha.result;

/**
 * @Title codemsg
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 16:02
 */
public class codemsg {
    private int code;
    private String msg;

    //通用的错误码
    public static codemsg SUCCESS = new codemsg(0, "success");
    public static codemsg SERVER_ERROR = new codemsg(500100, "服务端异常");
    public static codemsg BIND_ERROR = new codemsg(500101, "参数校验异常：%s");
    public static codemsg REQUEST_ILLEGAL = new codemsg(500102, "请求非法");
    public static codemsg ACCESS_LIMIT_REACHED= new codemsg(500104, "访问太频繁！");
    //登录模块 5002XX
    public static codemsg SESSION_ERROR = new codemsg(500210, "Session不存在或者已经失效");
    public static codemsg PASSWORD_EMPTY = new codemsg(500211, "登录密码不能为空");
    public static codemsg MOBILE_EMPTY = new codemsg(500212, "手机号不能为空");
    public static codemsg MOBILE_ERROR = new codemsg(500213, "手机号格式错误");
    public static codemsg MOBILE_NOT_EXIST = new codemsg(500214, "手机号不存在");
    public static codemsg PASSWORD_ERROR = new codemsg(500215, "密码错误");


    //商品模块 5003XX


    //订单模块 5004XX
    public static codemsg ORDER_NOT_EXIST = new codemsg(500400, "订单不存在");

    //秒杀模块 5005XX
    public static codemsg MIAO_SHA_OVER = new codemsg(500500, "商品已经秒杀完毕");
    public static codemsg REPEATE_MIAOSHA = new codemsg(500501, "不能重复秒杀");
    public static codemsg MIAOSHA_FAIL = new codemsg(500502, "秒杀失败");


    private codemsg( ) {
    }

    private codemsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public codemsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new codemsg(code, message);
    }

    @Override
    public String toString() {
        return "codemsg [code=" + code + ", msg=" + msg + "]";
    }

}

