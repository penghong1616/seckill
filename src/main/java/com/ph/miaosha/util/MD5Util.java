package com.ph.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }
    private static String salt="1a2b3c4d";
    public static String inputPassFormPass(String inputPass){
        String str=inputPass+salt;
        return md5(str);
    }
    public static String fromPassToDBPass(String inputPass,String salt){
        String str=inputPass+salt;
        return md5(str);
    }
    public static String inputPassToDBPass(String input,String saltDB){
        String fromPass=inputPassFormPass(input);
        String dbPass=fromPassToDBPass(fromPass,saltDB);
        return dbPass;
    }
    public static void main(String[] args) {
        System.out.println(inputPassFormPass("123123"));
        System.out.println(inputPassToDBPass(inputPassFormPass("123123"),"1a2b3c4d"));
    }
}
