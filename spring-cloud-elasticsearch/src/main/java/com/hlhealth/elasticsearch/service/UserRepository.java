package com.hlhealth.elasticsearch.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.hlhealth.elasticsearch.model.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String>{

}
