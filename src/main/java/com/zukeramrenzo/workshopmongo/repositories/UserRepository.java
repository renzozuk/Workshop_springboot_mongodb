package com.zukeramrenzo.workshopmongo.repositories;

import com.zukeramrenzo.workshopmongo.domains.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
