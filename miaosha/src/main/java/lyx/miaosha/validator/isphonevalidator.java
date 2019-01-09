package lyx.miaosha.validator;

import lyx.miaosha.util.validatorutil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Title isphonevalidator
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 15:45
 */
public class isphonevalidator implements ConstraintValidator<isphone,String> {

    private boolean required=false;

    @Override
    public void initialize(isphone constraintAnnotation) {
        //获取注解中的required
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required){
           return validatorutil.isphone(value);
        }else if (StringUtils.isEmpty(value)){
            return false;
        }else return validatorutil.isphone(value);
    }
}
