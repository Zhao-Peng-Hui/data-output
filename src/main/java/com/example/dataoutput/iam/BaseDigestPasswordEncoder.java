//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.dataoutput.iam;

public abstract class BaseDigestPasswordEncoder extends BasePasswordEncoder {
    private boolean encodeHashAsBase64 = false;

    public BaseDigestPasswordEncoder() {
    }

    public boolean getEncodeHashAsBase64() {
        return this.encodeHashAsBase64;
    }

    public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
        this.encodeHashAsBase64 = encodeHashAsBase64;
    }
}
