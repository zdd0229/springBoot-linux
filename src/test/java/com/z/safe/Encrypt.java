package com.z.safe;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import java.security.*;

public class Encrypt {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

//        sign();
//        encryptAndDecrypt();
        RSA();
    }

    /**
     * 签名算法
     *
     * @throws NoSuchAlgorithmException
     */
    private static void sign() throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] digest = instance.digest("ksafhlkasjdfhla".getBytes());
        System.out.println(Hex.encodeHexString(digest));
    }

    /**
     * 加解密
     */
    private static void encryptAndDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator aes = KeyGenerator.getInstance("AES");
        SecretKey secretKey = aes.generateKey();
        System.out.println(Hex.encodeHexString(secretKey.getEncoded()));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] en = cipher.doFinal("盖雅工场".getBytes());

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] de = cipher.doFinal(en);

        System.out.println(new String(de));
    }

    /**
     * 非对称加密
     */
    private static void RSA() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = rsa.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("privateKey：" + Hex.encodeHexString(privateKey.getEncoded()));
        System.out.println("publicKey：" + Hex.encodeHexString(publicKey.getEncoded()));

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] en = cipher.doFinal("盖雅工场".getBytes());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] de = cipher.doFinal(en);

        System.out.println(new String(de));

    }
}
