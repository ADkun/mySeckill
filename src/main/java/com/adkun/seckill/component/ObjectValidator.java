package com.adkun.seckill.component;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证实体类有效性
 */
@Component
public class ObjectValidator {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * @param obj
     * @return Map，存实体类属性、以及对应的消息。如果Map为空，说明没问题，如果非空，说明有问题。
     */
    public Map<String, String> validate(Object obj) {
        if (obj == null) {
            return null;
        }

        // key：属性名 value：错误消息
        Map<String, String> result = new HashMap<>();

        // validator根据注解检查对象
        Set<ConstraintViolation<Object>> set = validator.validate(obj);
        // 解析返回值，放到Map里
        if (set != null && set.size() > 0) {
            for (ConstraintViolation cv : set) {
                result.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
        }

        return result;
    }
}
