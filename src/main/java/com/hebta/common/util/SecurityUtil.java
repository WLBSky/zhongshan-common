package com.hebta.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: 加密工具类
 * @author: edward
 * @date: 2013-7-4 下午4:19:50
 * @version: V1.0
 */
public class SecurityUtil {

    private static SecurityUtil me;

    private SecurityUtil() {
    }

    /**
     * 获得单列
     * 
     * @return
     */
    private static SecurityUtil getInstance() {
        return null == me ? me = new SecurityUtil() : me;
    }

    /**
     * md5加密
     * 
     * @param s_param
     * @return
     */
    public static String md5Encrypt(String s_param) {
        return getInstance().encrypt("MD5", s_param, 16);
    }

    /**
     * md5加密
     * 
     * @param b_param
     * @return
     */
    public static String md5Encrypt(byte[] b_param) {
        return getInstance().encrypt("MD5", b_param, 16);
    }

    /**
     * md5加密(36进制)
     * 
     * @param s_param
     * @return
     */
    public static String md5Encrypt_36(String s_param) {
        return getInstance().encrypt("MD5", s_param, 36);
    }

    /**
     * md5加密(36进制)
     * 
     * @param b_param
     * @return
     */
    public static String md5Encrypt_36(byte[] b_param) {
        return getInstance().encrypt("MD5", b_param, 36);
    }

    /**
     * sha1加密
     * 
     * @param s_param
     * @return
     */
    public static String sha1Encrypt(String s_param) {
        return getInstance().encrypt("SHA-1", s_param, 16);
    }

    /**
     * sha1加密
     * 
     * @param b_param
     * @return
     */
    public static String sha1Encrypt(byte[] b_param) {
        return getInstance().encrypt("SHA-1", b_param, 16);
    }

    /**
     * sha1加密(36进制)
     * 
     * @param s_param
     * @return
     */
    public static String sha1Encrypt_36(String s_param) {
        return getInstance().encrypt("SHA-1", s_param, 36);
    }

    /**
     * sha1加密(36进制)
     * 
     * @param b_param
     * @return
     */
    public static String sha1Encrypt_36(byte[] b_param) {
        return getInstance().encrypt("SHA-1", b_param, 36);
    }

    /**
     * 密码加密
     * 
     * @param s_param // 公共Key
     * @param passwd 明文密码
     * @return
     */
    public static String encryptPasswd(String s_param, String passwd) {
        StringBuilder digestBuff = new StringBuilder();
        if (null != s_param && s_param.length() > 0) {
            int len = s_param.length();
            int half = len / 2;
            digestBuff.append(s_param.substring(0, half)).append(passwd).append(s_param.substring(half));
        } else {
            digestBuff.append(passwd);
        }
        return getInstance().encrypt("SHA-1", digestBuff.toString(), 16);
    }

    /**
     * 加密
     * 
     * @param algorithm
     * @param s_param
     * @param radix
     * @return
     */
    private String encrypt(String algorithm, String s_param, int radix) {
        try {
            byte[] result = digest(algorithm, s_param);
            return null == result ? null : byte2String(result, radix);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     * 
     * @param algorithm
     * @param b_param
     * @param radix
     * @return
     */
    private String encrypt(String algorithm, byte[] b_param, int radix) {
        try {
            byte[] result = digest(algorithm, b_param);
            return null == result ? null : byte2String(result, radix);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 算法签名
     * 
     * @param algorithm
     * @param s_param
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private byte[] digest(String algorithm, String s_param) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        if (null == s_param || s_param.length() == 0) return null;
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(s_param.getBytes("UTF-8"));
        return md.digest();
    }

    /**
     * 算法签名
     * 
     * @param algorithm
     * @param b_param
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private byte[] digest(String algorithm, byte[] b_param) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        if (null == b_param || b_param.length == 0) return null;
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(b_param);
        return md.digest();
    }

    /**
     * 加密算法Byte转化字串工具
     * 
     * @param digest
     * @param radix
     * @return
     */
    private String byte2String(byte[] digest, int radix) {
        StringBuilder buff = new StringBuilder();
        String code;
        for (int i = 0, len = digest.length; i < len; i++) {
            code = Integer.toString(0xFF & digest[i], radix);
            buff.append(1 == code.length() ? 0 : "").append(code);
        }
        return buff.toString();
    }
}
