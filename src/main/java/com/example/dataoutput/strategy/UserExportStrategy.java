package com.example.dataoutput.strategy;

import com.example.dataoutput.dao.ResourceGroupDAO;
import com.example.dataoutput.dao.UserEntityDao;
import com.example.dataoutput.entity.ResourceGroupEntity;
import com.example.dataoutput.entity.UserEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 用户导出数据构建
 */
@Component
public class UserExportStrategy implements ExportStrategy<UserEntity, Map> {

    @Autowired
    UserEntityDao userEntityDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ResourceGroupDAO resourceGroupDAO;

    @Override
    public List<UserEntity> find() {
        return mongoTemplate.find(Query.query(Criteria.where("username").regex("^T")), UserEntity.class);

    }

    @Override
    public Map exportBuild(UserEntity userEntity) {
        Map map = new HashMap();
        map.put("username", userEntity.getUsername());
        String[] groupIds = null;
        StringBuilder sb = new StringBuilder();
        if (userEntity.getGroupChain().size() != 0) {
            groupIds = userEntity.getGroupChain().toArray()[0].toString().split(",");
        }
        List<String> resultList = new ArrayList<>();
        if (groupIds != null) {
            resultList = new ArrayList<>(groupIds.length);
            Collections.addAll(resultList, groupIds);
            if (groupIds != null && groupIds.length >= 1) {
                List<ResourceGroupEntity> resourceList = resourceGroupDAO.findByIdIn(resultList);
                for (int i = 0; i < groupIds.length; i++) {
                    for (int j = 0; j < resourceList.size(); j++) {
                        if (groupIds[i].equals(resourceList.get(j).getId())) {
                            sb.append(resourceList.get(j).getName()).append(">");
                        }
                    }
                }
                map.put("org", sb.substring(0, sb.length() - 1));
            }
        } else {
            map.put("org", "无");
        }
        return map;
    }

    @Override
    public void export(List<Map> list) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("D:\\bbb.txt", true)));
            for (Map m : list) {
                out.write(m.get("username") + "," + m.get("org") + "\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
