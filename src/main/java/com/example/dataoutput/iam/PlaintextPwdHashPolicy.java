package com.example.dataoutput.iam;

/**
 * Created by 军 on 2016/3/1.
 */
public class PlaintextPwdHashPolicy implements PwdHashPolicy {
	@Override
	public String encryptPassword(String password, String salt) {
		return password;
	}

	@Override
	public String name() {
		return PwdHashPolicy.PLAINTEXT;
	}

	@Override
	public String displayName() {
		return "明文";
	}

	@Override
	public String description() {
		return "明文，不建议使用";
	}
}
