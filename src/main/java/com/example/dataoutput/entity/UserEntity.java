package com.example.dataoutput.entity;

import com.example.dataoutput.iam.*;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p/>
 * 核心用户库用户实体
 * <p/>
 *
 * @author ryvius
 */

@Document()
@CompoundIndexes({
        @CompoundIndex(def = "{'removed': 1, 'completeInfo': 1, 'admin': -1}", name = "rca"),
        @CompoundIndex(def = "{'admin': 1}", name = "admin"),
        @CompoundIndex(def = "{'removed': 1, 'superAdmin': 1}", name = "r_s"),
        @CompoundIndex(def = "{'enabled': 1, 'removed': 1}", name = "er"),
        @CompoundIndex(def = "{'completeInfo': 1, 'superAdmin': 1, 'removed': 1}", name = "csr"),
        @CompoundIndex(def = "{'createdDate': -1, 'removed': 1, 'superAdmin': 1, 'enabled': 1}", name = "c_r_s_e"),
        @CompoundIndex(def = "{'enabled': 1, 'createdDate': -1, 'removed': 1, 'superAdmin': 1}", name = "e_c_r_s"),
        @CompoundIndex(def = "{'username' : 1,'enabled' : 1,'createdDate' : -1,'removed' : 1,'superAdmin' : 1}", name = "uecrs"),
        @CompoundIndex(def = "{'displayName' : 1,'enabled' : 1,'createdDate' : -1,'removed' : 1,'superAdmin' : 1}", name = "decrs"),
        @CompoundIndex(def = "{'email' : 1,'enabled' : 1,'createdDate' : -1,'removed' : 1,'superAdmin' : 1}", name = "eecrs")
})
public class UserEntity extends ResourceItemEntity {

    private static final long serialVersionUID = 2702597830158081828L;

    @Indexed(unique = false, sparse = true)
    @ApiModelProperty(name = "roleIds", value = "角色")
    private Set<String> roleIds;

    @Indexed
    @ApiModelProperty(name = "usernameLowerPy", value = "用户名小写")
    private String usernameLowerPy;

    @Indexed(unique = true, sparse = true)
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    // 别名
    @Indexed(unique = false, sparse = true)
    @ApiModelProperty(name = "nickname", value = "别名")
    private String nickname;

    @Indexed
    @ApiModelProperty(name = "displayName", value = "名称")
    private String displayName;

    @Indexed
    @ApiModelProperty(name = "displayNameLowerPy", value = "名称小写")
    private String displayNameLowerPy;

    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    /**
     * md5密码
     */
    @ApiModelProperty(name = "md5Pwd", value = "md5密码")
    private String md5Pwd;

    @Indexed(unique = false)
    @ApiModelProperty(name = "mobile", value = "电话")
    private String mobile;

    @ApiModelProperty(name = "isCheckMobile", value = "手机号是否已验证")
    private boolean isCheckMobile = false;

    @ApiModelProperty(name = "deputyEmail", value = "副邮箱")
    private String deputyEmail;

    @ApiModelProperty(name = "isCheckDeputyEmail", value = "副邮箱是否已验证")
    private boolean isCheckDeputyEmail = false;

    @ApiModelProperty(name = "spareEmail", value = "备用邮箱")
    private String spareEmail;

    @ApiModelProperty(name = "isCheckSpareEmail", value = "备用邮箱是否已验证")
    private boolean isCheckSpareEmail = false;


    public static final String TENANT_ID = "tenantId";

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Indexed
    @ApiModelProperty(name = "tenantId", value = "租户ID")
    private String tenantId;

    /**
     * 电子邮箱
     */
    @Indexed(unique = false)
    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;

    @ApiModelProperty(name = "isCheckEmail", value = "邮箱是否已验证")
    private boolean isCheckEmail = false;

    /* 头像；保存为MongoDB的GridFS对象，此属性保存该对象ID */
    @ApiModelProperty(name = "avatarId", value = "头像；保存为MongoDB的GridFS对象，此属性保存该对象ID")
    private String avatarId;

    /**
     * 用户API Key
     */
    @Indexed(unique = true, sparse = true)
    @ApiModelProperty(name = "userApiKey", value = "用户API Key")
    private String userApiKey;

    @ApiModelProperty(name = "enabledUserApiKey", value = "enabledUserApiKey")
    private boolean enabledUserApiKey;

    @ApiModelProperty("上次登录时间")
    private DateTime lastLoginTime;

    @ApiModelProperty("本次登录时间")
    private DateTime currentLoginTime;

    @ApiModelProperty("上次登录地址")
    private String lastLoginAddress;

    @ApiModelProperty("本次登录地址")
    private String currentLoginAddress;

    /**
     * 是否可用
     */
    @ApiModelProperty(name = "enabled", value = "是否可用")
    private boolean enabled = true;

    @ApiModelProperty(name = "frozenDesc", value = "禁用描述")
    private String frozenDesc;

    /**
     * 是否已删除 （软删除标记）
     */
    @Indexed
    @ApiModelProperty(name = "removed", value = "是否已删除")
    private boolean removed = false;

    /**
     * 是否管理员(普通管理员)
     */
    @ApiModelProperty(name = "admin", value = "是否管理员")
    private boolean admin;

    /**
     * 管理员更新时间
     */
    @ApiModelProperty(name = "adminDate", value = "管理员更新时间")
    @Indexed(direction = IndexDirection.DESCENDING)
    private DateTime adminDate;

    /**
     * 是否是超级管理员
     */
    @ApiModelProperty(name = "superAdmin", value = "是否是超级管理员")
    private boolean superAdmin = false;

    @ApiModelProperty(name = "lastLogin", value = "是否登录")
    private boolean lastLogin = true;

    @ApiModelProperty(name = "sendPwdExpiredEmail", value = "sendPwdExpiredEmail")
    private boolean sendPwdExpiredEmail = false;

    @Indexed(unique = false)
    @ApiModelProperty(name = "pwdPolicyId", value = "密码策略ID")
    private String pwdPolicyId;

    /**
     * 账户周期
     */
    @ApiModelProperty(name = "lifecycle", value = "账户周期")
    private Lifecycle lifecycle = new Lifecycle();

    @ApiModelProperty(name = "pwdHash", value = "pwdHash")
    private String pwdHash = PwdHashPolicy.PBKDF2;

    @ApiModelProperty(name = "lastPwdUpdateDate", value = "最后修改密码时间")
    private DateTime lastPwdUpdateDate = DateTime.now();

    /**
     * 同步信息最后更新时间（用于标识用户映射属性修改变更时间） for NI-3292 add by mazhichao 2017-03-24
     */
    @ApiModelProperty(name = "synInfoUpdateDate", value = "同步信息最后更新时间")
    private DateTime synInfoUpdateDate = DateTime.now();

    /* 是否已经强制修改过密码 */
    @ApiModelProperty(name = "forceUpdatePwd", value = "是否已经强制修改过密码")
    private boolean forceUpdatePwd = false;

    /* 需要强制修改密码的原因 */
    @ApiModelProperty(name = "forceResetPasswordReason", value = "需要强制修改密码的原因")
    private ForceResetPasswordReason forceResetPasswordReason = ForceResetPasswordReason.PASSWORD_EXPIRED;

    @ApiModelProperty(name = "displayNameShortPy", value = "displayNameShortPy")
    private String displayNameShortPy;

    @ApiModelProperty(name = "displayNameFullPy", value = "displayNameFullPy")
    private String displayNameFullPy;

    @ApiModelProperty(name = "usernameShortPy", value = "usernameShortPy")
    private String usernameShortPy;

    @ApiModelProperty(name = "usernameFullPy", value = "usernameFullPy")
    private String usernameFullPy;
    /**
     * 用户所在的AD域
     */
    @ApiModelProperty(name = "adRealm", value = "用户所在的AD域")
    private String adRealm = "";

    /**
     * 挑战问题
     */
    @ApiModelProperty(name = "question", value = "挑战问题")
    private String question;

    /**
     * 挑战问题答案
     */
    @ApiModelProperty(name = "answer", value = "挑战问题答案")
    private String answer;

    /**
     * 放钓鱼信息
     */
    @ApiModelProperty(name = "fishNotice", value = "放钓鱼信息")
    private FishNotice fishNotice;

    @ApiModelProperty(value = "第三方用户唯一标识", hidden = true)
    @Indexed
    private String unionid;

    @ApiModelProperty(value = "是否补全信息", notes = "对于三方应用此属性才有意义", hidden = true)
    private boolean completeInfo = true;

    @ApiModelProperty(name = "order", value = "用户排序")
    @Field(value = "serialNum")
    @Indexed
    private int order = 0;

    /* 用户内嵌属性 */
    private Map<String, Object> attrs = new HashMap<>();

    private boolean white = false;

    private boolean firstLogin = true;

    @ApiModelProperty(name = "typeCode", value = "关联的用户类型编码")
    @Indexed
    private String typeCode;

    @ApiModelProperty(name = "certificateType", value = "证件类型")
    private String certificateType;

    @ApiModelProperty(name = "certificateNumber", value = "证件号码")
    private String certificateNumber;

    @ApiModelProperty(name = "isBindFingerPrint", value = "是否绑定指纹")
    private boolean isBindFingerPrint = false;

    @ApiModelProperty(name = "isBindVoicePrint", value = "是否绑定声纹")
    private boolean isBindVoicePrint = false;

    @ApiModelProperty(name = "isBindFacePrint", value = "是否绑定脸纹")
    private boolean isBindFacePrint = false;

    @ApiModelProperty(name = "isBindBlueKey", value = "是否绑定蓝牙Key")
    private boolean isBindBlueKey = false;

    @ApiModelProperty(name = "isBindUsbKey", value = "是否绑定USBKey")
    private boolean isBindUsbKey = false;

    @ApiModelProperty(name = "guaranteeId", value = "责任人ID")
    @Indexed
    private String guaranteeId;

    @ApiModelProperty(name = "employeeTaggerState", value = "员工岗位状态")
    private String employeeTaggerState;

    @ApiModelProperty(name = "employeeTaggerValue", value = "员工岗位状态中文")
    private String employeeTaggerValue;

    private String djUserId;

    //hg二次确认手机和邮箱
    private boolean secondaryConfirmation = false;

    //手机号国别
    private String areaCode;

    /**
     * 是否已合并 （合并标记）
     */
    @ApiModelProperty(name = "isMerge", value = "是否已合并")
    private boolean isMerge = false;


    public static Set<String> attributes() {
        return Collections.unmodifiableSet(
                new HashSet<>(
                        Arrays.asList("username",
                                "用户名",
                                "displayName",
                                "姓名",
                                "nickname",
                                "别名",
                                "admin",
                                "是否管理员",
                                "typeCode",
                                "用户类型",
                                "邮箱",
                                "email",
                                "手机号码",
                                "mobile",
                                "enabled",
                                "password",
                                "takeEffect",
                                "生效日期",
                                "loseEfficacy",
                                "失效日期",
                                "org")));
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String name) {
        this.mobile = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public String getFrozenDesc() {
        return frozenDesc;
    }

    public void setFrozenDesc(String frozenDesc) {
        this.frozenDesc = frozenDesc;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getUserApiKey() {
        return userApiKey;
    }

    public void setUserApiKey(String userApiKey) {
        this.userApiKey = userApiKey;
    }

    /**
     * 登录用户名
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.usernameFullPy = null;
        this.usernameShortPy = null;
        this.usernameLowerPy = null;
        if (null != username) {
            this.usernameFullPy = PinyinHelper.convertToPinyinString(username, "", PinyinFormat.WITHOUT_TONE);
            this.usernameShortPy = PinyinHelper.getShortPinyin(username);
            this.usernameLowerPy = username.toLowerCase();
        }
    }

    public boolean isCheckMobile() {
        return isCheckMobile;
    }

    public void setCheckMobile(boolean checkMobile) {
        isCheckMobile = checkMobile;
    }

    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    /**
     * 登录密码
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5Pwd() {
        return md5Pwd;
    }

    public void setMd5Pwd(String md5Pwd) {
        this.md5Pwd = md5Pwd;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public DateTime getAdminDate() {
        return adminDate;
    }

    public void setAdminDate(DateTime adminDate) {
        this.adminDate = adminDate;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public DateTime getLastPwdUpdateDate() {
        return lastPwdUpdateDate;
    }

    public void setLastPwdUpdateDate(DateTime lastPwdUpdateDate) {
        this.lastPwdUpdateDate = lastPwdUpdateDate;
    }

    public DateTime getSynInfoUpdateDate() {
        return synInfoUpdateDate;
    }

    public void setSynInfoUpdateDate(DateTime synInfoUpdateDate) {
        this.synInfoUpdateDate = synInfoUpdateDate;
    }

    public boolean isForceUpdatePwd() {
        return forceUpdatePwd;
    }

    /***
     * 是否需要强制修改密码，注意true为不需要修改，false为需要修改
     * @param forceUpdatePwd
     */
    public void setForceUpdatePwd(boolean forceUpdatePwd) {
        this.forceUpdatePwd = forceUpdatePwd;
    }

    /**
     * 用户的显示名
     */
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.displayNameFullPy = null;
        this.displayNameShortPy = null;
        this.displayNameLowerPy = null;
        if (null != displayName) {
            this.displayNameFullPy = PinyinHelper.convertToPinyinString(displayName, "", PinyinFormat.WITHOUT_TONE);
            this.displayNameShortPy = PinyinHelper.getShortPinyin(displayName);
            this.displayNameLowerPy = displayName.toLowerCase();
        }
    }

    public String getName() {
        if (!StringUtils.isEmpty(getDisplayName())) {
            return getDisplayName();
        } else {
            return getUsername();
        }
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * 安全令有效/无效
     */
    public boolean isEnabledUserApiKey() {
        return enabledUserApiKey;
    }

    public void setEnabledUserApiKey(boolean enabledUserApiKey) {
        this.enabledUserApiKey = enabledUserApiKey;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String encryptPassword(String password) {
        String pwdHash = getPwdHash();
        PwdHashPolicy pwdHashPolicy = PwdHashPolicyLookup.config.get(pwdHash);
        if (null == pwdHashPolicy) {
            // 不支持的Hash算法

        }
        String salt = getId();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(getDjUserId()))
            salt = getDjUserId();
        return pwdHashPolicy.encryptPassword(password, salt);
    }

    public String getDisplayNameShortPy() {
        return displayNameShortPy;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public FishNotice getFishNotice() {
        return fishNotice;
    }

    public void setFishNotice(FishNotice fishNotice) {
        this.fishNotice = fishNotice;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    /**
     * 修改密码后是否一次重新登录
     */
    public boolean isLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(boolean lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 是否已经发生密码过期邮件
     */
    public boolean isSendPwdExpiredEmail() {
        return sendPwdExpiredEmail;
    }

    public void setSendPwdExpiredEmail(boolean sendPwdExpiredEmail) {
        this.sendPwdExpiredEmail = sendPwdExpiredEmail;
    }

    /**
     * 密码策略
     */
    public String getPwdPolicyId() {
        return pwdPolicyId;
    }

    public void setPwdPolicyId(String pwdPolicyId) {
        this.pwdPolicyId = pwdPolicyId;
    }

    public String getAdRealm() {
        return adRealm;
    }

    public void setAdRealm(String adRealm) {
        this.adRealm = adRealm;
    }

    public ForceResetPasswordReason getForceResetPasswordReason() {
        return forceResetPasswordReason;
    }

    public void setForceResetPasswordReason(ForceResetPasswordReason forceResetPasswordReason) {
        this.forceResetPasswordReason = forceResetPasswordReason;
    }

    /**
     * 检查新密码是否与旧密码一致
     *
     * @param pwd
     * @return
     */
    public boolean checkOldPwd(String pwd) {
        return this.encryptPassword(pwd).equals(this.getPassword());
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public boolean isCompleteInfo() {
        return completeInfo;
    }

    public void setCompleteInfo(boolean completeInfo) {
        this.completeInfo = completeInfo;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public boolean isCheckEmail() {
        return isCheckEmail;
    }

    public void setCheckEmail(boolean checkEmail) {
        isCheckEmail = checkEmail;
    }

    public boolean isBindFingerPrint() {
        return isBindFingerPrint;
    }

    public void setBindFingerPrint(boolean bindFingerPrint) {
        isBindFingerPrint = bindFingerPrint;
    }

    public boolean isBindVoicePrint() {
        return isBindVoicePrint;
    }

    public void setBindVoicePrint(boolean bindVoicePrint) {
        isBindVoicePrint = bindVoicePrint;
    }

    public boolean isBindFacePrint() {
        return isBindFacePrint;
    }

    public void setBindFacePrint(boolean bindFacePrint) {
        isBindFacePrint = bindFacePrint;
    }

    public boolean isBindBlueKey() {
        return isBindBlueKey;
    }

    public void setBindBlueKey(boolean bindBlueKey) {
        isBindBlueKey = bindBlueKey;
    }

    public boolean isBindUsbKey() {
        return isBindUsbKey;
    }

    public void setBindUsbKey(boolean bindUsbKey) {
        isBindUsbKey = bindUsbKey;
    }

    public String getGuaranteeId() {
        return this.guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getEmployeeTaggerState() {
        return employeeTaggerState;
    }

    public void setEmployeeTaggerState(String employeeTaggerState) {
        this.employeeTaggerState = employeeTaggerState;
    }

    public Set<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

    public final DateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public final void setLastLoginTime(DateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public final String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public final void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public void setUsernameLowerPy(String usernameLowerPy) {
        this.usernameLowerPy = usernameLowerPy;
    }

    public String getUsernameLowerPy() {
        return usernameLowerPy;
    }

    public void setDisplayNameLowerPy(String displayNameLowerPy) {
        this.displayNameLowerPy = displayNameLowerPy;
    }

    public String getDisplayNameLowerPy() {
        return displayNameLowerPy;
    }

    public String getEmployeeTaggerValue() {
        return UserStatus.STATUS.get(this.employeeTaggerState);
    }

    public void setEmployeeTaggerValue(String employeeTaggerValue) {
        this.employeeTaggerValue = employeeTaggerValue;
    }

    public String getDjUserId() {
        return djUserId;
    }

    public void setDjUserId(String djUserId) {
        this.djUserId = djUserId;
    }

    public boolean getIsMerge() {
        return isMerge;
    }

    public void setIsMerge(boolean merge) {
        isMerge = merge;
    }

    public boolean isSecondaryConfirmation() {
        return secondaryConfirmation;
    }

    public void setSecondaryConfirmation(boolean secondaryConfirmation) {
        this.secondaryConfirmation = secondaryConfirmation;
    }


    public String getDeputyEmail() {
        return deputyEmail;
    }

    public void setDeputyEmail(String deputyEmail) {
        this.deputyEmail = deputyEmail;
    }

    public boolean isCheckDeputyEmail() {
        return isCheckDeputyEmail;
    }

    public void setCheckDeputyEmail(boolean checkDeputyEmail) {
        isCheckDeputyEmail = checkDeputyEmail;
    }


    public String getSpareEmail() {
        return spareEmail;
    }

    public void setSpareEmail(String spareEmail) {
        this.spareEmail = spareEmail;
    }

    public boolean isCheckSpareEmail() {
        return isCheckSpareEmail;
    }

    public void setCheckSpareEmail(boolean checkSpareEmail) {
        isCheckSpareEmail = checkSpareEmail;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public DateTime getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(DateTime currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getCurrentLoginAddress() {
        return currentLoginAddress;
    }

    public void setCurrentLoginAddress(String currentLoginAddress) {
        this.currentLoginAddress = currentLoginAddress;
    }
}
