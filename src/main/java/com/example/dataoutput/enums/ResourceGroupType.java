package com.example.dataoutput.enums;

/**
 * Created by wm on 2018/7/31.
 */
public enum ResourceGroupType {
    /**
     * 内部组织机构
     */
    INTERIOR_GROUP("内部组织机构"),
    /**
     * 外部组织机构
     */
    EXTERNAL_GROUP("外部组织机构");

    private String name;

    ResourceGroupType() {
    }
    ResourceGroupType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
