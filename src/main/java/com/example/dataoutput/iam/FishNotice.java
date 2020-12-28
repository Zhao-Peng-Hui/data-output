package com.example.dataoutput.iam;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户防钓鱼信息。可以用文字、图片
 * <p>
 * Created by tjwang on 2016/7/19.
 */
@ApiModel(description = "用户防钓鱼信息。可以用文字、图片")
public class FishNotice implements Serializable {

    /**
     * "text"|"img"。
     */
    @ApiModelProperty(name = "type", value = "text|img")
    private String type;

    /**
     * type为text，值为字符串
     * type为img，值为图片id
     */
    @ApiModelProperty(name = "notice", value = "type为img，值为图片id；type为text，值为字符串")
    private String notice;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
