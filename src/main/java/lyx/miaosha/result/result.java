package lyx.miaosha.result;

/**
 * @Title result
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 15:56
 */
public class result<T> {

    private int code;
    private String msg;
    private T data;

    public result(T data) {
        this.code=0;
        this.data=data;
        this.msg="success";
    }

    public result(codemsg msg){
        if (msg==null){
            return;
        }
        this.msg=msg.getMsg();
        this.code=msg.getCode();
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }

    public static<T> result<T> success(T data){
        return new result<T>(data);
    }

    public static <T> result<T> error(codemsg msg){
        return new result<T>(msg);
    }
}
