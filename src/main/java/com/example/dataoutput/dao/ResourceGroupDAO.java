package com.example.dataoutput.dao;


import com.example.dataoutput.entity.ResourceGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ResourceGroupDAO extends MongoRepository<ResourceGroupEntity, String> {



	@Query("{'id': {'$in': ?0}, 'removed':  false}")
	List<ResourceGroupEntity> findByIdIn(Collection<String> ids);


}
