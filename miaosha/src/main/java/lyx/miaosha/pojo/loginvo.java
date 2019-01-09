package lyx.miaosha.pojo;

import lyx.miaosha.validator.isphone;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Title loginvo
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 17:33
 */


public class loginvo {

    @NotNull
    @isphone
private String phone;

    @NotNull
    @Length(min = 32)
    private String password;

    @Override
    public String toString() {
        return "loginvo{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
