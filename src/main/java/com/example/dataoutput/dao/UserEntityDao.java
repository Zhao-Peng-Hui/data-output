package com.example.dataoutput.dao;

import com.example.dataoutput.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityDao extends MongoRepository<UserEntity, String> {
}
