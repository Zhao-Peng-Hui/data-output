package com.example.dataoutput.iam;

/**
 * Created by 军 on 2016/2/27.
 */
public interface PwdHashPolicy {

    String DEFAULT = "default";
    String PLAINTEXT = "plaintext";
    String PBKDF2 = "PBKDF2";

    String MD5_32_U = "md5_32_upper";
    String MD5_32_L = "md5_32_lower";

    String MD5_16_U = "md5_16_upper";
    String MD5_16_L = "md5_16_lower";

    String encryptPassword(String password, String salt);


    /**
     * 名称
     *
     * @return
     */
    String name();


    /**
     * 描述
     *
     * @return
     */
    String description();


    /**
     * 显示名
     *
     * @return
     */
    String displayName();
}
