package com.example.dataoutput.strategy;

import com.example.dataoutput.dao.UserEntityDao;
import com.example.dataoutput.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户导出数据构建
 */
@Component
public class UserExportStrategy implements ExportStrategy<UserEntity, Map> {

    @Autowired
    UserEntityDao userEntityDao;

    @Override
    public List<UserEntity> find() {
        return userEntityDao.findAll();
    }

    @Override
    public Map exportBuild(UserEntity userEntity) {
        return null;
    }

    @Override
    public void export(List<Map> list) {

    }
}
