/**
 * Creation Date:2016年8月3日-下午5:02:57
 * <p>
 * Copyright 2008-2016 © 同程网 Inc. All Rights Reserved
 */
package com.hebta.common.util;


import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;

import org.springframework.util.CollectionUtils;

import com.hebta.common.enums.CodeEnum;
import com.hebta.common.exception.BusinessException;



/**
 * 验证实体工具类 <br/>
 */
public class ValidationUtils {

	/**
	 * Validator
	 */
    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    /**
     * 验证对象属性
     * @param obj 传入的Object
     */
    public static <T> void validateBean(T obj) {
    	if (null != obj) {
    		Field[] fields = obj.getClass().getDeclaredFields();
    		if (null != fields && fields.length > 0) {
    			for (Field f : fields) {
    				NotEmpty anno = f.getDeclaredAnnotation(NotEmpty.class);
    				if (null != anno) {
    					boolean isAccessible = false;
    					try {
    						if (!f.isAccessible()) {
    							isAccessible = true;
    							f.setAccessible(true);
    						}
							Object value = f.get(obj);
							if (value instanceof String && null != value) {
								f.set(obj, ((String)value).trim());
							}
						} catch (Exception e) {
						} finally {
							if (isAccessible) {
								f.setAccessible(false);
							}
						}
    				}
    			}
    		}
    	}
        Set<ConstraintViolation<T>> set = validator.validate(obj,
                Default.class);
        if (!CollectionUtils.isEmpty(set)) {
            Optional<ConstraintViolation<T>> op = set.parallelStream()
                    .findFirst();
            if (op.isPresent()) {
                ConstraintViolation<T> cv = op.get();
                throw new BusinessException(cv.getMessage(), CodeEnum.PARAM_ERROR.getCode());
            }
        }
    }

}
