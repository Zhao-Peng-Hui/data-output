package com.example.dataoutput.iam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 军 on 2016/4/8.
 */
public class MD5PwdHashPolicy implements PwdHashPolicy {

    private final String md5Policy;

    public MD5PwdHashPolicy(String md5Policy) {
        this.md5Policy = md5Policy;
    }

    @Override
    public String encryptPassword(String password, String salt) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            String encryptPwd = hexString.toString();

            switch (md5Policy) {
                case MD5_32_U:
                    encryptPwd = encryptPwd.toUpperCase();
                    break;
                case MD5_16_U:
                    encryptPwd = encryptPwd.substring(8, 24).toUpperCase();
                    break;
                case MD5_16_L:
                    encryptPwd = encryptPwd.substring(8, 24).toLowerCase();
                    break;
                default:
                    encryptPwd = encryptPwd.toLowerCase();
            }

            return encryptPwd;
        } catch (NoSuchAlgorithmException e) {
            // ignore
        }

        return null;
    }

    @Override
    public String name() {
        return "MD5";
    }

    @Override
    public String description() {
        return "MD5加密算法";
    }

    @Override
    public String displayName() {
        return "MD5加密算法";
    }
}
