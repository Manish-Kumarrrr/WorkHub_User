package com.manish.WorkHub.repository;

import com.manish.WorkHub.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
