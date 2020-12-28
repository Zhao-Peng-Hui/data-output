package com.example.dataoutput.iam;

/**
 * <p>
 * 用户需要强制修改密码的原因
 * </p>
 * 
 * @author luotao 2016/05/05
 */
public enum ForceResetPasswordReason {
                                      PASSWORD_EXPIRED /* 密码过期 */,
                                      RESET_BY_ADMIN /* 管理员重置 */,
                                      NOT_MEET_PASSWORD_POLICY /* 不满足密码策略 */
}
