package com.example.dataoutput.strategy;

import com.example.dataoutput.entity.UserEntity;

import java.util.Map;

/**
 * 用户导出数据构建
 */
public class UserExportStrategy implements ExportStrategy<UserEntity, Map> {
    @Override
    public Map exportBuild(UserEntity userEntity) {
        return null;
    }
}
