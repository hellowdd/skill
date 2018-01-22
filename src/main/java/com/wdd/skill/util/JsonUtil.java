package com.wdd.skill.util;

import com.alibaba.fastjson.JSON;

/**
 * json常用方法
 *
 * @author
 * @create 2018-01-20 21:31
 **/
public class JsonUtil {

    public static <T> String bean2String(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        }
        if (clazz == String.class) {
            return (String) value;
        }
        if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        }

        return JSON.toJSONString(value);

    }

    public static <T> T string2Bean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

}
