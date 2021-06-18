package com.zpj.rsaaes;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: Longer
 * @date: 2020/8/23
 * @description: AES工具类
 */
public class AESUtil {
    public static final String KEY = "1234567887654321";// AES加密要求key必须要128个比特位（这里需要长度为16，否则会报错）
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    /**
     * 加密
     *
     * @param content 加密文本
     * @param key     加密密钥，appSecret的前16位
     * @param iv      初始化向量，appSecret的后16位
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key, String iv) throws Exception {
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR); //"算法/模式/补码方式"
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes()); //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParam);
        byte[] encrypted = cipher.doFinal(content.getBytes());

        return new BASE64Encoder().encode(encrypted);
    }

    public static String decrypt(String content, String key, String iv) throws Exception {
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR); //"算法/模式/补码方式"
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes()); //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParam);
        byte[] encrypted = new BASE64Decoder().decodeBuffer(content); //先用base64解密
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public static void main(String[] args) throws Exception {
        String encrypt = AESUtil.encrypt("222", "1234567887654321", "1234567890123456");
        System.out.println(encrypt);
        String decrypt = AESUtil.decrypt(encrypt, "1234567887654321", "1234567890123456");
        System.out.println(decrypt);
    }
}