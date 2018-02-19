package com.wdd.skill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5的公共方法
 *
 * @author
 * @create 2018-02-18 18:39
 **/
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    /**
     * 将字符串转化为md5
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);

    }

    /**
     * 将输入的密码转化为加密的密码
     *
     * @param pass
     * @return
     */
    public static String inputPass2formPass(String pass) {
        String str = salt.charAt(0) + salt.charAt(1) + pass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 二次加密保存到数据库md5加密
     *
     * @param pass
     * @param salt
     * @return
     */
    public static String formPass2DbPass(String pass, String salt) {
        String str = salt.charAt(0) + salt.charAt(1) + pass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static void main(String[] args) {
        System.out.println(formPass2DbPass(inputPass2formPass("123456"), "123456"));
    }

}
