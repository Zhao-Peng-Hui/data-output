package com.example.dataoutput.iam;


/**
 * Created by 军 on 2016/2/27.
 */
public class NgiamPwdHashPolicy implements PwdHashPolicy {

	@Override
	public String encryptPassword(String password, String salt) {
		Md5PasswordEncoder md5PasswordEncoder = buildMd5PasswordEncoder(true);
		String md5Password = md5PasswordEncoder.encodePassword(password, salt);
		return md5Password;
	}

	@Override
	public String displayName() {
		return "NGIAM默认加密";
	}

	@Override
	public String name() {
		return DEFAULT;
	}

	@Override
	public String description() {
		return "默认密码加密算法";
	}

	/**
	 * 构建一个MD5编码器
	 * 
	 * @return Md5PasswordEncoder
	 */
	private static Md5PasswordEncoder buildMd5PasswordEncoder(boolean base64) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		md5PasswordEncoder.setEncodeHashAsBase64(base64);
		md5PasswordEncoder.setIterations(1024);
		return md5PasswordEncoder;
	}

}
