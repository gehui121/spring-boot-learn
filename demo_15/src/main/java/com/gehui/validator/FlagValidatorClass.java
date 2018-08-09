package com.gehui.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2018/8/9 17:29.
 * 自定义验证实现类里面有两个方法，分别是初始化验证消息、执行验证。
 * 初始化验证消息方法内你可以得到配置的注解内容，而验证方法则是你的验证业务逻辑。
 **/
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {
    //临时变量保存flag值列表
    private String values;

    //初始化values的值
    public void initialize(FlagValidator validator) {
        //将注释内配置的值赋值给变量
        this.values = validator.values();
    }

    //实现验证
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        //分割定义的有效值
        String[] value_array = values.split(",");
        boolean isFlag = false;
        //遍历有效值
        for (int i = 0; i < value_array.length; i++) {
            //存在一致跳出循环，给isFlag赋值true
            if (value_array[i].equals(value)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }


}
