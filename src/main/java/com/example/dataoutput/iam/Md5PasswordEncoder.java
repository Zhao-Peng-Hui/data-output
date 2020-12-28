//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.dataoutput.iam;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

public class Md5PasswordEncoder extends MessageDigestPasswordEncoder {
    public Md5PasswordEncoder() {
        super("MD5");
    }
}
