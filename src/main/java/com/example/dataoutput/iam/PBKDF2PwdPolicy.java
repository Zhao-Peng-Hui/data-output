package com.example.dataoutput.iam;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


/**
 * Created by Lynn·Rowe on 2017/4/6.
 */
public class PBKDF2PwdPolicy implements PwdHashPolicy {

    public static final java.lang.String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * 生成密文的长度
     */
    public static final int HASH_BIT_SIZE = 128 * 4;

    /**
     * 迭代次数
     */
    public static final int PBKDF2_ITERATIONS = 1000;

    /**
     * 十六进制字符串转二进制字符串
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * 二进制字符串转十六进制字符串
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    @Override
    public String encryptPassword(String password, String salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), fromHex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return toHex(f.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // ignore
        }
        return null;
    }

    @Override
    public String name() {
        return PwdHashPolicy.PBKDF2;
    }

    @Override
    public String description() {
        return "PBKDF2";
    }

    @Override
    public String displayName() {
        return "PBKDF2";
    }
}
