package com.crowd.util;

import com.crowd.contant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {

    /**
     * MD5加密
     * */
    public static String md5(String source){
        //1.判断字符串合法性
        if(source == null || source.length() == 0){
            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_STRING_INVALID);
        }

        try {
            // 3.获取 MessageDigest 对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建 BigInteger 对象
            BigInteger bigInteger = new BigInteger(1, output);
            // 7.按照 16 进制将 bigInteger 的值转换为字符串
            String encode = bigInteger.toString(16);
            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 判断当前请求是否为ajax请求
     * */
    /*
     * 判断当前请求是否为ajax请求
     * true：是 false:不是
     * */
    public static boolean judgRequestType(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String xRequest = request.getHeader("X-Requested-With");
        return ((acceptHeader != null && acceptHeader.contains("application/json")) || (xRequest != null && xRequest.equals("XMLHttpRequest")));
    }
}
