package com.example.dataoutput.iam;

import java.util.*;

/**
 * Created by 军 on 2016/2/29.
 */
public class PwdHashPolicyLookup {

    public static Map<String, PwdHashPolicy> config = new LinkedHashMap<String, PwdHashPolicy>();

    static {
        // 是否有内置
        if (!config.containsKey(PwdHashPolicy.DEFAULT)) {
            config.put(PwdHashPolicy.DEFAULT, new NgiamPwdHashPolicy());
        }
        // 添加明文
        if (!config.containsKey(PwdHashPolicy.PLAINTEXT)) {
            config.put(PwdHashPolicy.PLAINTEXT, new PlaintextPwdHashPolicy());
        }

        // 添加MD5
        config.put("MD5", new MD5PwdHashPolicy(PwdHashPolicy.MD5_32_L));
        config.put("md5_32_upper", new MD5PwdHashPolicy(PwdHashPolicy.MD5_32_U));
        config.put("md5_32_lower", new MD5PwdHashPolicy(PwdHashPolicy.MD5_32_L));
        config.put("md5_16_upper", new MD5PwdHashPolicy(PwdHashPolicy.MD5_16_U));
        config.put("md5_16_lower", new MD5PwdHashPolicy(PwdHashPolicy.MD5_16_L));

        // PBKDF2
        config.put(PwdHashPolicy.PBKDF2, new PBKDF2PwdPolicy());

        // 其他算法
        ServiceLoader<PwdHashPolicy> serviceLoader = ServiceLoader.load(PwdHashPolicy.class);
        Iterator<PwdHashPolicy> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            PwdHashPolicy pwdHashPolicy = iterator.next();
            config.put(pwdHashPolicy.name(), pwdHashPolicy);
        }
        Collections.unmodifiableMap(config);
    }

}
