package com.wy.hodgepodges.service;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-08 11:45
 */

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.validation.Validation;
import com.alibaba.dubbo.validation.Validator;

/**
 * @ClassName CustomValidation
 * @Description
 * @Author lixin
 * @Date 2019-04-23 17:34
 */
public class CustomValidation implements Validation {

    @Override
    public Validator getValidator(URL url) {
//        return new CustomValidator(url);
        return null;
    }

}
