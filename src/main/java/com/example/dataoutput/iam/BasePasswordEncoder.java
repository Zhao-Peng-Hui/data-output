//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.dataoutput.iam;

public abstract class BasePasswordEncoder implements PasswordEncoder {
    public BasePasswordEncoder() {
    }

    protected String[] demergePasswordAndSalt(String mergedPasswordSalt) {
        if (mergedPasswordSalt != null && !"".equals(mergedPasswordSalt)) {
            String password = mergedPasswordSalt;
            String salt = "";
            int saltBegins = mergedPasswordSalt.lastIndexOf("{");
            if (saltBegins != -1 && saltBegins + 1 < mergedPasswordSalt.length()) {
                salt = mergedPasswordSalt.substring(saltBegins + 1, mergedPasswordSalt.length() - 1);
                password = mergedPasswordSalt.substring(0, saltBegins);
            }

            return new String[]{password, salt};
        } else {
            throw new IllegalArgumentException("Cannot pass a null or empty String");
        }
    }

    protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt) ? password + "{" + salt.toString() + "}" : password;
        }
    }
}
