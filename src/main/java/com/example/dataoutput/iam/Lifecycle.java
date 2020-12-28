package com.example.dataoutput.iam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * Created by 军 on 2016/2/29.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "生命周期")
public class Lifecycle extends BaseDTO {
	/**
	 * 生效时间
	 */
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	@ApiModelProperty(name = "takeEffect", value = "生效时间")
	public DateTime takeEffect;

	/**
	 * 失效时间
	 */
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	@ApiModelProperty(name = "loseEfficacy", value = "失效时间")
	public DateTime loseEfficacy;

	public DateTime getNotNullTakeEffect() {
		return takeEffect != null ? takeEffect : new DateTime(2000, 1, 1, 0, 0, 0, 0);
	}

	public DateTime getNotNullLoseEfficacy() {
		return loseEfficacy != null ? loseEfficacy : new DateTime(10000, 1, 1, 0, 0, 0, 0);
	}

	/**
	 * 判断是否生效
	 * 
	 * @return true--有效， false--无效
	 */
	public boolean isEffect() {
		boolean result = true;
		if (null != takeEffect) {
			result = takeEffect.isBeforeNow();
		}
		if (result && (null != loseEfficacy)) {
			result = loseEfficacy.isAfterNow();
		}


		return result;
	}

	/**
	 * 是否生效
	 * @return
     */
	public boolean isActive() {
		if (takeEffect != null) {
			return takeEffect.isBeforeNow();
		} else {
			return true;
		}
	}


	/**
	 * 是否过期
	 * @return
     */

    /* 是否失效 */
    public boolean isExpired() {
		if (loseEfficacy != null) {
			return !loseEfficacy.isAfterNow()	;
		}  else {
			return false;
		}
    }
    
    /* 是否生效 */
    public boolean isEffective() {
        return takeEffect == null || takeEffect.isBeforeNow();
    }
    
	/**
	 * 是否合法的输入参数
	 * @return
	 */
	public boolean isValid() {
		if ((takeEffect != null) && (loseEfficacy != null)) {
			if (takeEffect.getMillis() >= loseEfficacy.getMillis()) {
//			if (takeEffect.isAfter(loseEfficacy)) {
//                ValidatorHelper.error("loseEfficacy", "生效时间必须先于失效时间");
			}
		}
		return true;
	}

	public boolean isConflict(Lifecycle lifecycle) {
		return isConflict(this, lifecycle);
	}

	public static boolean isConflict(Lifecycle base, Lifecycle target) {
		if (base == null || target == null) {
			return true;
		}
		return !(base.getNotNullTakeEffect().isAfter(target.getNotNullLoseEfficacy()) ||
				base.getNotNullLoseEfficacy().isBefore(target.getNotNullTakeEffect()));
	}

}
