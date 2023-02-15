package com.ChangeBUG.utils;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class TestEmptyUtils {

    /**
     * 测试对象是否为空
     */
    public boolean TestIfTheObjectIsEmpty(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}



