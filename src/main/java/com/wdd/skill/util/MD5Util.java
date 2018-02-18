package com.wdd.skill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5的公共方法
 *
 * @author
 * @create 2018-02-18 18:39
 **/
public class MD5Util {

    /**
     * 将字符串转化为md5
     * @param str
     * @return
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);

    }

}
